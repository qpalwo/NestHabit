package com.example.nesthabit.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.NestFragment;
import com.example.nesthabit.fragment.RecordFragment;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Nest;
import com.example.nesthabit.model.bean.Punch;

import org.litepal.crud.DataSupport;

public class RecordActivity extends BaseActivity implements RecordFragment.RecordFragmentCallback {

    Intent intent;
    int nestId;
    Nest nest;
    Punch punchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        intent = getIntent();
        nestId = intent.getIntExtra(NestFragment.NEST, 0);
        punchData = DataSupport.where("nestId = ?", String.valueOf(nestId))
                .findFirst(Punch.class);
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
        RecordFragment recordFragment = new RecordFragment();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.punch_fragment_container, recordFragment)
                .commit();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void upDatePunchData() {
        punchData.setAllPunch(punchData.getAllPunch() + 1);
        punchData.setSuccessPunch(punchData.getSuccessPunch() + 1);
        punchData.setLastPunchDate(DateUtil.getUnixStamp());
        punchData.save();
    }
}
