package com.zhangju.xingquban.interestclassapp.refactor.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.fastlib.net.Request;
import com.mob.tools.network.HttpConnection;
import com.orhanobut.logger.Logger;
import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.MyProgressCallBack;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.XUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import org.xutils.common.Callback.Cancelable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateService
        extends Service {

    private String apkurl;
    private String apkPath;
    private String apkName;
    private boolean canceled = false;
    private NotificationManager        manager;
    private NotificationCompat.Builder notification;
    private RemoteViews                contentView;
    private CanceledReceiver           receiver;
    private Cancelable mCancelable;

    private boolean flag;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        if (intent != null) {
            apkurl = intent.getStringExtra("url");
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            apkPath = MyApp.instance.DISK_ROOT + "/Download";
            //            apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
            Logger.d(apkPath);
            Logger.d(apkurl+"");
            apkName = "xingquban.apk";
            //            if (new File(apkPath, apkName).exists()) {
            //                installApk();
            //            } else {
            registerBroader();
            setUpNotifiction();
            new Thread(new DownApkRunnable()).start();
            //            }
        } else {
            Toast.makeText(UpdateService.this, "SD卡不存在", Toast.LENGTH_SHORT)
                    .show();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 创建通知
     */
    private void setUpNotifiction() {
        manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        int icon = R.drawable.app_logo;
        CharSequence tickerText = "开始下载";
        long when = System.currentTimeMillis();
        notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(icon);
        notification.setContentText(tickerText);
        //		notification = new Notification(icon, tickerText, when);
        //		notification = new Notification();

        contentView = new RemoteViews(getPackageName(),R.layout.notify_update_layout);
        contentView.setTextViewText(R.id.name, "xingquban正在下载中");

        Intent canceledIntent = new Intent("canceled");
        canceledIntent.putExtra("canceled", "canceled");
        PendingIntent canceledPendingIntent = PendingIntent.getBroadcast(
                UpdateService.this, 1, canceledIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        contentView.setOnClickPendingIntent(R.id.tv_order_topay, canceledPendingIntent);
        //		notification.contentView = contentView;
        notification.setContent(contentView);
        manager.notify(0, notification.build());// 发送通知
    }

    /**
     * 取消接收者
     *
     * @author renzhiwen 创建时间 2014-8-16 下午4:05:24
     */
    class CanceledReceiver
            extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("canceled".equals(intent.getStringExtra("canceled"))) {
                canceled = true;
                manager.cancel(0);
                System.out.println("取消");
                if (flag) {
                    flag = false;
                    unregisterReceiver(receiver);
                }
                mCancelable.cancel();
                stopSelf();
            }
        }

    }

    /**
     * 注册广播
     */
    public void registerBroader() {
        flag = true;
        IntentFilter filter = new IntentFilter();
        filter.addAction("canceled");
        receiver = new CanceledReceiver();
        registerReceiver(receiver, filter);
    }

    /**
     * 下载apk
     *
     * @author renzhiwen 创建时间 2014-8-16 下午3:32:34
     */
    class DownApkRunnable
            implements Runnable {

        @Override
        public void run() {
            downloadApk();
        }

    }

    private void downloadApk() {
        try {
            HttpURLConnection connection= (HttpURLConnection) new URL(apkurl).openConnection();

            connection.setDoInput(true);
            connection.connect();
            InputStream in=connection.getInputStream();
            File file=new File(apkPath,apkName);

            file.createNewFile();
            OutputStream out=new FileOutputStream(file);
            long lengthCount=connection.getContentLength();
            long downloadCount=0;
            long timer=System.currentTimeMillis();
            int len;
            byte[] data=new byte[4096];
            while((len=in.read(data))!=-1){
                out.write(data,0,len);
                downloadCount+=len;
                if(System.currentTimeMillis()-timer>500){
                    int progress= (int) (downloadCount*100/lengthCount); // 得到当前进度
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    timer=System.currentTimeMillis();
                }
            }
            in.close();
            out.close();
            System.out.println("下载完成");
            handler.sendEmptyMessage(2);
            canceled = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:// 更新进度
                    int progress = msg.arg1;
                    if (progress <= 100) {
                        //					RemoteViews contentView = notification.contentView;
                        contentView.setTextViewText(R.id.tv_progress, progress
                                + "%");
                        contentView.setProgressBar(R.id.progressbar, 100, progress,
                                false);
                    }
                    manager.notify(0, notification.build());
                    break;
                case 2:// 安装apk
                    manager.cancel(0);
                    installApk();
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 安装apk
     */
    private void installApk() {
        File apkFile = new File(apkPath, apkName);
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyApp.instance, BuildConfig.APPLICATION_ID +
                    ".fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
        }
        startActivity(intent);
        unregisterReceiver(receiver);
        stopSelf();
    }
}
