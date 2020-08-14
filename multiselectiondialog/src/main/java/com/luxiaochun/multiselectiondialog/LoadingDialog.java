package com.luxiaochun.multiselectiondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2020-08-10 16:03
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context context;
        private String message;
        private boolean isShowMessage = true;
        private boolean isCancelable = false;
        private boolean isCancelOutside = false;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         * @param message
         * @return
         */

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         * @param isShowMessage
         * @return
         */
        public Builder setShowMessage(boolean isShowMessage) {
            this.isShowMessage = isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         *
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /**
         * 设置是否可以点击外部取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }

        public LoadingDialog build() {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.loading_dialog, null);
            LoadingDialog loadingDilaog = new LoadingDialog(context, R.style.LoadingDialogStyle);
            TextView msgText = view.findViewById(R.id.loading_msg_tv);
            if (isShowMessage) {
                msgText.setText(message);
            } else {
                msgText.setVisibility(View.GONE);
            }
            loadingDilaog.setContentView(view);
            loadingDilaog.setCancelable(isCancelable);
            loadingDilaog.setCanceledOnTouchOutside(isCancelOutside);
            //实现loading的透明度
//            WindowManager.LayoutParams lp=mmLoading.getWindow().getAttributes();
//            lp.alpha = 0.6f;
//            mmLoading.getWindow().setAttributes(lp);
            return loadingDilaog;
        }
    }
}