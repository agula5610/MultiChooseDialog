package com.luxiaochun.multiselectiondialog;

import android.app.Activity;

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
    private Activity mActivity;
    private String title;                               //标题
    private List<Node> mDatas;                          //数据流
    //选填
    private int titleColor;                             //标题颜色
    private int itemColor;                              //项目颜色
    private DialogType type;                            //类型
    private boolean canceledOnTouchOutside = true;      //是否点击外侧可取消
    private int mThemeColor;                            //主题颜色
    private int limited;                                //排序限制

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
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

    public int getItemColor() {
        return itemColor;
    }

    public void setItemColor(int itemColor) {
        this.itemColor = itemColor;
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

    public int getmThemeColor() {
        return mThemeColor;
    }

    public void setmThemeColor(int mThemeColor) {
        this.mThemeColor = mThemeColor;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }
}
