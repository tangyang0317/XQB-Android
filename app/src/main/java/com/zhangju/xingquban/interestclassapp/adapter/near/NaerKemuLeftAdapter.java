package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhangju.xingquban.R.id.select_line;

/**
 * Created by zsl on 2017/10/14.
 */

public class NaerKemuLeftAdapter extends BaseAdapter {
    private Context context;
    private NearSubjectBean data ;
    private LayoutInflater mlayoutInflater;
    private int flag;
    public NaerKemuLeftAdapter(Context mcontext, NearSubjectBean mdata) {
        this.context = mcontext;
        this.data = mdata;
        mlayoutInflater = LayoutInflater.from(mcontext);
    }
    public void selectnum(int mflag){
        this.flag=mflag;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (data!=null&&data.getAaData()!=null){
            return data.getAaData().size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return data.getAaData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = mlayoutInflater.inflate(R.layout.popup_text_item_red, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leibieText.setText(data.getAaData().get(position).getName());
        viewHolder.selectLine.setVisibility(View.GONE);
        if(flag==position){

        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.leibie_text)
        TextView leibieText;
        @BindView(select_line)
        View selectLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
