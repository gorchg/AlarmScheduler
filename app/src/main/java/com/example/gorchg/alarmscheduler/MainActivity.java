package com.example.gorchg.alarmscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gorchg.alarmscheduler.receiver.AlarmBraodCastReciever;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int count;

    private BroadcastReceiver mTimeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            count++;
            long time = intent.getLongExtra(Constants.KEY_DEFAULT,0);
            String name = intent.getStringExtra("name");
            if (time > 0) {
                Date date = new Date(time);
                textView.setText(
                        textView.getText()+"\n"+
                        name+"\n"+date.toString()+"\n"+"call count : "+count);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_timer);
        Date date = new Date();
        textView.setText(date.toString());

        findViewById(R.id.btn_onebyone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!AlarmBraodCastReciever.isLaunched) {
                    AlarmUtils.getInstance().startFiveSecondAlram(MainActivity.this);
                    AlarmUtils.getInstance().startOneMinuteAlram(MainActivity.this);
                }
            }
        });

        findViewById(R.id.btn_repeat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmUtils.getInstance().startRepeat(MainActivity.this, 30*1000);
//                AlarmUtils.getInstance().startRepeat(MainActivity.this, AlarmManager.INTERVAL_FIFTEEN_MINUTES);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mTimeReceiver,new IntentFilter(Constants.INTENTFILTER_BROADCAST_TIMER));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mTimeReceiver);
    }
}
