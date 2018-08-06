package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean1;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean2;
import com.zhangju.xingquban.interestclassapp.bean.me.CustomMessageBean;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomAdapter;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomMemberAdapter;
import com.zhangju.xingquban.interestclassapp.util.JsonUtils;
import com.zhangju.xingquban.interestclassapp.view.livestream.LiveGiftView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.NotificationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 聊天室成员资料缓存
 * Created by huangjun on 2016/1/18.
 */
public class ChatRoomMemberCache {

    private static final String TAG = "ChatRoomMemberCache";
    //    public static boolean isManage;
    private ChatRoomAdapter mChatRoomAdapter; // 消息适配器
    private ChatRoomMemberAdapter mChatRoomMemberAdapter; // 成员列表适配器
    private String                mRoomId;
    private List<ChatRoomMember>  mAvatarData;
    private Object                mChatRoomInfo;
    private TextView              mLiveNum; // 用于实时显示在线人数
    private TextView              tvWindowSetManage; // 设置场控按钮
    private LiveGiftView mGiftView;
    private CustomMessageBean mCustomMessageBean;
    private int                   mCounts;

    private HashMap<String, String>  mUserRecord = new HashMap<>();// 保存用户进入聊天室的通知,防止重复显示
    private HashMap<String, Integer> mGiftRecord = new HashMap<>();// 保存用户进入聊天室的通知,防止重复显示

    private onCustomMessageListener mOnCustomMessageListener; // 自定义消息的回调监听


    public static ChatRoomMemberCache getInstance() {
        return InstanceHolder.instance;
    }

    private Map<String, Map<String, ChatRoomMember>> cache = new HashMap<>();

    private Map<String, List<SimpleCallback<ChatRoomMember>>> frequencyLimitCache = new HashMap<>(); // 重复请求处理

    private List<RoomMemberChangedObserver> roomMemberChangedObservers = new ArrayList<>();

    public void clear() {
        cache.clear();
        frequencyLimitCache.clear();
        roomMemberChangedObservers.clear();
    }

    public void clearRoomCache(String roomId) {
        if (cache.containsKey(roomId)) {
            cache.remove(roomId);
        }
    }

    public ChatRoomMember getChatRoomMember(String roomId, String account) {
        if (cache.containsKey(roomId)) {
            return cache.get(roomId).get(account);
        }

        return null;
    }

    public void saveMyMember(ChatRoomMember chatRoomMember) {
        saveMember(chatRoomMember);
    }

    /**
     * 从服务器获取聊天室成员资料（去重处理）（异步）
     */
    public void fetchMember(final String roomId, final String account, final SimpleCallback<ChatRoomMember> callback) {
        if (TextUtils.isEmpty(roomId) || TextUtils.isEmpty(account)) {
            callback.onResult(false, null);
            return;
        }

        // 频率控制
        if (frequencyLimitCache.containsKey(account)) {
            if (callback != null) {
                frequencyLimitCache.get(account).add(callback);
            }
            return; // 已经在请求中，不要重复请求
        } else {
            List<SimpleCallback<ChatRoomMember>> cbs = new ArrayList<>();
            if (callback != null) {
                cbs.add(callback);
            }
            frequencyLimitCache.put(account, cbs);
        }

        // fetch
        List<String> accounts = new ArrayList<>(1);
        accounts.add(account);
        NIMClient.getService(ChatRoomService.class).fetchRoomMembersByIds(roomId, accounts).setCallback(new RequestCallbackWrapper<List<ChatRoomMember>>() {
            @Override
            public void onResult(int code, List<ChatRoomMember> members, Throwable exception) {
                ChatRoomMember member = null;
                boolean hasCallback = !frequencyLimitCache.get(account).isEmpty();
                boolean success = code == ResponseCode.RES_SUCCESS && members != null && !members.isEmpty();

                // cache
                if (success) {
                    saveMembers(members);
                    member = members.get(0);
                } else {
                    Log.e(TAG, "获取指定成员信息列表失败, 错误码:" + code);
                }
                // callback
                if (hasCallback) {
                    List<SimpleCallback<ChatRoomMember>> cbs = frequencyLimitCache.get(account);
                    for (SimpleCallback<ChatRoomMember> cb : cbs) {
                        cb.onResult(success, member);
                    }
                }

                frequencyLimitCache.remove(account);
            }
        });
    }

