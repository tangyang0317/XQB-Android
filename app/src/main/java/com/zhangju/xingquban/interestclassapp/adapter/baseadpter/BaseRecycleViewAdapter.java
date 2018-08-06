package com.zhangju.xingquban.interestclassapp.adapter.baseadpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * function
 * Created by hqf on 2016/12/11.11:51
 * update by:
 */
public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter {
    public Context c;
    protected final int TYPE_INVAILED = -1;
    protected final int TYPE_EMPTYVIEW = 2;
    protected final int TYPE_NORMALVIEW = 3;
    public static final int TYPE_EMPTY = 0x002;
    public static final int TYPE_FAILED = 0x003;
    public static final int TYPE_IGNORE = 0x004;
    protected int loadEmpty = TYPE_IGNORE;


    public BaseRecycleViewAdapter(Context c) {
        this.c = c;
    }

    protected View resIdToView(ViewGroup viewGroup, int resId) {
        return LayoutInflater.from(c).inflate(resId, viewGroup, false);
    }

    protected boolean isFullSpanType(int type) {
        return false;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (isFullSpanType(adapter.getItemViewType(position))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        int type = getItemViewType(position);
        if (isFullSpanType(type)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
    }

    protected OnListItemClickListener onListItemClickListener;
    protected OnListItemLongClickListener onListItemLongClickListener;

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public void setOnListItemLongClickListener(OnListItemLongClickListener onListItemLongClickListener) {
        this.onListItemLongClickListener = onListItemLongClickListener;
    }

    public void registerOnItemClickListener(final int position, final View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onItemClickListener(position, itemView);
                }

            }
        });
    }

    public void registerOnItemLongClickListener(final int position, final View itemView) {
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onListItemLongClickListener != null) {
                    onListItemLongClickListener.onItemLongClickListener(position, itemView);
                }
                return true;
            }
        });
    }

   

    public void loadHelper(int loadState) {
        loadEmpty = loadState;
        notifyDataSetChanged();
    }
}
