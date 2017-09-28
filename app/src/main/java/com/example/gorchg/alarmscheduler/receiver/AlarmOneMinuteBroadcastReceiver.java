package com.example.gorchg.alarmscheduler.receiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.gorchg.alarmscheduler.AlarmUtils;

/**
 * Created by Ch on 2016-05-13.
 */
public class AlarmOneMinuteBroadcastReceiver extends AlarmBraodCastReciever{


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);

        // 알람 스타트
        AlarmUtils.getInstance().startOneMinuteAlram(context);

        Toast.makeText(context,"Passed one minute.",Toast.LENGTH_SHORT).show();
    }
}
