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
import com.example.nesthabit.fragment.ClockSoundSetFragment;
import com.example.nesthabit.model.bean.Sound;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClockSetActivity extends BaseActivity implements ClockSetView,
        ClockSoundSetFragment.SoundSetCallback {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ClockSetFragment clockSetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeRed);
        setContentView(R.layout.activity_clock_set);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayShowTitleEnabled(false);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPink, null));
        }
        clockSetFragment = new ClockSetFragment();
        FragmentTransaction transaction =
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

    @Override
    public void onSoundSet(Sound sound) {
        clockSetFragment.setCurrentSound(sound);
    }

    @Override
    public void setIsVibrate(int isVibrate) {
        clockSetFragment.setIsVibrate(isVibrate);
    }
}
