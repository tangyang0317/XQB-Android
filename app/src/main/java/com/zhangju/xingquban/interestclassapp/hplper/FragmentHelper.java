package com.zhangju.xingquban.interestclassapp.hplper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */
public class FragmentHelper {
    public static void replaceFragment(FragmentManager manager, Fragment fragment, int layoutId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layoutId, fragment);
        transaction.commitAllowingStateLoss();
        manager.executePendingTransactions();
    }

    public static void switchFragment(FragmentManager manager, List<Fragment> list, int
            index, int layoutId) {
        FragmentTransaction transaction = manager.beginTransaction();
        //让当前显示的碎片进行隐藏
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisible()) {
                transaction.hide(list.get(i));
            }
        }
        Fragment toFragment = list.get(index);
        if (toFragment.isAdded()) {
            transaction.show(toFragment);
        } else {
            transaction.add(layoutId, toFragment);
        }
        transaction.commitAllowingStateLoss();
        manager.executePendingTransactions();
    }
}
