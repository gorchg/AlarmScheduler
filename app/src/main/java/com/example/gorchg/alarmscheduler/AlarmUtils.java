package com.example.gorchg.alarmscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.gorchg.alarmscheduler.receiver.AlarmBraodCastReciever;
import com.example.gorchg.alarmscheduler.receiver.AlarmOneMinuteBroadcastReceiver;
import com.example.gorchg.alarmscheduler.receiver.AlarmFiveSecondBroadcastReceiver;

import java.util.Calendar;

/**
 * Created by Ch on 2016-05-13.
 */
public class AlarmUtils {
    private final static int FIVE_SECOND = 5 * 1000;
    private final static int ONE_MINUES = 60 * 1000;

    private static AlarmUtils _instance;

    public static AlarmUtils getInstance() {
        if (_instance == null) _instance = new AlarmUtils();
        return _instance;
    }

    public void startOneMinuteAlram(Context context) {

        // AlarmOneMinuteBroadcastReceiver 초기화
        Intent alarmIntent = new Intent(context, AlarmOneMinuteBroadcastReceiver.class);
        alarmIntent.putExtra("name","OneMinute");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        // 1분뒤에 AlarmOneMinuteBroadcastReceiver 호출 한다.
        startAlram(context, pendingIntent, ONE_MINUES);
    }

    public void startFiveSecondAlram(Context context) {
        // AlarmOneSecondBroadcastReceiver 초기화
        Intent alarmIntent = new Intent(context, AlarmFiveSecondBroadcastReceiver.class);
        alarmIntent.putExtra("name","FiveSecond");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        // 1초 뒤에 AlarmOneMinuteBroadcastReceiver 호출 한다.
        startAlram(context, pendingIntent, FIVE_SECOND);
    }

    private void startAlram(Context context, PendingIntent pendingIntent, int delay) {

        // AlarmManager 호출
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // 1분뒤에 AlarmOneMinuteBroadcastReceiver 호출 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
        }
    }

    public void startRepeat(Context context, long delay) {
        Intent alramIntent = new Intent(context, AlarmBraodCastReciever.class);
        alramIntent.putExtra("name","Repeat");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1001, alramIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), delay, pendingIntent);
    }
}
