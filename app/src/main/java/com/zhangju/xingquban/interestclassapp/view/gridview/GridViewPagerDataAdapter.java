package com.zhangju.xingquban.interestclassapp.view.gridview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class GridViewPagerDataAdapter<T> {
    public List listAll;
    public int  rowInOnePage;
    public int  columnInOnePage;

    public GridViewPagerDataAdapter(List<T> listAll, int rowInOnePage, int columnInOnePage) {
        this.listAll = listAll;
        this.rowInOnePage = rowInOnePage;
        this.columnInOnePage = columnInOnePage;
    }

    public abstract BaseAdapter getGridViewAdapter(List<T> currentList, int pageIndex);

    /**
     * 条目点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     * @param pageIndex
     */
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id, int pageIndex);

    /**
     * 获取总页数
     * @param pageSum
     */
    public abstract void getPageSum(int pageSum);
}
