package com.zhangju.xingquban.refactoring.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName InterestNewExperienceAdapter
 * @Author tangyang
 * @DATE 2018/8/7
 **/
public class InterestNewExperienceAdapter extends BaseQuickAdapter<CurriculumBean.AaDataBean, BaseViewHolder> {

    public InterestNewExperienceAdapter() {
        super(R.layout.item_curriculum_data_sjkc, new ArrayList<CurriculumBean.AaDataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, CurriculumBean.AaDataBean item) {
        helper.addOnClickListener(R.id.textView12);
        ImageView kcImg = helper.getView(R.id.home_data_sjkc_image);
        TextView kcTitleTxt = helper.getView(R.id.home_data_sjkc_title);
        TextView kcJuliTxt = helper.getView(R.id.tv_juli);
        TextView kePriceTxt = helper.getView(R.id.home_data_sjkc_money);
        TextView textView12 = helper.getView(R.id.textView12);
        TextView keText1 = helper.getView(R.id.home_data_sjkc_text1);
        TextView keText2 = helper.getView(R.id.home_data_sjkc_text2);
        TextView keText3 = helper.getView(R.id.home_data_sjkc_text3);
        View dividerView = helper.getView(R.id.dividerView);
        Glide.with(mContext).load(item.getPicture()).error(R.drawable.rec_seize_bitmao).into(kcImg);
        kcTitleTxt.setText(item.getName());
        //米转公里
        int range = item.getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (item.getAreasName() == null) {
            kcJuliTxt.setText(dis + "km");
        } else {
            kcJuliTxt.setText(item.getAreasName() + dis + "km");
        }
        kePriceTxt.setText(new DecimalFormat("##.##").format(item.getVipPrice()));
        keText1.setText(item.getCatagoryName());
        if (item.getMethod() == null) {
            keText2.setText("协商地点");
        } else {
            keText2.setText(item.getMethod());
        }
        keText3.setText(item.getAllows() + "人");
    }
}
