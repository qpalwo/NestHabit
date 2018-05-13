package com.example.nesthabit.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.model.DataUtil;
import com.example.nesthabit.model.NestHelper;
import com.example.nesthabit.model.bean.Nest;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NestCreateActivity extends BaseActivity implements NestCreateView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.create_name_edit_text)
    EditText createNameEditText;
    @BindView(R.id.create_introduction_edit_text)
    EditText createIntroductionEditText;
    @BindView(R.id.create_challenge_date_edit_text)
    EditText createChallengeDateEditText;
    @BindView(R.id.create_start_date_text)
    TextView createStartDateText;
    @BindView(R.id.create_start_date_item)
    RelativeLayout createStartDateItem;
    @BindView(R.id.limit_number)
    Switch limitNumber;
    @BindView(R.id.create_member_number_edit_text)
    EditText createMemberNumberEditText;
    @BindView(R.id.create_submit)
    Button createSubmit;
    @BindView(R.id.nest_amount_layout)
    RelativeLayout nestAmountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_create);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        limitNumber.setChecked(true);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayShowTitleEnabled(false);
            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            toolbarTitle.setText("创建鸟窝");
        }
        limitNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    nestAmountLayout.setVisibility(View.GONE);
                } else {
                    nestAmountLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        createChallengeDateEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        createMemberNumberEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void getNestInfo() {
        Nest nest = new Nest();
        if (TextUtils.isEmpty(createNameEditText.getText().toString())) {
            showToast("请输入鸟窝名称", Toast.LENGTH_SHORT);
            return;
        } else if (TextUtils.isEmpty(createIntroductionEditText.getText().toString())) {
            showToast("请输入鸟窝简介", Toast.LENGTH_SHORT);
            return;
        } else if (TextUtils.isEmpty(createChallengeDateEditText.getText().toString())) {
            showToast("请输入挑战天数", Toast.LENGTH_SHORT);
            return;
        }
        if (!limitNumber.isChecked()) {
            nest.setMembersLimit(0);
        } else {
            if (TextUtils.isEmpty(createMemberNumberEditText.getText().toString())) {
                showToast("请输入成员数量", Toast.LENGTH_SHORT);
                return;
            }
            nest.setMembersLimit(Integer.parseInt(createMemberNumberEditText.getText().toString().trim()));
        }
        //nest.setId();
        nest.setName(createNameEditText.getText().toString());
        nest.setDesc(createIntroductionEditText.getText().toString());
        nest.setChallengeDays(Integer.parseInt(createChallengeDateEditText.getText().toString()));
        nest.setCreatedTime(DataUtil.getUnixStamp());
        nest.setIsOpen(1);//创建初默认开放
        // nest.setOwner(AVUser.getCurrentUser().getUsername());
        nest.setCreator(nest.getOwner());
        nest.setMemberAmount(1);
        NestHelper nestHelper = new NestHelper();
        nestHelper.createNestOnNet(nest);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.create_start_date_item, R.id.create_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_start_date_item:
                break;
            case R.id.create_submit:
                getNestInfo();
                break;
        }
    }
}