    public void fetchRoomMembers(String roomId, MemberQueryType memberQueryType, long time, int limit,
								 final SimpleCallback<List<ChatRoomMember>> callback) {
        if (TextUtils.isEmpty(roomId)) {
            callback.onResult(false, null);
            return;
        }

        NIMClient.getService(ChatRoomService.class).fetchRoomMembers(roomId, memberQueryType, time, limit)
                .setCallback(new RequestCallbackWrapper<List<ChatRoomMember>>() {
                    @Override
                    public void onResult(int code, List<ChatRoomMember> result, Throwable exception) {
                        boolean success = code == ResponseCode.RES_SUCCESS;

                        if (success) {
                            saveMembers(result);

                        } else {
                            Log.e(TAG, "拉取成员信息失败, 错误码:" + code);
                        }

                        if (callback != null) {
                            callback.onResult(success, result);
                        }
                    }
                });
    }

    private void saveMember(ChatRoomMember member) {
        if (member != null && !TextUtils.isEmpty(member.getRoomId()) && !TextUtils.isEmpty(member.getAccount())) {
            Map<String, ChatRoomMember> members = cache.get(member.getRoomId());

            if (members == null) {
                members = new HashMap<>();
                cache.put(member.getRoomId(), members);
            }

            members.put(member.getAccount(), member);
        }
    }

    private void saveMembers(List<ChatRoomMember> members) {
        if (members == null || members.isEmpty()) {
            return;
        }

        for (ChatRoomMember m : members) {
            saveMember(m);
        }
    }

    /**
     * 将直播页面需要改变的View传递过来
     */
    public void InitChatRoomView(TextView tvWindowSetManage) {
        this.tvWindowSetManage = tvWindowSetManage;
        //        this.isManage = isManage;
    }


    /**
     * ************************************ 单例 ***************************************
     */
    static class InstanceHolder {
        final static ChatRoomMemberCache instance = new ChatRoomMemberCache();
    }

    /**
     * ********************************** 监听 ********************************
     */

    public void registerObservers(boolean register) {
        NIMClient.getService(ChatRoomServiceObserver.class).observeReceiveMessage(incomingChatRoomMsg, register);
    }

    private boolean mFlag = true;
    private int mGiftCount;
    private Observer<List<ChatRoomMessage>> incomingChatRoomMsg = new Observer<List<ChatRoomMessage>>() {
        @Override
        public void onEvent(List<ChatRoomMessage> messages) {
            if (messages == null || messages.isEmpty()) {
                return;
            }
            for (ChatRoomMessage msg : messages) {
                if (msg == null) {
                    Log.e(TAG, "receive chat room message null");
                    continue;
                }
                if (msg.getMsgType() == MsgTypeEnum.text) {   // 文本消息
                    //                    String senderNick = msg.getChatRoomMessageExtension().getSenderNick();
                    if (msg.getContent() != null) {
                        mChatRoomAdapter.addItem(new ChatRoomItemBean2(2, messages.get(0))); //将收到的消息显示在界面上
                    }
                }
                if (msg.getMsgType() == MsgTypeEnum.custom) {   // 自定义消息
                    Map<String, Object> remoteExtension = msg.getRemoteExtension();
                    Integer msgType = (Integer) remoteExtension.get("msgtype");
                    switch (msgType) {
                        case 10001: // 送礼
                            showGift(msg, remoteExtension);
                            break;
                        case 10003: // 直播结束
                            String json = JsonUtils.obj2JsonString(remoteExtension);
                            mOnCustomMessageListener.onLiveExitMessage(json);
                            break;
                    }
                }

                if (msg.getMsgType() == MsgTypeEnum.notification) {
                    handleNotification(msg);
                }
            }
        }
    };

