package com.example.gorchg.alarmscheduler.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gorchg.alarmscheduler.AlarmUtils;

import java.util.Date;

/**
 * Created by Ch on 2016-05-13.
 */
public class AlarmFiveSecondBroadcastReceiver extends AlarmBraodCastReciever{

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);

        // 알람 스타트
        AlarmUtils.getInstance().startFiveSecondAlram(context);

        Log.d(getClass().getName(),"time second : "+new Date().toString());
    }
}
