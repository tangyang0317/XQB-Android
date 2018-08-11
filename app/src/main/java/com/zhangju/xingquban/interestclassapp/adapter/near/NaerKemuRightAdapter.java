package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.refactoring.entity.CategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/10/14.
 */

public class NaerKemuRightAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryBean> data;
    private LayoutInflater mlayoutInflater;
    private int flag = 0;

    public NaerKemuRightAdapter(Context mcontext, List<CategoryBean> mdata) {
        this.context = mcontext;
        this.data = mdata;
        mlayoutInflater = LayoutInflater.from(mcontext);
    }

    public void selectnum(int mflag) {
        this.flag = mflag;
        notifyDataSetChanged();
    }

    public List<CategoryBean> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mlayoutInflater.inflate(R.layout.popup_text_item_red, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leibieText.setText(data.get(position).getName());
        if (position == flag) {
            viewHolder.selectLine.setBackgroundColor(context.getResources().getColor(R.color.color_main));
            viewHolder.leibieText.setTextColor(context.getResources().getColor(R.color.color_main));
        } else {
            viewHolder.selectLine.setBackgroundColor(context.getResources().getColor(R.color.color_tab_text));
            viewHolder.leibieText.setTextColor(context.getResources().getColor(R.color.color_tab_text));
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.leibie_text)
        TextView leibieText;
        @BindView(R.id.select_line)
        View selectLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
