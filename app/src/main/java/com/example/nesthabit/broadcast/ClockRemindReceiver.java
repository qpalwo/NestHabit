package com.example.nesthabit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.nesthabit.activity.ClockRemindActivity;

import static com.example.nesthabit.activity.NestContentActivity.NEST;

public class ClockRemindReceiver extends BroadcastReceiver {

    private static final String TAG = "ClockRemindReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent startActivity = new Intent(context, ClockRemindActivity.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra("title", intent.getStringExtra("title"));
        startActivity.putExtra("time", intent.getLongExtra("time", 0));
        startActivity.putExtra("isVibrate", intent.getBooleanExtra("isVibrate", false));
        startActivity.putExtra("sound", intent.getStringExtra("sound"));
        startActivity.putExtra(NEST, intent.getSerializableExtra(NEST));
        context.startActivity(startActivity);

        AlarmSetManager.setAlarm(context);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
