package com.example.nesthabit.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nesthabit.adapter.NestContentPagerAdapter;
import com.example.nesthabit.fragment.PunchAndCommunicateFragment;
import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;

import java.util.ArrayList;

public class NestContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_content);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nest_content_menu, menu);
        return true;
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
            toolbarTitle.setText("早起的鸟儿有虫吃");
        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PunchAndCommunicateFragment());
        fragments.add(new PunchAndCommunicateFragment());
        FragmentManager fragmentManager = getSupportFragmentManager();
        NestContentPagerAdapter pagerAdapter = new NestContentPagerAdapter(fragmentManager,
                fragments);
        ViewPager viewPager = findViewById(R.id.nest_content_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        TabLayout tabLayout = findViewById(R.id.nest_content_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.rank_menu_content:
                Intent intentRank = new Intent(this, RankActivity.class);
                startActivity(intentRank);
                break;
            case R.id.more_menu_content:
                Intent intentDetail = new Intent(this, NestDetailActivity.class);
                startActivity(intentDetail);
                break;
            default:
        }
        return true;
    }
}
