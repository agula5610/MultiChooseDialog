package com.luxiaochun.multiselectiondialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnDialogListener;

import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2018-08-21 09:38
 */
public class SelectDialogManager {
    public static final String TAG = SelectDialogManager.class.getSimpleName();
    private SelectBean bean;
    private OnDialogListener onClickListener;

    private SelectDialogManager(Builder builder) {
        bean = new SelectBean();
        bean.setmActivity(builder.getActivity());
        bean.setTitle(builder.getTitle());
        bean.setTitleColor(builder.getTitleColor());
        bean.setmDatas(builder.getDatas());
        bean.setItemColor(builder.getItemColor());
        bean.setmThemeColor(builder.getmThemeColor());
        bean.setType(builder.getType());
        bean.setLimited(builder.getLimited());
        bean.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        onClickListener = builder.getOnClickListener();
    }

    /**
     * 显示Dialog
     */
    public void show() {
        if (bean != null && bean.getmActivity() != null && !bean.getmActivity().isFinishing()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(TAG, bean);
            MultiSelectionDialogFragment fragment = MultiSelectionDialogFragment
                    .newInstance(bundle);
            if (onClickListener != null) {
                fragment.setOnClickListener(onClickListener);
            }
            fragment.show(((FragmentActivity) bean.getmActivity()).getSupportFragmentManager(), TAG);
        }
    }

    public static class Builder {
        //必填
        private Activity mActivity;
        private String title;                               //标题
        private List<Node> mDatas;                          //数据流
        //选填
        private int titleColor = -1;                //标题颜色
        private int itemColor = -1;                  //项目颜色
        private DialogType type;                            //类型
        private boolean canceledOnTouchOutside = true;      //是否点击外侧可取消
        private int mThemeColor = -1;               //主题颜色
        private int limited = 9;                            //排序限制
        private OnDialogListener onClickListener;

        public Activity getActivity() {
            return mActivity;
        }

        public Builder setActivity(Activity mActivity) {
            this.mActivity = mActivity;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public int getTitleColor() {
            return titleColor;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public int getItemColor() {
            return itemColor;
        }

        public Builder setItemColor(int itemColor) {
            this.itemColor = itemColor;
            return this;
        }

        public DialogType getType() {
            return type;
        }

        public Builder setType(DialogType type) {
            this.type = type;
            return this;
        }

        public List<Node> getDatas() {
            return mDatas;
        }

        public Builder setDatas(List<Node> mDatas) {
            this.mDatas = mDatas;
            return this;
        }

        public boolean isCanceledOnTouchOutside() {
            return canceledOnTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public OnDialogListener getOnClickListener() {
            return onClickListener;
        }

        public Builder setOnClickListener(OnDialogListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public int getLimited() {
            return limited;
        }

        public Builder setLimited(int limited) {
            this.limited = limited;
            return this;
        }

        public int getmThemeColor() {
            return mThemeColor;
        }

        public Builder setmThemeColor(int mThemeColor) {
            this.mThemeColor = mThemeColor;
            return this;
        }

        public SelectDialogManager build() {
            if (getActivity() == null) {
                throw new NullPointerException("必要参数不能为空");
            }
            return new SelectDialogManager(this);
        }
    }
}
