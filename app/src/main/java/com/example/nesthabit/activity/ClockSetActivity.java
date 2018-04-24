package com.example.nesthabit.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.ClockSetView;
import com.example.nesthabit.presenter.ClockSetPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClockSetActivity extends BaseActivity implements ClockSetView {

    private ClockSetPresenter clockSetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_set);
        ButterKnife.bind(this);
        clockSetPresenter = new ClockSetPresenter();
        clockSetPresenter.attachView(this);
        initView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        clockSetPresenter.detachView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayShowTitleEnabled(false);
            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            toolbarTitle.setText("添加闹钟");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPink));
        }
    }

    @OnClick(R.id.clock_set_sound_item)
    public void onViewClicked() {

    }
}
