package com.zhangju.xingquban.interestclassapp.config;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class MyConfig {

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;//
    public static final int SELECT_PIC_KITKAT                   = 44;//4.4版本以及以上获取相册
    public static final int SELECT_PIC                          = 43;//4.4版本一下相册
    public static final int CROP                                = 0;//4.4版本一下相册
    public static final int MY_REQUEST_CAMERA                   = 23;//6.0掉起 照相机
    public static final int MY_REQUEST_WRITE                    = 50;//6.0掉起 照相机

    public static final int TYPE_USER = 1, TYPE_TEACHER = 2, TYPE_ORGANIZATION = 3, TYPE_LISTEN = 4;

    public static DisplayImageOptions getListOptions() {

        DisplayImageOptions mNormalImageOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.default_icon)
                .showImageForEmptyUri(R.drawable.default_icon)
                .resetViewBeforeLoading(true)
                .build();

        return mNormalImageOptions;
    }

    public static String getSharePreStr(Context mContext, String whichSp, String field) {
        if (mContext == null || whichSp == null || field == null) {
            return "";
        } else {
            SharedPreferences sp = mContext.getSharedPreferences(whichSp, Context.MODE_PRIVATE);
            if (sp != null) {
                return sp.getString(field, "");
            } else {
                return "";
            }
        }
    }

    /*
     * 4.3或以下获取图片的路径
     */
    public static String getRealPathFromURI(final Activity context, Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            // Do not call Cursor.close() on a cursor obtained using this method,
            // because the activity will do that for you at the appropriate time
            Cursor cursor = context.managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }

    /*
     * 4.4或以上 获取图片的路径
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 保存图片到相册
     *
     * @param context 上下文
     * @param bitmap
     */
    public static void saveImageToGallery(final Activity context, final Bitmap bitmap, final String name, final String
            description) {
        String stringUri = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, name, description);
        // 最后通知图库更新
        Logger.e(stringUri);
        // 保存后要扫描一下文件，及时更新到系统目录（一定要加绝对路径，这样才能更新）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.e(getPath(context, Uri.parse(stringUri)));
            MediaScannerConnection.scanFile(context,
                    new String[]{getPath(context, Uri.parse(stringUri))},
                    null,
                    null);

        } else {
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(stringUri)));
        }
        ToastUtil.showToast("保存成功");
    }
}
