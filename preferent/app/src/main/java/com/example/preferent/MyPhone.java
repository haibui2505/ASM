package com.example.preferent;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyPhone extends PhoneStateListener {
    Context pcontext;
    public MyPhone(Context context){
        this.pcontext = context;
    }

    @Override
    public void onCallStateChanged(int state, String phoneNumber) {
        if(state == 1){
            Toast.makeText(pcontext, "Số điện thoại gọi đến: " + phoneNumber, Toast.LENGTH_SHORT).show();
        }
        super.onCallStateChanged(state, phoneNumber);
    }
}
