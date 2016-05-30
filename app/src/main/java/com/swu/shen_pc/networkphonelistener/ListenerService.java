package com.swu.shen_pc.networkphonelistener;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class ListenerService extends Service {


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
      

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = manager.getActiveNetworkInfo();

                if (networkinfo != null && networkinfo.isAvailable()) {
                    /**
                     *  在ConnectivityManager{@link android.net.ConnectivityManager}中的getNetworkTypeName方法
                     *  类中可以查看网络类型
                     */
                    String netTyep = networkinfo.getTypeName();
                    if ("WIFI".equals(netTyep)) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show();
                    } else if ("MOBILE".equals(netTyep)) {
                        Toast.makeText(context, "蜂窝数据", Toast.LENGTH_SHORT).show();
                    } else if ("BLUETOOTH".equals(netTyep)) {
                        Toast.makeText(context, "蓝牙", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                }

            } else if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

                //这是你拨打的电话号码
                Toast.makeText(context, intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER), Toast.LENGTH_SHORT).show();

            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
                telephonyManager.getCallState();
                switch (telephonyManager.getCallState()) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        Toast.makeText(context, "电话来了", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        Toast.makeText(context, "没有电话", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Toast.makeText(context, "接听", Toast.LENGTH_SHORT).show();
                        break;

                }
                Toast.makeText(context, "电话来了", Toast.LENGTH_SHORT).show();
            }

        }
    };

    public ListenerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }


}
