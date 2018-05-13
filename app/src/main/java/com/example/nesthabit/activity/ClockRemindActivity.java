package com.example.nesthabit.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseActivity;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Nest;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nesthabit.activity.NestContentActivity.NEST;

public class ClockRemindActivity extends BaseActivity {

    String title;
    long time;
    boolean isVibrate;
    String sound;
    int nestId;
    Vibrator vibrator;
    MediaPlayer mediaPlayer = new MediaPlayer();

    private static final String TAG = "ClockRemindActivity";

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
        nestId = intent.getIntExtra(NEST, 0);
//        Log.d(TAG, "onCreate: " + String.valueOf(nest != null));
        clockRemindTitle.setText(title);
        clockRemindTime.setText(DateUtil.getTime(time));
        if (isVibrate) {
            doVibrate();
        }
        playSound(sound);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vibrator != null) {
            vibrator.cancel();
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void doVibrate() {
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null) {
            long[] timings = {400, 800, 1200, 1600};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(timings, 0));
            } else {
                vibrator.vibrate(timings, 0);
            }
        }
    }

    private void playSound(String path) {
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
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
                intent.putExtra(NEST, nestId);
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
