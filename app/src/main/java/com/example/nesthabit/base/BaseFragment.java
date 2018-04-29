package com.example.nesthabit.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nesthabit.R;

import java.util.Objects;

/**
 * Created by 肖宇轩 on 2018/3/3.
 */

public abstract class BaseFragment extends Fragment implements BaseView, HandleBackInterface{
    public abstract int getContentViewId();
    protected Context mContext;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        return mRootView;
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).showLoading();
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).hideLoading();
    }
    @Override
    public void showToast(String msg, int time) {
        checkActivityAttached();
        ((BaseActivity) mContext).showToast(msg, time);
    }

    @Override
    public boolean onBackPressed(){
        return HandleBackUtil.handleBackPress(this);
    }


    protected boolean isAttachedContext(){
        return getActivity() != null;
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity !");
        }
    }

    protected void setToolbarTitle(String title) {
        TextView toolbarTitle = Objects.requireNonNull(getActivity()).findViewById(R.id
                .toolbar_title);
        toolbarTitle.setText(title);
    }

}
