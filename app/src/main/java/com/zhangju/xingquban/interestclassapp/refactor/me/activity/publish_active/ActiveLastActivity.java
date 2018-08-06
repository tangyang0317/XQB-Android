package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoParamAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.net.Request;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ActivePublishedActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.ActiveTripAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.io.File;
import java.util.List;

/**
 * Created by sgfb on 2017/11/2.
 * 发布活动最后一步预览
 */
@ContentView(R.layout.act_publish_active_last)
public class ActiveLastActivity extends FastActivity{
    public static final String ARG_SER_ACTIVE_INFO ="activeInfo";

    @LocalData(ARG_SER_ACTIVE_INFO)
    PublishActiveInfo mInfo;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.host)
    TextView mHost;
    @Bind(R.id.startDate)
    TextView mStartDate;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.activeFeature)
    TextView mActiveFeature;
    @Bind(R.id.activeNote)
    TextView mActiveNote;
    @Bind(R.id.poster)
    ImageView mPoster;
    @Bind(R.id.tripList)
    ListView mTripList;
    ActiveTripAdapter mTripAdapter;

    @Override
    protected void alreadyPrepared() {
        mTitle.setText(mInfo.baseInfo.title);
        mHost.setText(mInfo.baseInfo.host+"举办");
        mStartDate.setText(mInfo.baseInfo.startDate+"开始");
        mLocation.setText(mInfo.baseInfo.place);
        mActiveNote.setText(mInfo.notes.notes);
        mActiveFeature.setText("当孩子离开了父母的庇护，没有手机、银行卡、现金、甚至是水！" +
                "当孩子只能通过完成一系列挑战填饱肚子，所有花费必须要靠自己的时候，他们可以在上海这座城市里生存一天吗？" +
                "要生存，如何获取生存基金？要选择什么方式赚更多的钱来维持生计？" +
                "要选择打工还是做生意？ " +
                "如果打工要体验什么职业？ " +
                "如果要做生意，进货有什么讲究？" +
                "加入“魔都生存”战队 这是一场属于孩子的蜕变之旅。");
        mTripList.setAdapter(mTripAdapter=new ActiveTripAdapter(this,mInfo.schedules.schedules));
        Glide.with(this).load(new File(mInfo.baseInfo.titlePic)).into(mPoster);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                requestPublishActive();
            }
        });
    }

    /**
     * 开始请求接口发布活动
     */
    private void requestPublishActive(){
        startTask(Task.begin(new NoParamAction<Request>() { //上传头像，返回时接收url

            @Override
            protected Request executeAdapt() {
                Request request = Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(mInfo.baseInfo.titlePic));
                request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                return request;
            }
        })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty()) {
                            mInfo.baseInfo.titlePicUrl = listResponse.data.get(0).fileName;
                        } else {
                            N.showShort(ActiveLastActivity.this, "上传图片资源时异常");
                            stopTask();
                        }
                    }
                })
                .cycleList(new Action<Response<List<ResponseUploadImage>>, List<PublishActiveFeature>>() { //遍历上传是图片类型的活动特色介绍
                    @Override
                    protected List<PublishActiveFeature> execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                        return mInfo.featured.featured;
                    }
                })
                .filter(new Action<PublishActiveFeature, Boolean>() {
                    @Override
                    protected Boolean execute(PublishActiveFeature param) throws Throwable {
                        return param != null && param.type == PublishActiveFeature.TYPE_IMAGE;
                    }
                })
                .next(new Action<PublishActiveFeature, Request>() {

                    @Override
                    protected Request execute(PublishActiveFeature param) throws Throwable {
                        Request request = Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(param.content));
                        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        request.setTag(param);
                        return request;
                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty()) {
                            ((PublishActiveFeature) request.getTag()).imageUrl = listResponse.data.get(0).fileName;
                        } else {
                            N.showShort(ActiveLastActivity.this, "上传图片资源时异常");
                            stopTask();
                        }
                    }
                })
                .again(new Action<List<Response<List<ResponseUploadImage>>>, Request>() { //开始发布活动
                    @Override
                    protected Request execute(List<Response<List<ResponseUploadImage>>> param) throws Throwable {
                        Request request = Request.obtain(MeInterface.POST_ACTIVE_PUBLISH);
                        request.put("title", mInfo.baseInfo.title)
                                .put("titlePic", mInfo.baseInfo.titlePicUrl)
                                .put("customerId", UserManager.getInstance().getUser().id)
                                .put("viprice", 0)
                                .put("price", mInfo.baseInfo.signupSettings.price)
                                .put("allows", mInfo.baseInfo.signupSettings.ticketCount)
                                .put("sponsor", mInfo.baseInfo.host)
                                .put("partIn", mInfo.baseInfo.signupSettings.signUpType)
                                .put("partName", mInfo.baseInfo.signupSettings.needName)
                                .put("partPhone", mInfo.baseInfo.signupSettings.needPhone)
                                .put("partSex", mInfo.baseInfo.signupSettings.needSex)
                                .put("partAge", mInfo.baseInfo.signupSettings.needAge)
                                .put("bondNumber", mInfo.baseInfo.signupSettings.freeTicketCount)
                                .put("bondPrice",(int)mInfo.baseInfo.signupSettings.price)
                                .put("remainNumber", mInfo.baseInfo.signupSettings.freeTicketCount)
                                .put("buyNumber", mInfo.baseInfo.signupSettings.buyLimit)
                                .put("priceType", mInfo.baseInfo.signupSettings.signUpType)
                                .put("voteChannel", mInfo.baseInfo.voteStatus)
                                .put("voteChannelType", mInfo.baseInfo.voteType)
                                .put("FinalTime", mInfo.baseInfo.endDate)
                                .put("longitude", mInfo.baseInfo.longitude)
                                .put("latitude", mInfo.baseInfo.latitude)
                                .put("atime", mInfo.baseInfo.startDate)
                                .put("featured", concatFeature())
                                .put("schedules", mInfo.schedules.schedules)
                                .put("notes", mInfo.notes.notes)
                                .put("statement", "活动声明")
                                .put("catagoriesId", mInfo.baseInfo.categoryId)
                                .put("catagoryName", mInfo.baseInfo.categoryName)
                                .put("place", mInfo.baseInfo.place)
                                .put("counts",mInfo.baseInfo.signupSettings.ticketCount)
                                .put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId);
                        return request;
                    }
                })
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        System.out.println("发布活动接口返回:" + response);
                        if(response.success){
                            startActivity(PublishActiveDoneActivity.class);
                            EventObserver.getInstance().sendEvent(ActiveLastActivity.this,new EventRefresh(ActivePublishedActivity.class));
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        N.showShort(ActiveLastActivity.this,"发布活动异常");
                    }
                });
                param.printStackTrace();
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
    }

    /**
     * 连接活动特色文本和图片地址
     * @return 文本和图片地址
     */
    private String concatFeature(){
        StringBuilder sb=new StringBuilder();

        for(PublishActiveFeature feature:mInfo.featured.featured){
            sb.append(feature.type==PublishActiveFeature.TYPE_TEXT?feature.content:feature.imageUrl).append("#");
        }
        if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    @Bind(R.id.addSomething)
    private void addSomething(){

    }
}
