package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFansReward;

import java.util.Locale;

/**
 * Created by sgfb on 2017/11/8.
 * 粉丝贡献榜适配器
 */
public class FansRankAdapter extends FastAdapterForRecycler<ResponseFansReward>{

    public FansRankAdapter(Context context) {
        super(context, R.layout.item_fans_rank);
    }

    @Override
    public void binding(int position,ResponseFansReward data, CommonViewHolder holder){
        String giveCoin=String.format(Locale.getDefault(),"贡献%d兴趣币",data.stdCoin);
        holder.setText(R.id.name,data.name);
        holder.setText(R.id.rank,Integer.toString(position+1));
        holder.setText(R.id.coin, Utils.getTextSomeOtherColor(2,3,giveCoin,mContext.getResources().getColor(R.color.EF4E4C)));
        ((ImageView)holder.getView(R.id.sex)).setImageResource(data.gender==2?R.drawable.home_teacher_gender_female:R.drawable.home_teacher_gender_male);
        Glide.with(mContext).load(data.icon).into((ImageView)holder.getView(R.id.avatar));
    }
}
