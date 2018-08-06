package com.zhangju.xingquban.interestclassapp.adapter.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangju.xingquban.R;


/**
 * Created by ydw on 2017/10/31.
 */

public class SubListViewAdapter extends BaseAdapter {
    private String[][] sub_items;
    private Context context;
    private int root_position;
    private LayoutInflater inflater;

    public SubListViewAdapter(Context context, String[][] sub_items,
                              int position) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.sub_items = sub_items;
        this.root_position = position;
    }

    @Override
    public int getCount() {

        return sub_items[root_position].length;
    }

    @Override
    public Object getItem(int position) {

        return sub_items[root_position][position];
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = (View) inflater.inflate(R.layout.root_listview_item,
                    parent, false);
            holder.item_text = (TextView) convertView
                    .findViewById(R.id.item_name_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_text.setText(sub_items[root_position][position]);
        return convertView;
    }
    class ViewHolder{
        TextView item_text;
    }
}
