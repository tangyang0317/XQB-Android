package com.zhangju.xingquban.interestclassapp.http;

public interface UploadProgressListener {
    /**
     * 上传进度
     *
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}
