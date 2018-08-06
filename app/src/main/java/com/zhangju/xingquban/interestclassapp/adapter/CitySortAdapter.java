package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;
import com.zhangju.xingquban.interestclassapp.util.PinyinUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//全部城市Adapter

public class CitySortAdapter
        extends BaseRecycleViewAdapter
        implements Filterable {


    private int CITY_ALL  = 0X11;
    private int CITY_HEAD = 0X22;

    private View                          viewHead;
    private List<CityNameBean.AaDataBean> mData;
    private List<CityNameBean.AaDataBean> mFilterList;

    public CitySortAdapter(Context c, List<CityNameBean.AaDataBean> mData, View viewHead) {
        super(c);
        this.mData = mData;
        this.mFilterList = mData;
        this.viewHead = viewHead;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CITY_HEAD) {
            return new HeadViewolder(viewHead);
        } else if (viewType == CITY_ALL) {
            return new CityAllViewHolder(resIdToView(parent, R.layout.item));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof HeadViewolder) {
                HeadViewolder headViewolder = (HeadViewolder) holder;
                headViewolder.onBind();
            } else if (holder instanceof CityAllViewHolder) {
                CityAllViewHolder cityAllViewHolder = (CityAllViewHolder) holder;
                cityAllViewHolder.onBind();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFilterList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return CITY_HEAD;
        }
        return CITY_ALL;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            //执行过滤操作
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    mFilterList = mData;
                } else {
                    List<CityNameBean.AaDataBean> filteredList = new ArrayList<>();
                    for (CityNameBean.AaDataBean data : mData) {
                        //这里根据需求，添加匹配规则
                        if (data.getName().contains(charString) ||
                                PinyinUtils.getFirstSpell(data.getName()).contains(charString)) {
                            filteredList.add(data);
                        }
                    }
                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            //把过滤后的值返回出来
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (List<CityNameBean.AaDataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    //head
    class HeadViewolder
            extends RecyclerView.ViewHolder {
        public HeadViewolder(View itemView) {
            super(itemView);
        }

        void onBind() {
        }
    }

    //city
    class CityAllViewHolder
            extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.tag)
        TextView tvTag;
        @BindView(R.id.name)
        TextView tvName;

        public CityAllViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition() - 1;
            int section = getSectionForPosition(pos);

            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (pos == getPositionForSection(section)) {
                tvTag.setVisibility(View.VISIBLE);
                tvTag.setText(mData.get(pos).getAddrShortPinyin());
            } else {
                tvTag.setVisibility(View.GONE);
            }

            tvName.setText(mFilterList.get(pos).getName());
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cityPos.getPos(pos);
                }
            });
        }
    }
    //位置数据回调

    CityPos cityPos;

    public void setCityPos(CityPos cityPos) {
        this.cityPos = cityPos;
    }

    public interface CityPos {
        void getPos(int postion);
    }


    /**
     * 提供给Activity刷新数据
     *
     * @param list
     */
    public void updateList(List<CityNameBean.AaDataBean> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getAddrShortPinyin().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount() - 1; i++) {
            String sortStr = mData.get(i).getAddrShortPinyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public List<CityNameBean.AaDataBean> getFilterList() {
        return mFilterList;
    }
}
