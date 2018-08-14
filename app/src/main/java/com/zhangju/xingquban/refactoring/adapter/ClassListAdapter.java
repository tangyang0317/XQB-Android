package com.zhangju.xingquban.refactoring.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;
import com.zhangju.xingquban.interestclassapp.view.GlideCircleTrans;
import com.zhangju.xingquban.refactoring.entity.LessonBean;
import com.zhangju.xingquban.refactoring.utils.DimentUtils;
import com.zhangju.xingquban.refactoring.view.CornerTransform;

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
        RelativeLayout itemLayout = helper.getView(R.id.itemLayout);
        LinearLayout lessonItemLayout = helper.getView(R.id.lessonItemLayout);
        ImageView lessonFaceImg = helper.getView(R.id.lessonFaceImg);
        TextView lessonTitleTxt = helper.getView(R.id.lessonTitleTxt);
        TextView lessonPriceTxt = helper.getView(R.id.lessonPriceTxt);
        TextView delLessonTxt = helper.getView(R.id.delLessonTxt);


        GridLayoutManager.LayoutParams itemLayoutParams = (GridLayoutManager.LayoutParams) itemLayout.getLayoutParams();
        itemLayoutParams.width = ScreenUtils.getScreenWidth(mContext) / 2;
        itemLayoutParams.height = GridLayoutManager.LayoutParams.WRAP_CONTENT;
        itemLayout.setLayoutParams(itemLayoutParams);

        RelativeLayout.LayoutParams schoolBeautyMasterItemLayoutParams = (RelativeLayout.LayoutParams) lessonItemLayout.getLayoutParams();
        schoolBeautyMasterItemLayoutParams.width = (ScreenUtils.getScreenWidth(mContext) - 100) / 2;
        schoolBeautyMasterItemLayoutParams.height = GridLayoutManager.LayoutParams.WRAP_CONTENT;

        if (helper.getPosition() % 2 == 1) {
            schoolBeautyMasterItemLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            schoolBeautyMasterItemLayoutParams.rightMargin = DimentUtils.dip2px(mContext, 5);
        } else {
            schoolBeautyMasterItemLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            schoolBeautyMasterItemLayoutParams.leftMargin = DimentUtils.dip2px(mContext, 5);
        }
        lessonItemLayout.setLayoutParams(schoolBeautyMasterItemLayoutParams);


        ViewGroup.LayoutParams layoutParams = lessonFaceImg.getLayoutParams();
        layoutParams.width = (ScreenUtils.getScreenWidth(mContext) - 100) / 2;
        layoutParams.height = ((ScreenUtils.getScreenWidth(mContext) - 100) / 2) + 20;
        lessonFaceImg.setLayoutParams(layoutParams);

        CornerTransform cornerTransform = new CornerTransform(mContext, DimentUtils.dip2px(mContext, 5));
        cornerTransform.setExceptCorner(false, false, true, true);

        Glide.with(mContext).load(item.getPicture()).asBitmap().transform(cornerTransform).into(lessonFaceImg);
        lessonPriceTxt.setText("¥" + item.getPrice());
        lessonTitleTxt.setText(item.getName());
    }
}
