package com.example.nesthabit.clock_set;

import android.os.Bundle;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;

public class ClockSet extends BaseActivity implements ClockView {

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
