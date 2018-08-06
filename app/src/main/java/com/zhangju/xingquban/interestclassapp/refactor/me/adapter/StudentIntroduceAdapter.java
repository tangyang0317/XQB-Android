package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.StudentIntroduce;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/25.
 * VIP商户--生源推荐adapter
 */

public class StudentIntroduceAdapter extends BaseRecycleViewAdapter {


    private int TYPE_IMAGE = 0x11;//图片
    private int TYPE_CONTENT = 0x22;//主体信息


    private List<StudentIntroduce> mStudentList;

    public StudentIntroduceAdapter(Context c, List<StudentIntroduce> mStudentList) {
        super(c);
        this.mStudentList = mStudentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMAGE) {
            return new StuHeadViewHolder(resIdToView(parent, R.layout.message_head_image));
        } else if (viewType == TYPE_CONTENT) {
            return new StuBodyViewHolder(resIdToView(parent, R.layout.item_meessge_info));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof StuBodyViewHolder) {
                StuBodyViewHolder stuBodyViewHolder = (StuBodyViewHolder) holder;
                stuBodyViewHolder.onBind();
            } else if (holder instanceof StuHeadViewHolder) {
                StuHeadViewHolder stuHeadViewHolder = (StuHeadViewHolder) holder;
                stuHeadViewHolder.onBind();
            }
        }

    }

    @Override
    public int getItemCount() {
        return mStudentList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_IMAGE;
        }
        return TYPE_CONTENT;
    }

    class StuBodyViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.find_message_title)
        TextView findMessageTitle;
        @BindView(R.id.find_message_describle)
        TextView findMessageDescrible;
        @BindView(R.id.find_message_sign)
        TextView findMessageSign;
        @BindView(R.id.find_message_time)
        TextView findMessageTime;
        @BindView(R.id.find_message_headpic)
        CustomRoundView findMessageHeadpic;
        @BindView(R.id.find_message_name)
        TextView findMessageName;
        @BindView(R.id.find_message_see_count)
        TextView findMessageSeeCount;
        @BindView(R.id.find_message_city)
        TextView findMessageCity;
        @BindView(R.id.platform_bottom)
        RelativeLayout platformBottom;
        @BindView(R.id.view_line)
        View viewLine;
        public StuBodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos=getLayoutPosition()-1;
            StudentIntroduce databean = mStudentList.get(pos);
            registerOnItemClickListener(pos,itemView);
            String userTime = databean.getAddUserTime()==null?"":databean.getAddUserTime();
            String title = databean.getTitle()==null?"":databean.getTitle();
            String cityName = databean.getCityName()==null?"":databean.getCityName();
            String content = databean.getContent()==null?"":databean.getContent();
            Integer click = databean.getClick();
            String catagoriesName = databean.getCatagoriesName()==null?"":databean.getCatagoriesName();


            findMessageTitle.setText(title);
            findMessageDescrible.setText(content);
            findMessageSign.setText(catagoriesName);
            findMessageTime.setText(userTime.substring(0,userTime.indexOf(" ")));
            findMessageCity.setText(cityName);
            findMessageSeeCount.setText(click+"");

            findMessageHeadpic.setVisibility(View.GONE);
            findMessageName.setVisibility(View.GONE);


        }
    }

    class StuHeadViewHolder extends RecyclerView.ViewHolder {
        int pos;

        public StuHeadViewHolder(View itemView) {
            super(itemView);
        }

        void onBind() {
            pos=getLayoutPosition();
        }
    }
}
