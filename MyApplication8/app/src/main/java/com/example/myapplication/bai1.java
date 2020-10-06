package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class bai1 extends AppCompatActivity {
    Button btnStart, btnStop;
    EditText edt1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        btnStart = findViewById(R.id.button);
        btnStop = findViewById(R.id.button1);
        final Intent intent = new Intent(bai1.this, MyService.class);
         edt1 = findViewById(R.id.edt1);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edt1.getText().toString().trim();
                Bundle b = new Bundle();
                    b.putInt("StuId", 100);
                    b.putString("Name", name);
                    b.putString("Class", "PK01120");
                    intent.putExtra("hai", b);
                    startService(intent);

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });
    }
}