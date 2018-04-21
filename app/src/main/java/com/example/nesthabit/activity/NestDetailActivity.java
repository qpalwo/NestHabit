package com.example.nesthabit.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.nesthabit.R;

public class NestDetailActivity extends AppCompatActivity {

    private static final String TAG = "NestDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_detail);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.d(TAG, "initView: actionbar");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayShowTitleEnabled(false);
            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            toolbarTitle.setText("早起的鸟儿有虫吃");
        }

        ViewStub viewStub = findViewById(R.id.detail_creator);
        if (viewStub != null) {
            View inflatedView = viewStub.inflate();
        }
        TextView exitText = findViewById(R.id.detail_exit_text);
        exitText.setText("解散鸟窝");
    }
}
