package com.example.nesthabit.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.model.DataUtil;
import com.example.nesthabit.model.bean.Nest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nesthabit.activity.NestContentActivity.NEST;

public class ClockRemindActivity extends BaseActivity {
    String title;
    long time;
    boolean isVibrate;
    String sound;
    Nest nest;
    @BindView(R.id.clock_remind_time)
    TextView clockRemindTime;
    @BindView(R.id.clock_remind_title)
    TextView clockRemindTitle;
    @BindView(R.id.clock_cancel_button)
    Button clockCancelButton;
    @BindView(R.id.clock_punch)
    Button clockPunch;
    @BindView(R.id.clock_nap_button)
    Button clockNapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_remind);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        time = intent.getLongExtra("time", 0);
        isVibrate = intent.getBooleanExtra("isVibrate", true);
        sound = intent.getStringExtra("sound");
        nest = (Nest) intent.getSerializableExtra(NEST);

        clockRemindTitle.setText(title);
        clockRemindTime.setText(DataUtil.getTime(time));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVibrate) {
            doVibrate();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void doVibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null) {
            long[] timings = {400, 800, 1200, 1600};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(timings, 0));
            } else {
                vibrator.vibrate(timings, 0);
            }
        }
    }


    @OnClick({R.id.clock_cancel_button, R.id.clock_punch, R.id.clock_nap_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clock_cancel_button:
                finish();
                break;
            case R.id.clock_punch:
                Intent intent = new Intent(this, RecordActivity.class);
                intent.putExtra(NEST, nest);
                startActivity(intent);
                finish();
                break;
            case R.id.clock_nap_button:
                break;
            default:
                break;
        }
    }
}
