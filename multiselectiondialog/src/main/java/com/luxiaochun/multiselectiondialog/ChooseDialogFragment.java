package com.luxiaochun.multiselectiondialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.adapter.MultiAdapter;
import com.luxiaochun.multiselectiondialog.adapter.MultiOrderAdapter;
import com.luxiaochun.multiselectiondialog.adapter.SingleAdapter;
import com.luxiaochun.multiselectiondialog.adapter.TreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnDialogListener;
import com.luxiaochun.multiselectiondialog.utils.DrawableUtil;
import com.luxiaochun.multiselectiondialog.utils.MultiDialogUtils;

import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2018-08-21 09:34
 */
public class ChooseDialogFragment extends AppCompatDialogFragment implements View.OnClickListener {
    private int mDefaultTitleColor = R.color.black;
    private int mDefaultThemeColor = R.color.luxiaochun_themeColor;
    private SelectBean bean;
    private OnDialogListener onClickListener;

    private TextView tv_title;
    private RecyclerView recyclerview;
    private LinearLayout ll_onclick;
    private Button btn_cancel;
    private Button btn_confirm;

    private Activity mActivity;
    private TreeRecyclerAdapter mAdapter;

    private boolean isViewSet;  //view是否已经加载好
    DisplayMetrics displayMetrics;
    Window dialogWindow;


    public static ChooseDialogFragment newInstance(Bundle args) {
        ChooseDialogFragment fragment = new ChooseDialogFragment();
        if (args != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    public void setOnClickListener(OnDialogListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MultiSelectionDialog);
        mActivity = getActivity();
        displayMetrics = getContext().getResources().getDisplayMetrics();

        bean = (SelectBean) getArguments().getSerializable(SelectDialogManager.TAG);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.luxiaochun_selection_dialog, container);
        initView(view);
        initTheme();
        initData();
        return view;
    }

    private void initView(final View view) {
        tv_title = view.findViewById(R.id.tv_title);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
//        recyclerview.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        ll_onclick = view.findViewById(R.id.ll_onclick);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_confirm = view.findViewById(R.id.btn_confirm);
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                if (!isViewSet) {
                    int height = v.getHeight();     //此处的view 和v 其实是同一个控件
                    int needHeight = (int) (displayMetrics.heightPixels * 0.60f);
                    isViewSet = true;
                    if (height > needHeight) {
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.height = needHeight;
                        dialogWindow.setAttributes(lp);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogWindow = getDialog().getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (displayMetrics.widthPixels * 0.68f);
//        lp.height = lp.height > displayMetrics.heightPixels * 0.68f ? (int) (displayMetrics.heightPixels * 0.68f) : lp.height;
        dialogWindow.setAttributes(lp);
        getDialog().setCanceledOnTouchOutside(bean.isCanceledOnTouchOutside());
    }

    private void initData() {
        if (bean != null) {
            //弹出对话框
            final String dialogTitle = bean.getTitle();
            //标题
            tv_title.setText(dialogTitle);
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
    }

    /**
     * 初始化主题色
     */
    @SuppressLint("ResourceAsColor")
    private void initTheme() {
        final int titleColor = bean.getTitleColor();
        final int themeColor = bean.getThemeColor();
        if (-1 == titleColor) {
            tv_title.setTextColor(this.getResources().getColor(mDefaultTitleColor));
        } else {
            tv_title.setTextColor(this.getResources().getColor(titleColor));
        }
        if (-1 == themeColor) {
            btn_cancel.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), this.getResources().getColor(R.color.luxiaochun_light_gray), Color.WHITE));
            btn_confirm.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), this.getResources().getColor(R.color.luxiaochun_light_gray), Color.WHITE));
            btn_cancel.setTextColor(this.getResources().getColor(R.color.luxiaochun_themeColor));
            btn_confirm.setTextColor(this.getResources().getColor(R.color.luxiaochun_themeColor));
        } else {
            btn_cancel.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), this.getResources().getColor(R.color.luxiaochun_light_gray), Color.WHITE));
            btn_confirm.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), this.getResources().getColor(R.color.luxiaochun_light_gray), Color.WHITE));
            btn_cancel.setTextColor(this.getResources().getColor(themeColor));
            btn_confirm.setTextColor(this.getResources().getColor(themeColor));
        }
    }


    private void initClickEvents() {
        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_confirm) {
            if (onClickListener != null) {
                dismiss();
                onClickListener.onConfirm(mAdapter.getCheckedNodeList());
            }
        } else if (i == R.id.btn_cancel) {
            if (onClickListener != null) {
                dismiss();
            }
        }
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }
}
