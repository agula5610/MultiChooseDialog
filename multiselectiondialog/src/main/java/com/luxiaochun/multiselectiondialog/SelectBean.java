package com.luxiaochun.multiselectiondialog;

import android.support.v4.app.FragmentActivity;

import com.luxiaochun.multiselectiondialog.base.Node;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2018-08-21 09:51
 */
public class SelectBean implements Serializable {
    //必填
    private FragmentActivity context;
    private String title;                               //标题
    private List<Node> mDatas;                          //数据流
    //选填
    private int titleColor;                             //标题颜色
    private int themeColor;                       //主题颜色，包括单选、多选框的颜色，按钮颜色等
    private DialogType type;                            //类型
    private boolean canceledOnTouchOutside = true;      //是否点击外侧可取消
    private int limited;                                //排序限制

    public FragmentActivity getContext() {
        return context;
    }

    public void setContext(FragmentActivity context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Node> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Node> mDatas) {
        this.mDatas = mDatas;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(int themeColor) {
        this.themeColor = themeColor;
    }

    public DialogType getType() {
        return type;
    }

    public void setType(DialogType type) {
        this.type = type;
    }

    public boolean isCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }
}
