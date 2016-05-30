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
import android.widget.Toast;

public class ListenerService extends Service {

    public static final String NET_ACTION = "netAction";

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
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }


}
