package com.zhangju.xingquban.refactoring.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;
import com.zhangju.xingquban.refactoring.bean.CommentBean;

import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName CommentAdapter
 * @Author tangyang
 * @DATE 2018/9/28
 **/
public class CommentAdapter extends BaseQuickAdapter<ResDetailBean.AaDataBean, BaseViewHolder> {

    public CommentAdapter() {
        super(R.layout.item_resource_comment, new ArrayList<ResDetailBean.AaDataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ResDetailBean.AaDataBean item) {
        ImageView commentUserPicImg = helper.getView(R.id.commentUserPicImg);
        TextView userNickNameTxt = helper.getView(R.id.userNickNameTxt);
        TextView userCommentTimeTxt = helper.getView(R.id.userCommentTimeTxt);
        TextView commentContentTxt = helper.getView(R.id.commentContentTxt);

        userNickNameTxt.setText(item.getCustomerName());
        Glide.with(mContext).load(item.getCustomerPicture()).placeholder(R.drawable.app_icon).dontAnimate().into(commentUserPicImg);
        userCommentTimeTxt.setText(item.getAddUserTime());
        commentContentTxt.setText(item.getSummary());
    }
}
