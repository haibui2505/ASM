package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (MainActivity.WIFI.equals(MainActivity.sPref) && networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            MainActivity.refreshDisplay = true;
            Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();
        } else if (MainActivity.ANY.equals(MainActivity.sPref) && networkInfo != null) {
            MainActivity.refreshDisplay = true;
        } else {
            MainActivity.refreshDisplay = false;
            Toast.makeText(context, R.string.lost_connected, Toast.LENGTH_SHORT).show();

        }
    }
}
