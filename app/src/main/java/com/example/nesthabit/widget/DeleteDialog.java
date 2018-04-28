package com.example.nesthabit.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.nesthabit.R;
import com.tencent.plus.DensityUtil;

/**
 * Created by dizzylay on 2018/4/27.
 */
public class DeleteDialog extends Dialog {
    private Context context;
    private int height;
    private int width;
    private boolean cancelTouchOut;
    private View view;
    private CancelListener cancelListener;

    private DeleteDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
        cancelListener = builder.cancelListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchOut);

        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.height = height;
            lp.width = width;
            window.setAttributes(lp);
        }
        view.findViewById(R.id.delete_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.onCancelClicked();
                dismiss();
            }
        });
    }

    private DeleteDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
    }

    public interface CancelListener {
        public void onCancelClicked();
    }

    public static final class Builder {
        private Context context;
        private int height;
        private int width;
        private boolean cancelTouchOut;
        private View view;
        private int resStyle = -1;
        private CancelListener cancelListener;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder heightDp(int val) {
            height = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder widthDp(int val) {
            width = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder cancelTouchOut(boolean val) {
            cancelTouchOut = val;
            return this;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder setCancelListener(CancelListener listener) {
            this.cancelListener = listener;
            return this;
        }

        public DeleteDialog build() {
            if (resStyle == -1) {
                return new DeleteDialog(this);
            } else {
                return new DeleteDialog(this, resStyle);
            }
        }
    }
}
