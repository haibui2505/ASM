package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Wellcome to Service! Service was Started", Toast.LENGTH_SHORT).show();

        Bundle b = intent.getBundleExtra("hai");
        int StuId = b.getInt("StuId");
        String name = b.getString("Name");
        String Class = b.getString("Class");
        int A = demKT(name,'A');
        int B = demKT(name,'B');
        int C = demKT(name,'C');

        if (intent != null) {
            String content = "Thêm sinh viên thành công.\nThông tin sinh viên: \nSinh viên: " +StuId+" " +name;
            content  += "\nLớp: " + Class;
            content +="\nTổng kí tự A là: " + A;
            content +="\nTổng kí tự B là: " + B;
            content +="\nTổng kí tự C là: " + C;
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
//        super.onStartCommand(intent, flags, startId)
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    public int demKT(String chuoi, char kitu){
        int dem = 0;
        for(int i = 0; i < chuoi.length();i++){
            if(chuoi.charAt(i)==kitu){
                dem++;
            }
        }
        return dem;
    }

}
