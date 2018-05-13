package com.example.nesthabit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.NestDetailFragment;
import com.example.nesthabit.model.bean.Nest;

import org.litepal.crud.DataSupport;

import butterknife.ButterKnife;

public class NestDetailActivity extends BaseActivity implements NestDetailFragment.NestDeleteCallback {

    private Nest nest;

    private static final String TAG = "NestDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_detail);
        ButterKnife.bind(this);
        nest = (Nest) getIntent().getSerializableExtra(NestContentActivity.NEST);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        NestDetailFragment nestDetailFragment = new NestDetailFragment();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nest_detail_fragment_container, nestDetailFragment)
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
    public void onNestDelete() {
        DataSupport.delete(Nest.class, nest.getId());
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent(NestContentActivity.FINISH);
        broadcastManager.sendBroadcast(intent);
        finish();
    }

    @Override
    public Nest getNest() {
        return nest;
    }
}
