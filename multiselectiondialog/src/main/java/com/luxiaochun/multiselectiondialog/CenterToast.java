package com.luxiaochun.multiselectiondialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ProjectName: MultiChooseDialog
 * PackageName: com.luxiaochun.multiselectiondialog
 * Author: jun
 * Date: 2020-08-10 16:37
 */
public class CenterToast extends Toast {

    public CenterToast(Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        private String message;
        private boolean isSuccess = true;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置toast的内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 是否是显示成功的toast，true:成功 false:失败
         *
         * @param isSuccess
         * @return
         */
        public Builder setSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public CenterToast build() {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.center_toast_dialog, null);
            CenterToast mmToast = new CenterToast(context);
            TextView msgText = view.findViewById(R.id.center_toast_tv);
            ImageView imageView = view.findViewById(R.id.center_toast_image);
            if (!message.isEmpty()) {
                msgText.setText(message);
            }
            if (isSuccess) {
                imageView.setImageResource(R.drawable.center_toast_success);
            } else {
                imageView.setImageResource(R.drawable.center_toast_failure);
            }

            mmToast.setView(view);
            mmToast.setDuration(Toast.LENGTH_SHORT);
            mmToast.setGravity(Gravity.CENTER,0,0);
            return mmToast;
        }
    }
}