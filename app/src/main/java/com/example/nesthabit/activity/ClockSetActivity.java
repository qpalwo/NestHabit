package com.example.nesthabit.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.ClockSetFragment;
import com.example.nesthabit.fragment.ClockSetView;
import com.example.nesthabit.presenter.ClockSetPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClockSetActivity extends BaseActivity implements ClockSetView {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPink));
        }
        ClockSetFragment clockSetFragment = new ClockSetFragment();
        @SuppressLint("CommitTransaction") FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.clock_set_fragment_container, clockSetFragment)
                .commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                } else {
                    finish();
                }
                break;
            default:
        }
        return true;
    }
}
