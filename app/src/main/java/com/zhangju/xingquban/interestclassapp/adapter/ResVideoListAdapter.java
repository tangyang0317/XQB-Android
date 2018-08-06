package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/11.
 */

public class ResVideoListAdapter extends BaseRecycleViewAdapter {


    private List<ResDeatailTopBean.AaDataBean.TwoListBean> twoList;

    public ResVideoListAdapter(Context c, List<ResDeatailTopBean.AaDataBean.TwoListBean> twoList) {
        super(c);
        this.twoList = twoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(resIdToView(parent, R.layout.item_res_detail));
    }

    @Override


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mViewHolder mViewHolder= (ResVideoListAdapter.mViewHolder) holder;
        mViewHolder.onBind();

    }

    @Override
    public int getItemCount() {
        return twoList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_news_num)
        TextView tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView tvNewsTime;
        @BindView(R.id.img_news_back)
        ImageView imgNewsBack;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void onBind(){
            pos=getLayoutPosition();
            registerOnItemClickListener(pos,itemView);
            ResDeatailTopBean.AaDataBean.TwoListBean listBean = twoList.get(pos);
            if (listBean!=null){
                String title = listBean.getTitle()==null?"":listBean.getTitle();//标题
                String titlePicture = listBean.getTitlePicture()==null?"":listBean.getTitlePicture();//背景图
                int likes = listBean.getCollectionCounts();//关注
                String addUserTime = listBean.getAddUserTime()==null?"":listBean.getAddUserTime();//时间
                int commentCounts = listBean.getCommentCounts();//评论数
                int isLike = listBean.getIsLike();//0未  1关注
                setDrawableLeft(c,isLike==0?R.mipmap.home_data_spkc_x:R.mipmap.home_data_spkc_red_xh,tvNewsLoveNum);
                if (!addUserTime.isEmpty()){
                    String substring = addUserTime.substring(0, addUserTime.indexOf(" "));
                    tvNewsTime.setText(substring);
                }
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imgNewsBack);
            }
        }
    }
    //setDrawble
    public void setDrawableLeft(Context context, int resId, TextView textView) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
