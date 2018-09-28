package com.zhangju.xingquban.refactoring.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.refactoring.bean.CommentBean;

import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName CommentAdapter
 * @Author tangyang
 * @DATE 2018/9/28
 **/
public class CommentAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {

    public CommentAdapter() {
        super(R.layout.item_resource_comment, new ArrayList<CommentBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {
        ImageView commentUserPicImg = helper.getView(R.id.commentUserPicImg);
        TextView userNickNameTxt = helper.getView(R.id.userNickNameTxt);
        TextView userCommentTimeTxt = helper.getView(R.id.userCommentTimeTxt);
        TextView commentContentTxt = helper.getView(R.id.commentContentTxt);

    }
}
