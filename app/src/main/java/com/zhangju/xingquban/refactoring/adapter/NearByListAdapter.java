package com.zhangju.xingquban.refactoring.adapter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;

import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName NearByListAdapter
 * @Author tangyang
 * @DATE 2018/8/4
 **/
public class NearByListAdapter extends BaseQuickAdapter<NearDataBean.AaDataBean, BaseViewHolder> {

    public NearByListAdapter() {
        super(R.layout.home_recyler_item, new ArrayList<NearDataBean.AaDataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NearDataBean.AaDataBean item) {
        ImageView home_recyclerview_item_image = (ImageView) helper.getView(R.id.home_recyclerview_item_image);
        TextView home_recyclerview_item_title = (TextView) helper.getView(R.id.home_recyclerview_item_title);
        TextView home_recyclerview_item_course = (TextView) helper.getView(R.id.home_recyclerview_item_course);
        TextView home_recyclerview_item_coursename = (TextView) helper.getView(R.id.home_recyclerview_item_coursename);
        TextView home_recyclerview_item_money = (TextView) helper.getView(R.id.home_recyclerview_item_money);
        LinearLayout home_recyclerview_linlayout_shipin = (LinearLayout) helper.getView(R.id.home_recyclerview_linlayout_shipin);
        LinearLayout home_recyclerview_linlayout_kecheng = (LinearLayout) helper.getView(R.id.home_recyclerview_linlayout_kecheng);
        TextView near_number = (TextView) helper.getView(R.id.near_number);
        TextView home_recyclerview_item_city = (TextView) helper.getView(R.id.home_recyclerview_item_city);
        TextView home_recyclerview_item_cityNumber = (TextView) helper.getView(R.id.home_recyclerview_item_cityNumber);
        ImageView zhizhirenzheng = (ImageView) helper.getView(R.id.home_recyclerview_item_huiyuan);
        ImageView start1 = (ImageView) helper.getView(R.id.start1);
        ImageView start2 = (ImageView) helper.getView(R.id.start2);
        ImageView start3 = (ImageView) helper.getView(R.id.start3);
        ImageView start4 = (ImageView) helper.getView(R.id.start4);
        ImageView start5 = (ImageView) helper.getView(R.id.start5);

        Glide.with(mContext)
                .load(item.getPicture())
                .placeholder(R.drawable.rec_seize_bitmao)
                .error(R.drawable.rec_seize_bitmao)
                .into(home_recyclerview_item_image);
        if (item.getDegreeId().equals("1")) //1老师 2机构
            home_recyclerview_item_title.setText(item.getUsername());
        else
            home_recyclerview_item_title.setText(item.getName());
        home_recyclerview_item_coursename.setText(item.getCatagoryName());
        if (item.getQcAuth() == 2) {

            zhizhirenzheng.setVisibility(View.VISIBLE);
        } else {
            zhizhirenzheng.setVisibility(View.GONE);
        }
        if (item.getMinVipPrice() == 0.0) {
            home_recyclerview_item_money.setVisibility(View.GONE);
        } else {
            home_recyclerview_item_money.setVisibility(View.VISIBLE);
            home_recyclerview_item_money.setText("￥" + item.getMinVipPrice() + "");
        }

        if (item.getCommentCount() <= 0) {
            near_number.setVisibility(View.GONE);
        } else {
            near_number.setVisibility(View.VISIBLE);
            near_number.setText(item.getCommentCount() + "条");
        }
        home_recyclerview_item_city.setText(item.getAreasName());

        //米转公里
        int range = item.getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (range < 1000)
            home_recyclerview_item_cityNumber.setText(range + "m");
        else
            home_recyclerview_item_cityNumber.setText(dis + "km");

        if (item.getVideoLesson().size() <= 0) {
            home_recyclerview_linlayout_shipin.setVisibility(View.GONE);
        } else {
            home_recyclerview_linlayout_shipin.setVisibility(View.VISIBLE);
        }
        if (item.getLessons().size() <= 0) {
            home_recyclerview_linlayout_kecheng.setVisibility(View.GONE);
        } else {
            home_recyclerview_linlayout_kecheng.setVisibility(View.VISIBLE);
            home_recyclerview_item_course.setText(item.getLessons().get(0).getName());
        }
        switch ((int) item.getAvgComment()) {
            case 0:
                start1.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 1:
                start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 2:
                start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 3:
                start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 4:
                start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 5:
                start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                start5.setImageResource(R.drawable.home_recyler_item_pingjia);
                break;
        }

    }
}
