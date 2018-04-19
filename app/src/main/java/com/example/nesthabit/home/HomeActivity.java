package com.example.nesthabit.home;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_view_pager)
    ViewPager homeViewPager;
    @BindView(R.id.home_bottom)
    BottomNavigationView homeBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initBottom() {
        homeBottom.setLayoutMode(BottomNavigationView.LAYOUT_MODE_CLIP_BOUNDS);


    }
}
