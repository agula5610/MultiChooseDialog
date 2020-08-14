package com.luxiaochun.multiselectiondialog.choicedialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.adapter.MultiAdapter;
import com.luxiaochun.multiselectiondialog.adapter.MultiOrderAdapter;
import com.luxiaochun.multiselectiondialog.adapter.SingleAdapter;
import com.luxiaochun.multiselectiondialog.adapter.TreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.utils.DrawableUtil;
import com.luxiaochun.multiselectiondialog.utils.MultiDialogUtils;
import com.luxiaochun.multiselectiondialog.utils.ScreenUtil;

import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2020-08-12 09:00
 */
public class ChoiceDialog extends Dialog implements View.OnClickListener {
    private Context context;

    private int mDefaultTitleColor = R.color.black;
    private int mDefaultThemeColor = R.color.dialog_btn_bgc;
    private OnDialogListener onClickListener;

    private TextView tv_title;
    private RecyclerView recyclerview;
    private Button dialog_cancel;
    private Button dialog_confirm;

    private SelectBean bean;
    private TreeRecyclerAdapter mAdapter;

    private ChoiceDialog(Context context) {
        super(context, R.style.dialog_full);
        this.context = context;
    }

    public interface OnDialogListener {
        void onConfirm(List<Node> list);
    }

    public void setBean(SelectBean bean) {
        this.bean = bean;
    }

    public void setOnClickListener(OnDialogListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.bottom_choice_dailog,
                null);
        int needHeight = (int) (ScreenUtil.getScreenHeigth((Activity) context) * 0.48);
        setContentView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, needHeight));

        // 设置window的高度和位置
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, needHeight);
        setCanceledOnTouchOutside(false);

        initView(view);
        if (bean != null) {
            initData();
            initTheme();
        }
    }

    private void initView(View view) {
        tv_title = view.findViewById(R.id.bottom_dialog_title);
        recyclerview = view.findViewById(R.id.bottom_dialog_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
//        recyclerview.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        dialog_cancel = view.findViewById(R.id.dialog_cancel);
        dialog_confirm = view.findViewById(R.id.dialog_confirm);
    }

    private void initData() {
        //标题
        tv_title.setText(bean.getTitle());
        List<Node> mDatas = bean.getmDatas();
        DialogType type = bean.getType();
        if (DialogType.SINGLEDEGREE_SINGLECHOOSE.equals(type)) {
            mAdapter = new SingleAdapter(mDatas, bean.getThemeColor() == -1 ? mDefaultThemeColor : bean.getThemeColor());
        } else if (DialogType.SINGLEDEGREE_MULTICHOOSE.equals(type)) {
            mAdapter = new MultiAdapter(mDatas, bean.getThemeColor() == -1 ? mDefaultThemeColor : bean.getThemeColor());
        } else if (DialogType.SINGLEDEGREE_ORDER.equals(type)) {
            mAdapter = new MultiOrderAdapter(mDatas, bean.getLimited(), bean.getThemeColor() == -1 ? mDefaultThemeColor : bean.getThemeColor());
        }
        recyclerview.setAdapter(mAdapter);
        initClickEvents();
    }

    /**
     * 初始化主题色
     */
    private void initTheme() {
        final int titleColor = bean.getTitleColor();
        final int themeColor = bean.getThemeColor();
        if (-1 == titleColor) {
            tv_title.setTextColor(getContext().getResources().getColor(mDefaultTitleColor));
        } else {
            tv_title.setTextColor(getContext().getResources().getColor(titleColor));
        }
        if (-1 != themeColor) {
            dialog_confirm.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, context), getContext().getResources().getColor(themeColor)));
        }
    }


    private void initClickEvents() {
        dialog_cancel.setOnClickListener(this);
        dialog_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_confirm) {
            if (onClickListener != null) {
                dismiss();
                onClickListener.onConfirm(mAdapter.getCheckedNodeList());
            }
        } else if (i == R.id.dialog_cancel) {
            if (onClickListener != null) {
                dismiss();
            }
        }
    }

    public static class Builder {

        private Context context;
        private String title;                               //标题
        private List<Node> mDatas;                          //数据流
        //选填
        private int titleColor = -1;                        //标题颜色
        private int themeColor = -1;                        //主题颜色，包括单选、多选框的颜色，按钮颜色等
        private DialogType type = DialogType.SINGLEDEGREE_SINGLECHOOSE;   //类型(默认是单选)
        private boolean canceledOnTouchOutside = true;      //是否点击外侧可取消
        private int limited;                                //排序限制
        private OnDialogListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public ChoiceDialog.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ChoiceDialog.Builder setDatas(List<Node> mDatas) {
            this.mDatas = mDatas;
            return this;
        }

        public ChoiceDialog.Builder setTitleColor(@ColorRes int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public ChoiceDialog.Builder setThemeColor(@ColorRes int themeColor) {
            this.themeColor = themeColor;
            return this;
        }

        public ChoiceDialog.Builder setType(DialogType type) {
            this.type = type;
            return this;
        }

        public ChoiceDialog.Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public ChoiceDialog.Builder setLimited(int limited) {
            this.limited = limited;
            return this;
        }

        public Builder setOnClickListener(OnDialogListener listener) {
            this.listener = listener;
            return this;
        }

        public Context getContext() {
            return context;
        }

        public String getTitle() {
            return title;
        }

        public List<Node> getDatas() {
            return mDatas;
        }

        public int getTitleColor() {
            return titleColor;
        }

        public int getThemeColor() {
            return themeColor;
        }

        public DialogType getType() {
            return type;
        }

        public boolean isCanceledOnTouchOutside() {
            return canceledOnTouchOutside;
        }

        public int getLimited() {
            return limited;
        }

        public ChoiceDialog build() {
            ChoiceDialog choiceDialog = new ChoiceDialog(context);
            SelectBean bean = new SelectBean();
            bean.setContext(getContext());
            bean.setTitle(getTitle());
            bean.setType(getType());
            bean.setTitleColor(getTitleColor());
            bean.setThemeColor(getThemeColor());
            bean.setmDatas(getDatas());
            bean.setLimited(getLimited());
            bean.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
            choiceDialog.setBean(bean);
            choiceDialog.setOnClickListener(listener);
            return choiceDialog;
        }
    }
}