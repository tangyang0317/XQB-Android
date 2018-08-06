package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 视频文件夹带文件夹中视频返回实体（不合理结构？）
 */
public class ResponseVideoFolderWithVideo{
    public int organAlbumFilesNum;
    @SerializedName("files")
    public String name;
    public String id;
    public ArrayList<ResponseCourseVideo> organAlbumFilesList;
}