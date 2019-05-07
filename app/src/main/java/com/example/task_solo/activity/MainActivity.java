package com.example.task_solo.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.task_solo.R;

public class MainActivity extends AppCompatActivity {

//    public final static String BROADCAST_ACTION ="BROADCAST_ACTION";
//    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
//        registerReceiver(broadcastReceiver,intFilt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
    }
}
