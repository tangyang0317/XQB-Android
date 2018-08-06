package com.zhangju.xingquban.interestclassapp.shadow;

import android.os.Handler;
import android.os.Looper;

import com.fastlib.BuildConfig;
import com.fastlib.net.NetManager;
import com.fastlib.net.NetProcessor;
import com.fastlib.net.Request;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2017/12/26.
 * NetManager影子.
 * 1.截取立即返回模式时的返回数据
 * 2.加入回调锁
 */
@Implements(NetManager.class)
public class NetManagerShadow{
    @RealObject private NetManager mNetManager;
    public final static Object mLock=new Object();
    private byte[] mResponseData;

    @Implementation
    public byte[] enqueue(Request request, boolean promptlyBackMode)throws IOException{
        ThreadPoolExecutor pool=request.getExecutor();
        NetProcessor processor=new NetProcessor(request,new NetProcessor.OnCompleteListener(){
            @Override
            public void onComplete(NetProcessor processor1){
                System.out.println(processor1);
                synchronized (mLock){
                    mLock.notifyAll();
                }
            }
        },new Handler(Looper.getMainLooper()));
        if(promptlyBackMode){ //如果是立即返回模式，不进入线程池直接运行后返回数据
            processor.run();
            return mResponseData=processor.getResponse();
        }
        if(pool!=null) pool.execute(processor);
        else NetManager.sRequestPool.execute(processor);
        return null;
    }

    public byte[] getResponse(){
        return mResponseData;
    }
}
