package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseCity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sgfb on 2017/11/22.
 * 市县联动适配器
 */
public class ApplyToMemberLocationDetailAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private OnAreaSelectedCallback mCallback;
    private List<Item> mData=new ArrayList<>();
    private Map<ResponseCity,List<Item>> mCityToAreas=new HashMap<>();

    public ApplyToMemberLocationDetailAdapter(OnAreaSelectedCallback callback){
        mCallback=callback;
    }

    public void setData(List<ResponseCity> list){
        mData.clear();
        if(list!=null&&!list.isEmpty()){
            for(final ResponseCity city:list){
                mData.add(new Item() {
                    @Override
                    public int getType() {
                        return 0;
                    }

                    @Override
                    public Object getData() {
                        return city;
                    }
                });
                if(city.areas!=null)
                    for(final ResponseCity.Area area:city.areas){
                        area.fromCity=city;
                        Item item=new Item() {
                            @Override
                            public int getType() {
                                return 1;
                            }

                            @Override
                            public Object getData() {
                                return area;
                            }
                        };
                        List<Item> childItems=mCityToAreas.get(city);
                        if(childItems==null) childItems=new ArrayList<>();
                        childItems.add(item);
                        mCityToAreas.put(city,childItems);
                    }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new CommonViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType==0?
                R.layout.item_apply_member_location:R.layout.item_apply_member_location_detail,
                parent,false));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder,int position) {
        if(getItemViewType(position)==0){
            final int fPosition=position;
            final ResponseCity city= (ResponseCity) mData.get(position).getData();
            holder.setText(R.id.name,city.name);
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean showChildren=city.showChildren;

                    if(!showChildren) mData.addAll(fPosition+1,mCityToAreas.get(city));
                    else mData.removeAll(mCityToAreas.get(city));
                    city.showChildren=!showChildren;
                    notifyDataSetChanged();
                }
            });
        }
        else {
            final ResponseCity.Area area= (ResponseCity.Area) mData.get(position).getData();
            holder.setText(R.id.name,area.name);
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCallback!=null) mCallback.onSelectedArea(area);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnAreaSelectedCallback{
        void onSelectedArea(ResponseCity.Area city);
    }

    public interface Item{
        int getType();
        Object getData();
    }
}