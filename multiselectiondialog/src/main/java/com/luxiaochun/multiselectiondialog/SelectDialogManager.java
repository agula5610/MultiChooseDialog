package com.luxiaochun.multiselectiondialog;

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
        bean.setContext(builder.getContext());
        bean.setTitle(builder.getTitle());
        bean.setTitleColor(builder.getTitleColor());
        bean.setmDatas(builder.getDatas());
        bean.setType(builder.getType());
        bean.setLimited(builder.getLimited());
        bean.setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());
        onClickListener = builder.getOnClickListener();
    }

    /**
     * 显示Dialog
     */
    public void show() {
        if (bean != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(TAG, bean);
            ChooseDialogFragment fragment = ChooseDialogFragment
                    .newInstance(bundle);
            if (onClickListener != null) {
                fragment.setOnClickListener(onClickListener);
            }
            fragment.show(bean.getContext().getSupportFragmentManager(), TAG);
        }
    }

    public static class Builder {
        //必填
        private FragmentActivity context;  //
        private String title;                               //标题
        private List<Node> mDatas;                          //数据流
        //选填
        private int titleColor = -1;                        //标题颜色
        private DialogType type = DialogType.SINGLEDEGREE_SINGLECHOOSE; //类型(默认单级单选)
        private boolean canceledOnTouchOutside = true;      //是否点击外侧可取消
        private int limited = 9;                            //排序限制(默认9个)
        private OnDialogListener onClickListener;

        public FragmentActivity getContext() {
            return context;
        }

        public Builder setContext(FragmentActivity context) {
            this.context = context;
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

        public SelectDialogManager build() {
            return new SelectDialogManager(this);
        }
    }
}
