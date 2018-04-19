package com.example.nesthabit.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.clock.ClockFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_view_pager)
    ViewPager homeViewPager;

    HomePresenter homePresenter;
    @BindView(R.id.home_bottom_clock_img)
    ImageButton homeBottomClockImg;
    @BindView(R.id.home_bottom_nest_img)
    ImageButton homeBottomNestImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenter();
        ButterKnife.bind(this);
        homePresenter.attachView(this);
        initPager();
    }

    private void initPager() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ClockFragment());
        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
                homeViewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        homeViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), fragments));
    }

    private void selectTab(int position) {
        homeBottomClockImg.setImageResource(R.drawable.clock_unclick);
        homeBottomNestImg.setImageResource(R.drawable.nest_normal);
        switch (position) {
            case 0:
                homeBottomClockImg.setImageResource(R.drawable.clock_clicked);
                break;
            case 1:
                homeBottomNestImg.setImageResource(R.drawable.nest_click);
                break;
            default:
                break;
        }
        homeViewPager.setCurrentItem(position);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @OnClick({R.id.home_bottom_clock_img, R.id.home_bottom_nest_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_bottom_clock_img:
                selectTab(0);
                break;
            case R.id.home_bottom_nest_img:
                selectTab(1);
                break;
        }
    }
}
