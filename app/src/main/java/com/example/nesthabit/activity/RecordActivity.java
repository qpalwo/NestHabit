package com.example.nesthabit.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.NestFragment;
import com.example.nesthabit.fragment.RecordFragment;
import com.example.nesthabit.model.bean.Nest;

public class RecordActivity extends BaseActivity {

    Intent intent;
    Nest nest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        intent = getIntent();
        nest = (Nest) intent.getSerializableExtra(NestFragment.NEST);
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
}
