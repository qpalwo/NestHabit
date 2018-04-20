package com.example.nesthabit.fragment;

import android.os.Bundle;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.presenter.ClockSetPresenter;

public class ClockSet extends BaseActivity implements ClockSetView {

    private ClockSetPresenter clockSetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_set);
        clockSetPresenter = new ClockSetPresenter();
        clockSetPresenter.attachView(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        clockSetPresenter.detachView();
    }
}
