package com.example.nesthabit.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.example.nesthabit.R;
import com.example.nesthabit.adapter.HomePagerAdapter;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.fragment.ClockFragment;
import com.example.nesthabit.fragment.NestFragment;

import com.makeramen.roundedimageview.RoundedImageView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

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
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


    @BindView(R.id.home_bottom_clock_img)
    ImageButton homeBottomClockImg;
    @BindView(R.id.home_bottom_nest_img)
    ImageButton homeBottomNestImg;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_user_avatar)
    RoundedImageView toolbarUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        Connector.getDatabase();
        initView();
        initPager();
        selectTab(0);

    }

    private void initView() {
        setSupportActionBar(homeToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("闹钟");
        toolbarUserAvatar.setVisibility(View.VISIBLE);
    }

    private void initPager() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ClockFragment());
        fragments.add(new NestFragment());
        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

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
                toolbarTitle.setText("闹钟");
                break;
            case 1:
                homeBottomNestImg.setImageResource(R.drawable.nest_click);
                toolbarTitle.setText("鸟窝");
                break;
            default:
                break;
        }
        homeViewPager.setCurrentItem(position);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.home_bottom_clock, R.id.home_bottom_nest,
            R.id.home_bottom_clock_img, R.id.home_bottom_nest_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_bottom_clock:
            case R.id.home_bottom_clock_img:
                selectTab(0);
                break;
            case R.id.home_bottom_nest:
            case R.id.home_bottom_nest_img:
                selectTab(1);
                break;
        }
    }

    @OnClick(R.id.toolbar_user_avatar)
    public void onViewClicked() {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
