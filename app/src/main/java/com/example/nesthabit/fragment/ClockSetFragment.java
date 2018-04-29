package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.R;
import com.example.nesthabit.adapter.NestSelectAdapter;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.CallBack;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.ClockHelper;
import com.example.nesthabit.model.UserHelper;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/24.
 */
public class ClockSetFragment extends BaseFragment {


    Unbinder unbinder;

    List<Nest> nestList;
    Nest currentNest;

    @BindView(R.id.clock_set_volume_text)
    TextView clockSetVolumeText;
    @BindView(R.id.clock_set_time_picker)
    TimePicker clockSetTimePicker;
    @BindView(R.id.clock_set_title)
    EditText clockSetTitle;
    @BindView(R.id.clock_set_volume_seekbar)
    SeekBar clockSetVolumeSeekbar;
    @BindView(R.id.clock_set_nap_switch)
    Switch clockSetNapSwitch;
    @BindView(R.id.clock_set_remind_voice_switch)
    Switch clockSetRemindVoiceSwitch;
    @BindView(R.id.clock_set_remind_text_switch)
    Switch clockSetRemindTextSwitch;
    @BindView(R.id.clock_set_nest_name)
    TextView clockSetNestName;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock_set;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
        }
        getNests();
        return rootView;
    }

    private void getNests() {
        UserHelper helper = new UserHelper();
        helper.getNest(AVUser.getCurrentUser(), new CallBack<List<Nest>>() {
            @Override
            public void onSuccess(List<Nest> data) {
                nestList = data;
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("添加闹钟");
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        clockSetRemindTextSwitch.setChecked(true);
        clockSetVolumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                clockSetVolumeText.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @OnClick({R.id.clock_days_0, R.id.clock_days_1, R.id.clock_days_2, R.id.clock_days_3, R.id
            .clock_days_4, R.id.clock_days_5, R.id.clock_days_6, R.id.clock_set_complete_button,
            R.id.clock_set_nest_item, R.id.clock_set_sound_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clock_days_0:
            case R.id.clock_days_1:
            case R.id.clock_days_2:
            case R.id.clock_days_3:
            case R.id.clock_days_4:
            case R.id.clock_days_5:
            case R.id.clock_days_6:
                if (view.isActivated()) {
                    view.setActivated(false);
                } else {
                    view.setActivated(true);
                }
                break;

            case R.id.clock_set_sound_item:
                ClockSoundSetFragment soundSetFragment = new ClockSoundSetFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.clock_set_fragment_container, soundSetFragment)
                        .addToBackStack("clock")
                        .commit();
                break;

            case R.id.clock_set_nest_item:
                BottomSheetDialog dialog = new BottomSheetDialog(Objects.requireNonNull
                        (getActivity()));
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_nest, null);
                RecyclerView recyclerView = view.findViewById(R.id.selected_friend_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
                NestSelectAdapter adapter = new NestSelectAdapter(nestList);
                adapter.setItemOnClickListener(new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        currentNest = nestList.get(position);
                        clockSetNestName.setText(currentNest.getName());
                    }
                });
                recyclerView.setAdapter(adapter);
                dialog.setContentView(dialogView);
                dialog.show();
                break;

            case R.id.clock_set_complete_button:
                Clock clock = new Clock();
                clock.setId();
                if (clockSetTitle.getText().toString() == "") {
                    showToast("请填写闹钟的标题");
                    return;
                }
                clock.setTitle(clockSetTitle.getText().toString());
                clock.setIsOpen(1);
                clock.setTimeHour(clockSetTimePicker.getHour());
                clock.setTimeMin(clockSetTimePicker.getMinute());
                clock.setVolumeLevel(clockSetVolumeSeekbar.getProgress());
                clock.setNapLevel(clockSetNapSwitch.isChecked() ? 1 : 0);
                clock.setWillingMusic(clockSetRemindVoiceSwitch.isChecked() ? 1 : 0);
                clock.setWillingText(clockSetRemindTextSwitch.isChecked() ? 1 : 0);
                clock.setOwner(AVUser.getCurrentUser().getUsername());
                clock.setCreateTime(System.currentTimeMillis());
                clock.setNest(currentNest);
//                clock.setMusicId();
//                clock.setDurationLevel();
//                clock.setSlogan();

                ClockHelper helper = new ClockHelper();
                helper.createClockOnNet(clock);
                break;
        }
    }
}
