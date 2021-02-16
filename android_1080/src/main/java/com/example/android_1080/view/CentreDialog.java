package com.example.android_1080.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_1080.R;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


/**
 * 通用对话框
 */
public class CentreDialog implements LifecycleObserver {

    private final Context mContext;
    private final Display mDisplay;
    protected Dialog dialog;
    //**************************************
    private LinearLayout mBackgroundLayout;
    //自定义布局
    private LinearLayout mMainLayout;
    private TextView mTitleTv;
    private Button mCancelButton;
    private Button mSureButton;
    private ImageView mLineImg;

    /**
     * 是否显示title
     */
    private boolean showTitle = false;
    /***
     * 是否显示确定按钮
     */
    private boolean showPosBtn = false;

    /**
     * 是否显示取消按钮
     */
    private boolean showNegBtn = false;
    /**
     * dialog  宽度
     */
    private float dialogWidth = 0.7f;

    public CentreDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
    }

    public CentreDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_centre_layout, null);
        mBackgroundLayout = view.findViewById(R.id.ll_background);
        mTitleTv = view.findViewById(R.id.tv_title);
        mMainLayout = view.findViewById(R.id.ll_alert);
        mCancelButton = view.findViewById(R.id.btn_neg);
        mSureButton = view.findViewById(R.id.btn_pos);
        mLineImg = view.findViewById(R.id.img_line);
        dialog = new Dialog(mContext, R.style.CentreDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Point point = new Point();
        mDisplay.getSize(point);
        mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (point.x * dialogWidth), ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }

    public CentreDialog setTitle(String title) {
        showTitle = true;
        mTitleTv.setText(title);
        return this;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public CentreDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public CentreDialog setCancelOutside(boolean isCancelOutside) {
        dialog.setCanceledOnTouchOutside(isCancelOutside);
        return this;
    }

    /**
     * 设置确定
     *
     * @param text
     * @param listener
     * @return
     */
    public CentreDialog setPositiveButton(String text, final View.OnClickListener listener) {
        showPosBtn = true;
        mSureButton.setText(text);
        mSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public CentreDialog setPositiveButton(final View.OnClickListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }

    /***
     * 设置取消
     * @param text
     * @param listener
     * @return
     */
    public CentreDialog setNegativeButton(String text,
                                                                      final View.OnClickListener listener) {
        showNegBtn = true;
        mCancelButton.setText(text);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public CentreDialog setNegativeButton(
            final View.OnClickListener listener) {
        setNegativeButton("取消", listener);
        return this;
    }


    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    /**
     * 设置dialog  宽度
     *
     * @param dialogWidth
     * @return
     */
    public CentreDialog setDialogWidth(float dialogWidth) {
        if (mBackgroundLayout != null) {
            mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (mDisplay.getWidth() * dialogWidth), LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        this.dialogWidth = dialogWidth;
        return this;
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


//                    new CentreDialog(LoginActivity .this)
//                        .builder()
//                        .setCancelable(true)
//                        .setCancelOutside(true)
//                        .setTitle("您确认解散该群?")
//                        .setDialogWidth(0.75f)
//                        .setPositiveButton("确定", new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    })
//            .setNegativeButton("取消", new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    })
//            .show();

}
