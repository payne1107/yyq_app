package com.yyq58.activity.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yyq58.R;


/**
 * Created by Administrator on 2017/9/23 0023.
 *  加载对话框
 */

public class LoadingDailog extends Dialog {
    public LoadingDailog(Context context) {
        super(context);
    }

    public LoadingDailog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        private Context context;
        private String message;
        private boolean isCancelable;
        private boolean isCancelOutside;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         * @param message
         * @return
         */

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable){
            this.isCancelable=isCancelable;
            return this;
        }

        /**
         * 设置是否可以取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside=isCancelOutside;
            return this;
        }


        private LoadingDailog loadingDailog;
        public LoadingDailog create() {
            if (context != null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                if (inflater != null) {
                    View view = inflater.inflate(R.layout.dialog_loading, null);
                    loadingDailog = new LoadingDailog(context, R.style.MyDialogStyle);
                    TextView msgText = (TextView) view.findViewById(R.id.tipTextView);
                    msgText.setText(message);
                    loadingDailog.setContentView(view);
                    loadingDailog.setCancelable(isCancelable);
                    loadingDailog.setCanceledOnTouchOutside(isCancelOutside);
                }
            }
            return loadingDailog;
        }
    }
}
