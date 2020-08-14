package com.luxiaochun.multiselectiondialog.bubblepopupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.luxiaochun.multiselectiondialog.utils.DensityUtils;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog.bubblepopupwindow
 * Author: jun
 * Date: 2020-08-11 09:57
 */
public class BubblePopupWindow extends PopupWindow {
    private Context context;

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    private BubblePopupWindow(Context context) {
        this.context = context;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Window mWindow = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = 1.0f;
        mWindow.setAttributes(params);
    }

    public static class Builder {
        private Context mContext;
        private View mPopupView;    //弹窗布局View
        private int layoutResId;//布局id
        private View mView;     //自定义view
        private int mWidth, mHeight;//弹窗的宽和高
        private boolean isShowBg, isShowAnim;
        private float bg_level;//屏幕背景灰色程度
        private int animationStyle;//动画Id
        private boolean isTouchable = true;
        private ViewInterface listener;


        public Builder(Context context) {
            mContext = context;
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            mView = null;
            this.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            mView = view;
            layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            mWidth = width;
            mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            isShowBg = true;
            bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            isShowAnim = true;
            this.animationStyle = animationStyle;
            return this;
        }

        public BubblePopupWindow create() {
            final BubblePopupWindow popupWindow = new BubblePopupWindow(mContext);
            if (layoutResId != 0) {
                mPopupView = LayoutInflater.from(mContext).inflate(layoutResId, null);
            } else if (mView != null) {
                mPopupView = mView;
            }
            popupWindow.setContentView(mPopupView);

            if (mWidth == 0 || mHeight == 0) {
                //如果没设置宽高，默认是WRAP_CONTENT
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                popupWindow.setWidth(mWidth);
                popupWindow.setHeight(mHeight);
            }
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明背景
            popupWindow.setOutsideTouchable(isTouchable);//设置outside可点击
            popupWindow.setFocusable(isTouchable);
            if (isShowBg) {
                //设置背景
                Window mWindow = ((Activity) mContext).getWindow();
                WindowManager.LayoutParams params = mWindow.getAttributes();
                params.alpha = bg_level;
                mWindow.setAttributes(params);
            }
            if (isShowAnim) {
                popupWindow.setAnimationStyle(animationStyle);
            }
            if (listener != null && layoutResId != 0) {
                listener.getChildView(mPopupView, layoutResId);
            }
            DensityUtils.measureWidthAndHeight(mPopupView);
            return popupWindow;
        }
    }
}
