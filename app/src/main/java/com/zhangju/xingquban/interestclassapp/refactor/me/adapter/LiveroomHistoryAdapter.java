package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.LivePayBean;
import com.zhangju.xingquban.interestclassapp.bean.LiveVideoLoveBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponsePublishHistory;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LivePayActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveVideoPlayerActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.VideoCommentActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播发布历史记录
 */
public class LiveroomHistoryAdapter extends FastAdapterForRecycler<ResponsePublishHistory> {
    private boolean mIsAddOp;


    public LiveroomHistoryAdapter(Context context) {
        super(context, R.layout.item_live_room_publish_history);

    }


    @Override
    public void binding(final int position, final ResponsePublishHistory data, final CommonViewHolder holder) {

        mIsAddOp = data.isAddOp;
        if (data.chatUser != null) {
            ChatUser user = data.chatUser;
            holder.setText(R.id.name, user.name);
            Glide.with(mContext).load(user.icon).into((ImageView) holder.getView(R.id.avatar));
        }
        if (data.liveRecord != null) {
            ResponsePublishHistory.LiveRecord record = data.liveRecord;
            holder.setText(R.id.address, record.address);
            Glide.with(mContext).load(record.roomPic).into((ImageView) holder.getView(R.id.cover));
        }
        holder.setText(R.id.date, data.sdate);
        holder.setText(R.id.visitCount, String.format(Locale.getDefault(), "%d人看过", data.seeCount));
        holder.setText(R.id.thumbCount, String.format(Locale.getDefault(), "%d次点赞", data.likesCount));

        //看回放
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils httpUtils = new HttpUtils();
                final String token = UserManager.getInstance().getToken();
                RequestParams params = new RequestParams();
                params.addHeader("X-CustomToken", token);
                params.addBodyParameter("liveVdoId", data.id);
                String url = UrlUtils.URL_INITROOM;
                httpUtils.send(HttpRequest.HttpMethod.POST,
                        url,
                        params,
                        new RequestCallBack<String>() {
                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                String json = responseInfo.result;
                                LivePayBean bean = JSONObject.parseObject(json, LivePayBean.class);
                                try {
                                    if (bean != null && bean.getAttachData() != null) {
                                        boolean pay = bean.getAttachData().isPay();//是否支付费用
                                        //当视频免费或者是已付费
                                        Intent intent = new Intent();

                                        String id = bean.getAaData().getId() == null ? "" : bean.getAaData().getId();
                                        String liveVdoUrl = bean.getAttachData().getLiveVdoUrl() == null ? "" : bean.getAttachData().getLiveVdoUrl();
                                        String orig_video_key = bean.getAaData().getOrig_video_key() == null ? "" : bean.getAaData().getOrig_video_key();
                                        boolean addOp = bean.getAaData().isAddOp();

                                        intent.setClass(mContext, LiveVideoPlayerActivity.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("pic", data.liveRecord.roomPic);
                                        intent.putExtra("title", data.liveRecord.roomName);
                                        intent.putExtra("url", liveVdoUrl + "/" + orig_video_key);
                                        intent.putExtra("isfocus", addOp);

                                        mContext.startActivity(intent);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(HttpException e, String s) {

                            }
                        });
            }
        });

        //评论
        holder.setOnClickListener(R.id.toComment, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mId = data.id;
                Intent intent = new Intent(mContext, VideoCommentActivity.class);
                intent.putExtra("id", mId);
                mContext.startActivity(intent);

            }
        });

        //分享
        holder.setOnClickListener(R.id.share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareInter.toShare(position,data);

            }
        });

        //收藏
        holder.setOnClickListener(R.id.collection, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = data.id;
                addCollection(id, holder);
            }
        });

        //删除
        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHistory(position, data.id);
            }
        });
    }

    /**
     * 收藏处理
     *
     * @param mId
     * @param holder
     */
    private void addCollection(String mId, final CommonViewHolder holder) {
        HttpUtils httpUtils = new HttpUtils();
        String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addBodyParameter("subjectId", mId);
        params.addBodyParameter("comtType", "liveVdo");
        params.addHeader("X-CustomToken", token);


        String url = UrlUtils.POST_RECORD_LIKE;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LiveVideoLoveBean bean = JSONObject.parseObject(json, LiveVideoLoveBean.class);
                        if (bean.isSuccess()) {

                            mIsAddOp = !mIsAddOp;
                            if (mIsAddOp) { // 点过赞

                                ((ImageView) holder.getView(R.id.collection)).setImageResource(R.mipmap.icon_love_2);
                            } else {
                                ((ImageView) holder.getView(R.id.collection)).setImageResource(R.mipmap.home_data_spkc_x);

                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });


    }

    /**
     * 删除这条记录
     *
     * @param position
     * @param id
     */
    private void deleteHistory(final int position, final String id) {
        FastDialog.showMessageDialog("删除后不可恢复，确定要删除吗？", true).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request(MeInterface.POST_DELETE_LIVE_PUIBLISH);
                request.put("pub", false);
                request.put("id", id);
                request.setListener(new SimpleListener<Response>() {

                    @Override
                    public void onResponseListener(Request r, Response result) {
                        if (result.success) remove(position);
                        else N.showShort(mContext, "删除失败");
                    }
                });
                request.start();
            }
        });
    }
    public ShareInter shareInter;

    public void setShareInter(ShareInter shareInter) {
        this.shareInter = shareInter;
    }

   public interface ShareInter  {
        void toShare(int pos, ResponsePublishHistory data);

    }
}