package com.zhangju.xingquban.refactoring.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.Entity
 * @FileName CategoryBean
 * @Author tangyang
 * @DATE 2018/8/9
 **/
@DatabaseTable(tableName = "category")
public class CategoryBean implements Serializable {

    @DatabaseField(columnName = "id", dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(columnName = "removed", dataType = DataType.INTEGER)
    private int removed;
    @DatabaseField(columnName = "name", dataType = DataType.STRING)
    private String name;
    @DatabaseField(columnName = "parentsId", dataType = DataType.STRING)
    private String parentsId;
    @DatabaseField(columnName = "summary", dataType = DataType.STRING)
    private String summary;
    @DatabaseField(columnName = "published", dataType = DataType.BOOLEAN)
    private boolean published;
    @DatabaseField(columnName = "display", dataType = DataType.INTEGER)
    private int display;
    @DatabaseField(columnName = "hots", dataType = DataType.INTEGER)
    private int hots;
    @DatabaseField(columnName = "istrue", dataType = DataType.INTEGER)
    private int istrue;
    @DatabaseField(columnName = "catagoriesPicture", dataType = DataType.STRING)
    private String catagoriesPicture;
    @DatabaseField(columnName = "showHome", dataType = DataType.INTEGER)
    private int showHome;

    private List<CategoryBean> childs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getHots() {
        return hots;
    }

    public void setHots(int hots) {
        this.hots = hots;
    }

    public int getIstrue() {
        return istrue;
    }

    public void setIstrue(int istrue) {
        this.istrue = istrue;
    }

    public String getCatagoriesPicture() {
        return catagoriesPicture;
    }

    public void setCatagoriesPicture(String catagoriesPicture) {
        this.catagoriesPicture = catagoriesPicture;
    }

    public int getShowHome() {
        return showHome;
    }

    public void setShowHome(int showHome) {
        this.showHome = showHome;
    }

    public List<CategoryBean> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryBean> childs) {
        this.childs = childs;
    }
}
