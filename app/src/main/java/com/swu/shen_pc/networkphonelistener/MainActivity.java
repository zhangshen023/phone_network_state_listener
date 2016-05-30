package com.swu.shen_pc.networkphonelistener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, ListenerService.class);
        startService(mIntent);
    }
}
