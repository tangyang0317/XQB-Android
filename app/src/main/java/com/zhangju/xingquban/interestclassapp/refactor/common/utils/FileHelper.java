package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by sgfb on 2017/11/16.
 */
public class FileHelper{

    private FileHelper(){}

    public static File getImageCacheFolder(Context context){
        return getCacheFolder(context,Environment.DIRECTORY_PICTURES);
    }

    public static File getCacheFolder(Context context,String type){
        File cacheFolder=context.getExternalCacheDir();
        File folder=new File(cacheFolder,type);
        folder.mkdir();
        return folder;
    }

    public static File getFileFolder(Context context,String type){
        File fileFolder=context.getExternalFilesDir(type);
        if(!fileFolder.exists()||fileFolder.isFile())
            fileFolder.mkdir();
        return fileFolder;
    }

    public static File getCacheTempFile(Context context,String type){
        return getCacheTempFile(context,type,"",true);
    }

    public static File getCacheTempFile(Context context,String type,String suffix,boolean create){
        File folder=getCacheFolder(context,type);
        File file=new File(folder,System.currentTimeMillis()+"."+suffix);
        if(create) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}