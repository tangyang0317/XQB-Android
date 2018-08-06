package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.hplper.RoundImageView;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/1.
 */
//直播科目选择Adapter

public class LiveSubAdapter extends BaseRecycleViewAdapter {


    private List<NearSubjectBean.AaDataBean> subjectList;
    private boolean type;

    private int selectedPos = -1;
    private int secondPos = -1;


    public void setFirstSelected(int selectedPos, int secondPos) {
        this.selectedPos = selectedPos;
        this.secondPos = secondPos;
        notifyDataSetChanged();
    }


    public LiveSubAdapter(Context c, List<NearSubjectBean.AaDataBean> subjectList, boolean type) {
        super(c);
        this.subjectList = subjectList;
        this.type = type;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubjectViewHolder(resIdToView(parent, R.layout.live_subject_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubjectViewHolder subjectViewHolder = (SubjectViewHolder) holder;
        subjectViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.image_title_icon)
        RoundImageView imageTitleIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.sub_item_gridview)
        RecyclerView subItemGridview;
        @BindView(R.id.tv_select)
        TextView tvSelect;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

            if (!type ) {
                tvSelect.setVisibility(View.GONE);
            }

            final NearSubjectBean.AaDataBean itemSubList = subjectList.get(pos);
            String name = itemSubList.getName() == null ? "" : itemSubList.getName();//标题
            String picture = itemSubList.getCatagoriesPicture() == null ? "" : itemSubList.getCatagoriesPicture();//图标


            final boolean isSelected = itemSubList.isSelected();//是否选中状态

            Glide.with(c).load(picture).placeholder(R.drawable.app_icon).into(imageTitleIcon);
            tvTitle.setText(name);

            final LiveSubItemAdapter liveSubItemAdapter = new LiveSubItemAdapter(c, itemSubList);
            ScrollGridManager scrollGridManager = new ScrollGridManager(c, 4);
            scrollGridManager.setScrollEnabled(false);
            subItemGridview.setLayoutManager(scrollGridManager);
            subItemGridview.setAdapter(liveSubItemAdapter);

            liveSubItemAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                @Override
                public void onItemClickListener(int position, View v) {

                    String name1 = subjectList.get(pos).getName() == null ? "" : subjectList.get(pos).getName();//一级名字
                    String id1 = subjectList.get(pos).getId() == null ? "" : subjectList.get(pos).getId();//一级id

                    List<NearSubjectBean.AaDataBean.ChildsBean> childList = itemSubList.getChilds();//子集合

                    String name2 = childList.get(position).getName() == null ? "" : childList.get(position).getName();//二级名字
                    String id2 = childList.get(position).getId() == null ? "" : childList.get(position).getId();//二级id


                    boolean selected = childList.get(position).isSelected();

                    if (!type ){
                        for (int i = 0; i < subjectList.size(); i++) {
                            List<NearSubjectBean.AaDataBean.ChildsBean> childsBeanList = subjectList.get(i).getChilds();
                            for (NearSubjectBean.AaDataBean.ChildsBean bean : childsBeanList) {
                                bean.setSelected(false);
                            }
                        }
                    }
                    childList.get(position).setSelected(selected == true ? false : true);


                    liveSubItemAdapter.notifyItemChanged(position);


                    secondPosition.getSecondPosition(pos, position);
                    subData.getSubData(name1, id1, name2, id2);


                }
            });

            //全选单选判断
            tvSelect.setText(isSelected ? "已选" : "全选");
            tvSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<NearSubjectBean.AaDataBean.ChildsBean> childs = itemSubList.getChilds();

                    if (isSelected) {
                        for (NearSubjectBean.AaDataBean.ChildsBean child : childs) {
                            child.setSelected(false);
                        }
                        itemSubList.setSelected(false);

                    } else {
                        for (NearSubjectBean.AaDataBean.ChildsBean child : childs) {
                            child.setSelected(true);
                        }
                        itemSubList.setSelected(true);
                    }
                    notifyItemChanged(pos);
                }
            });
        }
    }


    //位置刷新处理
    private SecondPosition secondPosition;

    public void setSecondPosition(SecondPosition secondPosition) {
        this.secondPosition = secondPosition;
    }

    public interface SecondPosition {
        void getSecondPosition(int position, int secondPos);
    }

    //数据获取
    private SubData subData;

    public void setSubData(SubData subData) {
        this.subData = subData;
    }

    public interface SubData {
        void getSubData(String firstName, String firstId, String secondName, String secondId);
    }


}
