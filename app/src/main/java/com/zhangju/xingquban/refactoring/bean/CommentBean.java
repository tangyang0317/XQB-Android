package com.zhangju.xingquban.refactoring.bean;

/**
 * @packageName com.zhangju.xingquban.refactoring.bean
 * @FileName CommentBean
 * @Author tangyang
 * @DATE 2018/9/28
 **/
public class CommentBean {

    private String userHeadPic;
    private String userNickName;
    private String commentTime;
    private String commentContent;

    public String getUserHeadPic() {
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic) {
        this.userHeadPic = userHeadPic;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
