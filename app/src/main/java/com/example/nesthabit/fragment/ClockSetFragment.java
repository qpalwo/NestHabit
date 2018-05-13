package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nesthabit.R;
import com.example.nesthabit.adapter.NestSelectAdapter;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.broadcast.AlarmSetManager;
import com.example.nesthabit.model.ClockHelper;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Clock;
import com.example.nesthabit.model.bean.Nest;
import com.example.nesthabit.model.bean.Sound;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
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

    private List<Nest> nestList;
    private Nest currentNest;
    private Sound currentSound;
    private int durationLevel = 0;
    private int isVibrate = 0;

    private static final String TAG = "ClockSetFragment";

    @BindView(R.id.clock_set_volume_text)
    TextView clockSetVolumeText;
    @BindView(R.id.clock_set_time_picker)
    TimePicker clockSetTimePicker;
    @BindView(R.id.clock_set_title)
    EditText clockSetTitle;
    @BindView(R.id.clock_set_sound_text)
    TextView clockSetSoundText;
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
        nestList = DataSupport.findAll(Nest.class);
        clockSetTimePicker.setIs24HourView(true);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("添加闹钟");
        initView();
        if (currentSound != null) {
            clockSetSoundText.setText(currentSound.getName());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        if (nestList != null && !nestList.isEmpty()) {
            currentNest = nestList.get(0);
            clockSetNestName.setText(currentNest.getName());
        }
        clockSetRemindTextSwitch.setChecked(true);
        clockSetVolumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String percent = String.valueOf(progress) + "%";
                clockSetVolumeText.setText(percent);
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
            // 设置闹钟的日期
            case R.id.clock_days_0:
                durationLevel ^= 0x01;
                changeViewActivated(view);
                break;
            case R.id.clock_days_1:
                durationLevel ^= 0x02;
                changeViewActivated(view);
                break;
            case R.id.clock_days_2:
                durationLevel ^= 0x04;
                changeViewActivated(view);
                break;
            case R.id.clock_days_3:
                durationLevel ^= 0x08;
                changeViewActivated(view);
                break;
            case R.id.clock_days_4:
                durationLevel ^= 0x10;
                changeViewActivated(view);
                break;
            case R.id.clock_days_5:
                durationLevel ^= 0x20;
                changeViewActivated(view);
                break;
            case R.id.clock_days_6:
                durationLevel ^= 0x40;
                changeViewActivated(view);
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
                if (nestList == null) {
                    nestList = new ArrayList<>();
                }
                final BottomSheetDialog dialog = new BottomSheetDialog(Objects.requireNonNull
                        (getActivity()));
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_nest, null);
                RecyclerView recyclerView = dialogView.findViewById(R.id.dialog_select_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
                NestSelectAdapter adapter = new NestSelectAdapter(nestList);
                adapter.setItemOnClickListener(new ItemOnClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        currentNest = nestList.get(position);
                        clockSetNestName.setText(currentNest.getName());
                        dialog.dismiss();
                    }
                });
                recyclerView.setAdapter(adapter);
                dialog.setContentView(dialogView);
//                View behaviorView = Objects.requireNonNull(dialog.getWindow()).findViewById(
//                        android.support.design.R.id.design_bottom_sheet);
//                BottomSheetBehavior.from(view).setPeekHeight(1600);
                dialog.show();
                break;

            case R.id.clock_set_complete_button:
                addClock();
                break;

            default:
                break;
        }
    }

    public void setCurrentSound(Sound currentSound) {
        this.currentSound = currentSound;
    }

    public void setIsVibrate(int isVibrate) {
        this.isVibrate = isVibrate;
    }

    private void changeViewActivated(View view) {
        if (view.isActivated()) {
            view.setActivated(false);
        } else {
            view.setActivated(true);
        }
    }

    private void addClock() {
        Clock clock = new Clock();
        // clock.setId();
        if (TextUtils.isEmpty(clockSetTitle.getText().toString())) {
            showToast("请填写闹钟的标题", Toast.LENGTH_SHORT);
            return;
        } else if (currentNest == null) {
            showToast("请选择鸟窝", Toast.LENGTH_SHORT);
            return;
        } else if (currentSound == null) {
            showToast("请选择铃声", Toast.LENGTH_SHORT);
            return;
        } else if (durationLevel == 0) {
            showToast("请选择闹钟的日期", Toast.LENGTH_SHORT);
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
        clock.setCreateTime(DateUtil.getUnixStamp());
        clock.setNestId(currentNest.getId());
        clock.setMusicId(currentSound.getPath());
        clock.setDurationLevel(durationLevel);
        clock.setIsVibrate(isVibrate);
//                clock.setSlogan();
//                clock.setOwner(AVUser.getCurrentUser().getUsername());
        ClockHelper helper = new ClockHelper();
        helper.createClockOnNet(clock);

        AlarmSetManager.setAlarm(getContext());
        Objects.requireNonNull(getActivity()).finish();
    }
}