    private void showGift(ChatRoomMessage msg, Map<String, Object> remoteExtension) {
        String json = JsonUtils.obj2JsonString(remoteExtension);
        mCustomMessageBean = JSONObject.parseObject(json, CustomMessageBean.class);
        CustomMessageBean.AttachBean attachBean = mCustomMessageBean.getAttach().get(0);
        mCounts = attachBean.getCounts();
        String name = attachBean.getName();
        if (name != null) {
            Integer count = mGiftRecord.get(name);
            if (count != null) {
                mGiftRecord.put(name, mCounts + count);
            } else {
                mGiftRecord.put(name, mCounts);
            }
        }
        mOnCustomMessageListener.onGitGiftMessage(attachBean.getStdCoin());

        mChatRoomAdapter.addItem(new ChatRoomItemBean1(1, msg, "送出了" +
                mCounts + "个" + name));

        // 根据获取到的counts每隔500ms展示一次礼物动画
        if (mGiftView != null) {
            if (mFlag) {
                mFlag = false;
                mGiftCount = mCounts;
                mTimer = new Timer();
                TimerTask mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(0);
                        mGiftCount = mGiftCount - 1;
                        if (mGiftCount == 0) {
                            mFlag = true;
                            if (mTimer != null) {
                                mTimer.cancel();
                                mTimer = null;
                            }
                        }
                    }
                };
                mTimer.schedule(mTimerTask, 0, 500);
            } else {
                mGiftCount = mGiftCount + mCounts;
            }
        }
    }

    /**
     * 展示礼物
     *
     * @param bean
     */
    private void showGift(CustomMessageBean bean) {
        final CustomMessageBean.AttachBean attachBean = bean.getAttach().get(0);

        mGiftView.showGift(bean.getFrom().getIcon(), bean.getFrom().getName(), attachBean.getName(),
                attachBean.getIcon());
    }

    /**
     * 初始化礼物动画
     *
     * @param context
     * @param view
     */
    public void initGiftView(Context context, View view) {
        mGiftView = new LiveGiftView(context, view);
        mGiftView.initView();
    }

    /**
     * 初始化普通消息的数据
     */
    public void InitMessageData(ChatRoomAdapter chatRoomAdapter) {
        this.mChatRoomAdapter = chatRoomAdapter;
    }

    /**
     * 初始化聊天室成员信息
     *
     * @param chatRoomMemberAdapter 头像列表的adapter
     * @param avatarData            头像列表的data
     * @param roomId                房间号
     * @param liveNum               在线人数的view(实时更新)
     */
    public void InitMemberData(ChatRoomMemberAdapter chatRoomMemberAdapter, List<ChatRoomMember> avatarData, String
            roomId, TextView liveNum) {
        this.mChatRoomMemberAdapter = chatRoomMemberAdapter;
        this.mAvatarData = avatarData;
        this.mRoomId = roomId;
        this.mLiveNum = liveNum;
    }

    private void handleNotification(ChatRoomMessage message) {
        if (message.getAttachment() == null) {
            return;
        }

        String roomId = message.getSessionId();
        ChatRoomNotificationAttachment attachment = (ChatRoomNotificationAttachment) message.getAttachment();
        List<String> targets = attachment.getTargets();
        if (targets != null) {
            for (String target : targets) {
                ChatRoomMember member = getChatRoomMember(roomId, target);

                NotificationType type = attachment.getType();
                if (type == NotificationType.ChatRoomMemberIn) {
                    if (mUserRecord.get(message.getFromAccount() + "") == null) {
                        refreshAvatar(message, "进入了直播间");
                        mUserRecord.put(message.getFromAccount() + "", "1");
                    }
                } else if (type == NotificationType.ChatRoomMemberExit) {
                    //                    refreshAvatar(message, "离开了直播间");
                    refreshAvatar(null, null);
                } else {
                    handleMemberChanged(attachment.getType(), member, message);
                }
            }
            getChatRoomInfo(); // 获取聊天室信息, 实时更新在线人数
        }
    }


    private void handleMemberChanged(NotificationType type, ChatRoomMember member, ChatRoomMessage message) {
        if (member == null) {
            return;
        }
        String accid = null;
        StringBuffer buffer;
        switch (type) {
            case ChatRoomManagerAdd:
                member.setMemberType(MemberType.ADMIN);
                mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, member, "被任命为场控"));
                if (tvWindowSetManage != null)
                    tvWindowSetManage.setText("取消场控");
                break;
            case ChatRoomManagerRemove:
                member.setMemberType(MemberType.GUEST);
                mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, member, "被取消场控"));
                if (tvWindowSetManage != null)
                    tvWindowSetManage.setText("设为场控");
                break;
            case ChatRoomMemberBlackAdd:
                member.setInBlackList(true);
                break;
            case ChatRoomMemberBlackRemove:
                member.setInBlackList(false);
                break;
            case ChatRoomMemberMuteAdd:
                mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, member, "被管理员禁言了"));
                member.setMuted(true);
                refreshAvatar(null, null);
                break;
            case ChatRoomMemberMuteRemove:
                mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, member, "被取消禁言"));
                member.setMuted(false);
                member.setMemberType(MemberType.GUEST);
                refreshAvatar(null, null);
                break;
            case ChatRoomCommonAdd:
                member.setMemberType(MemberType.NORMAL);
                break;
            case ChatRoomCommonRemove:
                member.setMemberType(MemberType.GUEST);
                break;

            case ChatRoomMemberKicked:
                mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, member, "被踢出了直播间"));
                break;
            default:
                break;
        }

        saveMember(member);
    }

    /**
     * 刷新头像列表
     *
     * @param message
     * @param context
     */
    private void refreshAvatar(ChatRoomMessage message, String context) {
        if (message != null && context != null) {
            mChatRoomAdapter.addItem(new ChatRoomItemBean1(1, message, context));
        }
        fetchRoomMembers(mRoomId, MemberQueryType.GUEST, 0, 50, new SimpleCallback<List<ChatRoomMember>>() {
            @Override
            public void onResult(boolean success, List<ChatRoomMember> result) {
                if (mAvatarData != null || mChatRoomMemberAdapter != null) {
                    if (result != null) {
                        mAvatarData.clear();
                        mAvatarData.addAll(result);
                        mChatRoomMemberAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    /**
     * ************************** 在线用户变化通知 ****************************
     */

    public interface RoomMemberChangedObserver {
        void onRoomMemberIn(ChatRoomMember member);

        void onRoomMemberExit(ChatRoomMember member);
    }

    public void registerRoomMemberChangedObserver(RoomMemberChangedObserver o, boolean register) {
        if (o == null) {
            return;
        }

        if (register) {
            if (!roomMemberChangedObservers.contains(o)) {
                roomMemberChangedObservers.add(o);
            }
        } else {
            roomMemberChangedObservers.remove(o);
        }
    }

    /**
     * 获取聊天室基本信息
     * 实时更新在线人数
     */
    public void getChatRoomInfo() {
        NIMClient.getService(ChatRoomService.class).fetchRoomInfo(mRoomId).setCallback(new RequestCallback<ChatRoomInfo>() {
            @Override
            public void onSuccess(ChatRoomInfo chatRoomInfo) {
                int onlineUserCount = chatRoomInfo.getOnlineUserCount(); // 当前在线人数
                if (mLiveNum != null) {
                    mLiveNum.setText(onlineUserCount + "人");
                }
            }

            @Override
            public void onFailed(int i) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    Timer mTimer = new Timer();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mCustomMessageBean != null) {
                showGift(mCustomMessageBean);
            }
        }
    };

    /**
     * 当接收到自定义消息的回调
     */
    interface onCustomMessageListener {
        void onGitGiftMessage(String stdCoin);  // 礼物

        void onLiveExitMessage(String json);    // 退出直播间

    }

    public void setOnCustomMessageListener(onCustomMessageListener onCustomMessageListener) {
        this.mOnCustomMessageListener = onCustomMessageListener;
    }

}
