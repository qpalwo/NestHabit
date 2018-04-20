package com.example.nesthabit.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.fragment.RankFragment;
import com.example.nesthabit.adapter.NestContentPagerAdapter;
import com.example.nesthabit.base.BaseActivity;

import java.util.ArrayList;

public class RankActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
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
            toolbarTitle.setText("排行榜");
        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RankFragment());
        fragments.add(new RankFragment());
        FragmentManager fragmentManager = getSupportFragmentManager();
        NestContentPagerAdapter pagerAdapter = new NestContentPagerAdapter(fragmentManager, fragments);
        ViewPager viewPager = findViewById(R.id.rank_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        TabLayout tabLayout = findViewById(R.id.rank_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
