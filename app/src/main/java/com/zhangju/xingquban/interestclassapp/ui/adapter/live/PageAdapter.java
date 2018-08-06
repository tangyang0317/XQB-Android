package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/18.
 *
 */

public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<String> al;
    private ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager fm, ArrayList<String>  al, ArrayList<Fragment>  fragments) {
        super(fm);
        this.al = al;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return al.get(position);
    }
}
