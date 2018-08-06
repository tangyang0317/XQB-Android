package com.zhangju.xingquban.interestclassapp.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/23.
 */
@ContentView(R.layout.act_test)
public class TestActivity extends FastActivity{
    @Bind(R.id.list)
    RecyclerView mList;

    @Override
    protected void alreadyPrepared(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("张三");
        }
        mList.setAdapter(new MyAdapter(this,list));
    }

    class MyAdapter extends FastAdapterForRecycler<String>{

        public MyAdapter(Context context) {
            super(context,android.R.layout.simple_list_item_2);
        }

        public MyAdapter(Context context, List<String> data) {
            super(context,android.R.layout.simple_list_item_1, data);
        }

        @Override
        public void binding(final int position, String data, CommonViewHolder holder){
            holder.setText(android.R.id.text1,String.valueOf(position));
            holder.setText(android.R.id.text2,data);
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("position:"+position+" clicked");
                }
            });
        }
    }
}
