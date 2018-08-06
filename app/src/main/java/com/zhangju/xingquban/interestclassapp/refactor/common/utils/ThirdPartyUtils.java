package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.app.task.NoParamAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.DefaultDownload;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.common.ShareUiListener;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.PayResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by sgfb on 2017/11/9.
 * 第三方支付，分享和登录封装。注意微信的第三方功能必须要正式签名包才可调起
 */
public class ThirdPartyUtils {
    public static final String QQ_APPID="1104938073";
    public static final String WEIBO_APP_KEY ="3721758782";
    public static final String WX_APPID="wx792fbfb68e82c491";
    public static final String WX_SECRET="0964f85c18145f9eccba554d68b45572";
    public final String SHARE_APP_NAME="兴趣班";
    public IWXAPI mWxAPI;
    public Tencent mTencent;
    private static ThirdPartyUtils mThirdPartyUtils;

    private ThirdPartyUtils(Context context){
//        WbSdk.install(context,new AuthInfo(context,WEIBO_APP_KEY,"http://www.sharesdk.cn","all"));
        mTencent=Tencent.createInstance(QQ_APPID,context);
        mWxAPI=WXAPIFactory.createWXAPI(context,WX_APPID);
    }

    public synchronized static ThirdPartyUtils getInstance(Context context){
        if(mThirdPartyUtils ==null) mThirdPartyUtils =new ThirdPartyUtils(context.getApplicationContext());
        return mThirdPartyUtils;
    }

    /**
     * 分享链接到微信
     * @param url 链接
     * @param title 标题
     * @param description 描述
     * @param imagePath 本地图像
     * @param toFriend 如果是true分享到好友，false分享到朋友圈
     */
    public void shareUrlToWechat(String url, String title, String description, String imagePath, boolean toFriend){
        WXWebpageObject webPageObject=new WXWebpageObject();

        webPageObject.webpageUrl=url;
        WXMediaMessage msg=new WXMediaMessage(webPageObject);
        msg.title=title;
        msg.description=description;
        msg.setThumbImage(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(imagePath),50,50,false));

        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=Long.toString(System.currentTimeMillis());
        req.message=msg;
        req.scene=toFriend?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        mWxAPI.sendReq(req);
    }

    /**
     * 分享链接到微信
     * @param url 链接
     * @param title 标题
     * @param description 描述
     * @param image 图像
     * @param isLocalImage 是否本地图像
     * @param toFriend 如果是true分享到好友，false分享到朋友圈
     */
    public void shareUrlToWechat(final String url, final String title, final String description, final String image, boolean isLocalImage, final boolean toFriend){
        if(isLocalImage) shareUrlToWechat(url,title,description,image,toFriend);
        else{
            final File file=new File(FileHelper.getCacheFolder(MyApp.instance,Environment.DIRECTORY_PICTURES),"shareTemp.jpg");
            if(TextUtils.isEmpty(image)){ //如果图片为空使用默认icon
                Bitmap bitmap=BitmapFactory.decodeResource(MyApp.instance.getResources(),R.drawable.app_icon);
                try {
                    file.createNewFile();
                    OutputStream outputStream=new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,90,outputStream);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                shareUrlToWechat(url,title,description,file.getAbsolutePath(),toFriend);
                return;
            }
            Request request=new Request("get",image);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.setHadRootAddress(true);
            request.setDownloadable(new DefaultDownload(file));
            request.setListener(new SimpleListener<String>(){

                @Override
                public void onResponseListener(Request r, String result) {
                    shareUrlToWechat(url,title,description,file.getAbsolutePath(),toFriend);
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    N.showShort(MyApp.instance,"分享的图像异常,分享失败");
                }
            });
            request.start();
        }
    }

    /**
     * 分享链接到QQ
     * @param activity 上下文
     * @param url 链接
     * @param title 标题
     * @param summary 内容
     * @param imageUrl 图像Url或本地路径
     */
    public void shareToQQ(Activity activity,String url, String title, String summary, String imageUrl){
        Bundle params=new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE,title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,summary);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,imageUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,SHARE_APP_NAME);
        mTencent.shareToQQ(activity,params,new ShareUiListener());


    }

    /**
     * 分享到QQ空间
     * @param activity 上下文
     * @param url 链接
     * @param title 标题
     * @param summary 内容
     * @param imageUrls 图片组
     */
    public void shareUrlToZone(Activity activity, String url, String title, String summary, ArrayList<String> imageUrls){
        Bundle params=new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE,title);
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY,summary);
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,url);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,imageUrls);
        mTencent.shareToQzone(activity, params, new ShareUiListener() );
    }

    /**
     * 分享到微博
     * @param context
     * @param content
     * @param image
     */
    public void shareToWeibo(Activity context, String content, String image){
        /*WbShareHandler handler=new WbShareHandler(context);
        WeiboMultiMessage multiMessage=new WeiboMultiMessage();

        multiMessage.textObject=new TextObject();
        multiMessage.textObject.text=content;
        multiMessage.imageObject=new ImageObject();
        multiMessage.imageObject.imagePath=image;
        if(handler.registerApp()){
            handler.shareMessage(multiMessage,false);
        }
        else */N.showShort(context,"微博连接失败，无法分享");
    }

    /**
     * 请求微信登录
     * 回调使用广播事件{}
     */
    public void loginByWechat(){
        SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="replace";
        mWxAPI.sendReq(req);
    }

    /**
     * 拉起微信支付
     * @param appid
     * @param partnerId
     * @param timestamp
     * @param packageValue
     * @param nonceStr
     * @param sign
     */
    public void payByWechat(String appid,String partnerId,String prepayId,String timestamp,String packageValue,String nonceStr,String sign){
        if(!mWxAPI.isWXAppInstalled()||!mWxAPI.isWXAppSupportAPI()){
            N.showLong(MyApp.instance,"微信未安装或者版本太低");
            return;
        }
        PayReq request=new PayReq();
        request.appId=appid;
        request.partnerId=partnerId;
        request.prepayId=prepayId;
        request.timeStamp=timestamp;
        request.packageValue=packageValue;
        request.nonceStr=nonceStr;
        request.sign=sign;
        mWxAPI.sendReq(request);
    }

    /**
     * 拉起支付宝支付
     * @param payInfo
     */
    public void payByAli(final FastActivity activity, final String payInfo){
        activity.startTask(Task.begin(new NoParamAction<String>(){

            @Override
            protected String executeAdapt() {
                PayTask payTask=new PayTask(activity);
                return payTask.pay(payInfo,true);
            }
        })
        .next(new NoReturnAction<String>() {
            @Override
            public void executeAdapt(String param) {
                PayResult payResult=new PayResult(param);
                if("9000".equals(payResult.getResultStatus())){
                    N.showLong(activity,"支付成功");
                    EventObserver.getInstance().sendEvent(activity,new EventPayResult(true,"aipay"));
                }
                else{
                    if("8000".equals(payResult.getResultStatus())) N.showLong(activity,"支付结果确认中");
                    else if("6001".equals(payResult.getResultStatus())) N.showLong(activity,"取消支付");
                    else N.showLong(activity,"支付失败");
                    EventObserver.getInstance().sendEvent(activity,new EventPayResult(false,"aipay"));
                }
            }
        }, ThreadType.MAIN));
    }
}
