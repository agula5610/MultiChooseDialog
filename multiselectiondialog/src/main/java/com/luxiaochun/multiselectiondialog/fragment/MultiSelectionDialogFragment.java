package com.luxiaochun.multiselectiondialog.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.DialogType;
import com.luxiaochun.multiselectiondialog.MultiSelectionBean;
import com.luxiaochun.multiselectiondialog.MultiSelectionDialogManager;
import com.luxiaochun.multiselectiondialog.R;
import com.luxiaochun.multiselectiondialog.adapter.CustomAbsTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.MultiAllTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.MultiOrderTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.MultiTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.SingleAllTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.SingleBottomTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.adapter.SingleTreeRecyclerAdapter;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnClickListener;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;
import com.luxiaochun.multiselectiondialog.utils.ColorUtil;
import com.luxiaochun.multiselectiondialog.utils.DrawableUtil;
import com.luxiaochun.multiselectiondialog.utils.MultiDialogUtils;

import java.util.List;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2018-08-21 09:34
 */
public class MultiSelectionDialogFragment extends DialogFragment implements View.OnClickListener {
    private int mDefaultColor = 0xffe94339;
    private int mDefaultPicResId = -1;
    private MultiSelectionBean dialogBean;
    private OnItemClickListener onItemClickListener;
    private OnClickListener onClickListener;

    private ImageView iv_top;
    private TextView tv_title;
    private RecyclerView recyclerview;
    private LinearLayout ll_onclick;
    private Button btn_cancel;
    private Button btn_confirm;
    private Activity mActivity;
    private CustomAbsTreeRecyclerAdapter mAdapter;

    //默认色
    public static MultiSelectionDialogFragment newInstance(Bundle args) {
        MultiSelectionDialogFragment fragment = new MultiSelectionDialogFragment();
        if (args != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NO_TITLE | DialogFragment.STYLE_NO_FRAME, 0);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MultiSelectionDialog);
        mActivity = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        //点击window外的区域 是否消失
        getDialog().setCanceledOnTouchOutside(dialogBean.isCanceledOnTouchOutside());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.multi_selection_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view) {
        //提示内容
        iv_top = view.findViewById(R.id.iv_top);
        tv_title = view.findViewById(R.id.tv_title);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        ll_onclick = view.findViewById(R.id.ll_onclick);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_confirm = view.findViewById(R.id.btn_confirm);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        lp.width = (int) (displayMetrics.widthPixels * 0.68f);
        dialogWindow.setAttributes(lp);
        initData();
    }

    private void initData() {
        dialogBean = (MultiSelectionBean) getArguments().getSerializable(MultiSelectionDialogManager.TAG);
        //设置主题色
        initTheme();
        if (dialogBean != null) {
            //弹出对话框
            final String dialogTitle = dialogBean.getTitle();
            //标题
            tv_title.setText(dialogTitle);
            DialogType type = dialogBean.getType();
            List<Node> mDatas = dialogBean.getmDatas();
            if (DialogType.SINGLE.equals(type)) {
                mAdapter = new SingleTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown, onItemClickListener);
            } else if (DialogType.SINGLE_BOTTOM.equals(type)) {
                mAdapter = new SingleBottomTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown, onItemClickListener);
            } else if (DialogType.SINGLE_ALL.equals(type)) {
                mAdapter = new SingleAllTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown, onItemClickListener);
            } else if (DialogType.MULTI.equals(type)) {
                ll_onclick.setVisibility(View.VISIBLE);
                mAdapter = new MultiTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown);
                final List<Node> list = mAdapter.getCheckedNodeList();
            } else if (DialogType.MULTI_ALL.equals(type)) {
                ll_onclick.setVisibility(View.VISIBLE);
                mAdapter = new MultiAllTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown);
            } else {
                ll_onclick.setVisibility(View.VISIBLE);
                mAdapter = new MultiOrderTreeRecyclerAdapter(recyclerview, mActivity,
                        mDatas, R.drawable.pullup, R.drawable.pulldown, dialogBean.getLimited());
            }
            recyclerview.setAdapter(mAdapter);
            initClickEvents();
        }
    }

    /**
     * 初始化主题色
     */
    private void initTheme() {
        final int color = dialogBean.getmThemeColor();
        final int topResId = dialogBean.getmTopPic();

        if (-1 == topResId) {
            if (-1 == color) {
                //默认红色
                setDialogTheme(mDefaultColor, mDefaultPicResId);
            } else {
                setDialogTheme(color, mDefaultPicResId);
            }
        } else {
            if (-1 == color) {
                setDialogTheme(mDefaultColor, topResId);
            } else {
                setDialogTheme(color, topResId);
            }
        }
    }

    /**
     * 设置
     *
     * @param color    主色
     * @param topResId 图片
     */
    private void setDialogTheme(int color, int topResId) {
        if (topResId != -1) {
            iv_top.setImageResource(topResId);
        }
        btn_cancel.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), color));
        btn_confirm.setBackground(DrawableUtil.getDrawable(MultiDialogUtils.dip2px(4, getActivity()), color));
        //随背景颜色变化
        btn_cancel.setTextColor(ColorUtil.isTextColorDark(color) ? Color.BLACK : Color.WHITE);
        btn_confirm.setTextColor(ColorUtil.isTextColorDark(color) ? Color.BLACK : Color.WHITE);
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
                onClickListener.onPositive(mAdapter.getCheckedNodeList());
            }
        } else if (i == R.id.btn_cancel) {
            if (onClickListener != null) {
                onClickListener.onNegative();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }
}