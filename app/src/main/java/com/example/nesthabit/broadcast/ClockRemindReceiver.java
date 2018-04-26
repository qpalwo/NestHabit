package com.example.nesthabit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.nesthabit.activity.ClockRemindActivity;

public class ClockRemindReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent startActivity = new Intent(context, ClockRemindActivity.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra("title", intent.getStringExtra("title"));
        startActivity.putExtra("time", intent.getLongExtra("time", 0));
        startActivity.putExtra("isVibrate", intent.getBooleanExtra("isVibrate", true));
        context.startActivity(startActivity);

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
