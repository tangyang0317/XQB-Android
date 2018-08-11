package com.zhangju.xingquban.refactoring.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;
import com.zhangju.xingquban.refactoring.entity.LessonBean;

import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName ClassListAdapter
 * @Author tangyang
 * @DATE 2018/8/11
 **/
public class ClassListAdapter extends BaseQuickAdapter<LessonBean, BaseViewHolder> {

    public ClassListAdapter() {
        super(R.layout.view_item_lesson, new ArrayList<LessonBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, LessonBean item) {
        helper.addOnClickListener(R.id.delLessonTxt);
        LinearLayout lessonItemLayout = helper.getView(R.id.lessonItemLayout);
        ImageView lessonFaceImg = helper.getView(R.id.lessonFaceImg);
        TextView lessonTitleTxt = helper.getView(R.id.lessonTitleTxt);
        TextView lessonPriceTxt = helper.getView(R.id.lessonPriceTxt);
        TextView delLessonTxt = helper.getView(R.id.delLessonTxt);

        GridLayoutManager.LayoutParams schoolBeautyMasterItemLayoutParams = (GridLayoutManager.LayoutParams) lessonItemLayout.getLayoutParams();
        schoolBeautyMasterItemLayoutParams.width = (ScreenUtils.getScreenWidth(mContext) - 60) / 2;
        schoolBeautyMasterItemLayoutParams.height = GridLayoutManager.LayoutParams.WRAP_CONTENT;
        lessonItemLayout.setLayoutParams(schoolBeautyMasterItemLayoutParams);

        ViewGroup.LayoutParams layoutParams = lessonFaceImg.getLayoutParams();
        layoutParams.width = (ScreenUtils.getScreenWidth(mContext) - 80) / 2;
        layoutParams.height = GridLayoutManager.LayoutParams.WRAP_CONTENT;
        lessonFaceImg.setLayoutParams(layoutParams);

        Glide.with(mContext).load(item.getPicture()).into(lessonFaceImg);
        lessonPriceTxt.setText("¥" + item.getPrice());
        lessonTitleTxt.setText(item.getName());
    }
}
