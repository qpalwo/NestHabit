package com.example.nesthabit.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.adapter.NestContentPagerAdapter;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.fragment.NestFragment;
import com.example.nesthabit.fragment.PunchAndCommunicateFragment;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Nest;
import com.example.nesthabit.model.bean.Punch;
import com.example.nesthabit.widget.ProgressBar;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NestContentActivity extends BaseActivity {

    @BindView(R.id.total_punch_number)
    TextView totalPunchNumber;
    @BindView(R.id.successive_punch_number)
    TextView successivePunchNumber;
    @BindView(R.id.punch)
    Button punch;
    @BindView(R.id.total_punch_progress)
    ProgressBar totalPunchProgress;
    @BindView(R.id.successive_punch_progress)
    ProgressBar successivePunchProgress;
    private Nest nest;
    private Punch punchData;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    private static final String TAG = "NestContentActivity";
    public static final String NEST = "NEST";
    public static final String FINISH = "com.example.nesthabit.activity.nestcontentactivity.FINISH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_content);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nest = (Nest) intent.getSerializableExtra(NEST);
        initView();
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH);
        broadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        punchData = DataSupport.where("nestId = ?", String.valueOf(nest.getId()))
                .findFirst(Punch.class);
        int interval = DateUtil.daysInterval(punchData.getLastPunchDate() * 1000, System.currentTimeMillis());
        if (interval != 0) {
            punch.setText("打 卡");
            if (interval > 1 && punchData.getSuccessPunch() != 0) {
                punchData.setSuccessPunch(0);
                punchData.save();
            }
        } else {
            punch.setText("已 打 卡");
        }
        totalPunchNumber.setText(String.valueOf(punchData.getAllPunch()));
        successivePunchNumber.setText(String.valueOf(punchData.getSuccessPunch()));
        totalPunchProgress.setCurrentProgress((float) punchData.getAllPunch() / nest.getChallengeDays());
        successivePunchProgress.setCurrentProgress((float) punchData.getSuccessPunch() / nest.getChallengeDays());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(broadcastReceiver);
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
            toolbarTitle.setText(nest.getName());
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
                intentDetail.putExtra(NEST, nest);
                startActivity(intentDetail);
                break;
            default:
        }
        return true;
    }

    @OnClick(R.id.punch)
    public void onViewClicked() {
        if (DateUtil.daysInterval(punchData.getLastPunchDate() * 1000, System.currentTimeMillis()) != 0) {
            Intent intent = new Intent(this, RecordActivity.class);
            intent.putExtra(NEST, nest.getId());
            startActivity(intent);
        }
    }

}
