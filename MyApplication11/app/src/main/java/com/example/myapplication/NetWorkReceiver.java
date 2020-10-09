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
        NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean mobile = networkInfo1.isConnected();

        if (MainActivity.WIFI.equals(MainActivity.sPref) && networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && mobile) {
            MainActivity.refreshDisplay = true;
            Toast.makeText(context, R.string.all, Toast.LENGTH_SHORT).show();
        } else if (MainActivity.ANY.equals(MainActivity.sPref) && networkInfo != null) {
            MainActivity.refreshDisplay = true;
        } else if (MainActivity.WIFI.equals(MainActivity.sPref) && networkInfo != null && networkInfo.getType() != ConnectivityManager.TYPE_WIFI && mobile){
            MainActivity.refreshDisplay = false;
            Toast.makeText(context, "Chua ket noi Wifi!", Toast.LENGTH_SHORT).show();
        } else if(MainActivity.WIFI.equals(MainActivity.sPref) && networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && !mobile){
            Toast.makeText(context, "chua ket noi 3G!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Khong co ket noi nao!", Toast.LENGTH_SHORT).show();
        }

    }
}
