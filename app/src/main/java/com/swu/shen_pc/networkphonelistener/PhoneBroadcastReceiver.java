package com.swu.shen_pc.networkphonelistener;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by shen-pc on 5/30/16.
 */
public class PhoneBroadcastReceiver extends BroadcastReceiver {
    public String incoming_number;
    public boolean incomingFlag;

    @Override
    public void onReceive(Context context, Intent intent) {


//            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
//                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
//                NetworkInfo networkinfo = manager.getActiveNetworkInfo();
//
//                if (networkinfo != null && networkinfo.isAvailable()) {
//                    /**
//                     *  在ConnectivityManager{@link android.net.ConnectivityManager}中的getNetworkTypeName方法
//                     *  类中可以查看网络类型
//                     */
//                    String netTyep = networkinfo.getTypeName();
//                    if ("WIFI".equals(netTyep)) {
//                        Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show();
//                    } else if ("MOBILE".equals(netTyep)) {
////                        Toast.makeText(context, "蜂窝数据", Toast.LENGTH_SHORT).show();
//                    } else if ("BLUETOOTH".equals(netTyep)) {
//                        Toast.makeText(context, "蓝牙", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
//                }
//
//            }else
//            if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
////                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
////                telephonyManager.getCallState();
////                switch (telephonyManager.getCallState()){
////                    case TelephonyManager.CALL_STATE_RINGING:
////                        Toast.makeText(context, "电话来了", Toast.LENGTH_SHORT).show();
////                        break;
////                    case TelephonyManager.CALL_STATE_IDLE:
////                        Toast.makeText(context, "没有电话", Toast.LENGTH_SHORT).show();
////                        break;
////                    case TelephonyManager.CALL_STATE_OFFHOOK:
////                        Toast.makeText(context, "挂断", Toast.LENGTH_SHORT).show();
////                        break;
//
////                }
//                Toast.makeText(context, "电话来了", Toast.LENGTH_SHORT).show();
        //如果是拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        } else {
            //如果是来电
            TelephonyManager tm =
                    (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

            switch (tm.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    incomingFlag = true;//标识当前是来电
                    incoming_number = intent.getStringExtra("incoming_number");
                    Toast.makeText(context, incoming_number, Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (incomingFlag) {
                        Toast.makeText(context, incoming_number, Toast.LENGTH_SHORT).show();
                    }
                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    if (incomingFlag) {
                        Toast.makeText(context, "incoming IDLE", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }


}
