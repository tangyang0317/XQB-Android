package com.zhangju.xingquban.refactoring.dblite;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;
import com.zhangju.xingquban.refactoring.entity.CategoryBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.dblite
 * @FileName CategoryDao
 * @Author tangyang
 * @DATE 2018/8/9
 **/
public class CategoryDao extends BaseDao<CategoryBean> {
    /**
     * @param context using this to get instance of  DatabaseHelper
     */
    public CategoryDao(Context context) {
        super(DataBaseHelper.getHelper(context));
    }

    /**
     * 新增一条不存在的数据
     *
     * @param categoryBean
     */
    public void addCategory(CategoryBean categoryBean) {
        try {
            daoOpe.create(categoryBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     * 根据Id查询单个类别
     * @param id
     * @return
     */
    public CategoryBean queryCategoryById(int id) {
        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        try {
            return (CategoryBean) queryBuilder.where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 查询一级类别
     * @return
     */
    public List<CategoryBean> queryLevelOneAll() {
        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        List<CategoryBean> categoryBeans = null;
        try {
            categoryBeans = queryBuilder.where().isNull("parentsId").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBeans;
    }


    /***
     * 根据父Id查询二级类别
     * @param id
     * @return
     */
    public List<CategoryBean> queryLevelTowAll(int id) {
        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        List<CategoryBean> categoryBeans = null;
        try {
            categoryBeans = queryBuilder.where().eq("parentsId", String.valueOf(id)).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBeans;
    }
}
