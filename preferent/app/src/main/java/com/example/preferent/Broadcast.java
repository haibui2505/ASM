package com.example.preferent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            Toast.makeText(context, "SDT gọi: " + intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
        }
        String txt = intent.getStringExtra("txt");
        if (txt != null){
            if (txt.equals("MEM123")) {
                Toast.makeText(context, "Khuyến mãi 10%", Toast.LENGTH_SHORT).show();
            } else if (txt.equals("VIP123")) {
                Toast.makeText(context, "Khuyến mãi 30%", Toast.LENGTH_SHORT).show();
            } else if (txt.substring(0, 3).equals("MEM")) {
                Toast.makeText(context, "Khuyến mãi từ 10% đến 50%", Toast.LENGTH_SHORT).show();
            } else if (txt.substring(0, 3).equals("VIP")) {
                Toast.makeText(context, "Khuyến mãi từ 30% đến 60%", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Không khuyến mãi bạn ơi!", Toast.LENGTH_SHORT).show();
        }


//            Toast.makeText(context, "aaa", Toast.LENGTH_SHORT).show();
//            String state  = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//
//            if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)){
//                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//                Log.d("hai","phonenumber: " + phoneNumber);
//            }else Log.d("hai", "State: " + state);

    }
}
