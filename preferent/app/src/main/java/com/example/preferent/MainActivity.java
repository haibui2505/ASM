package com.example.preferent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final EditText editText = findViewById(R.id.editTextTextPersonName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ed = editText.getText().toString().trim();
                if(ed.equals("")){
                    Toast.makeText(MainActivity.this, "Khoong trong", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, Broadcast.class);
                    intent.putExtra("txt",ed);
                    sendBroadcast(intent);
                }
            }
        });

//        Intent intent = new Intent(MainActivity.this, Broadcast.class);
//        intent.getAction();
//        sendBroadcast(intent);
    }
}