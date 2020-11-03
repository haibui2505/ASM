package com.example.pk01537_duy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaoBaiHat extends AppCompatActivity {

    EditText tenBaiHat, tacGia, loiBaiHat;
    Button btnBaiHat;
    DatabaseHelper db;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thembaihat);

        ImageView imgback = findViewById(R.id.imgback);


        tenBaiHat = findViewById(R.id.edt_tenBaiHat);
        tacGia = findViewById(R.id.edt_tacGia);
        loiBaiHat = findViewById(R.id.edt_loiBaiHat);
//        btnBaiHat = findViewById(R.id.btn_BaiHat);


        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();


        db = new DatabaseHelper(TaoBaiHat.this, "new.sqlite", null, 1);

        ImageView img = findViewById(R.id.imgabc);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TaoBaiHat.this, "Chức năng sắp được bổ sung!", Toast.LENGTH_SHORT).show();

            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TaoBaiHat.this);
                builder.setMessage("Đồng ý thoát?");
                builder.setCancelable(false);
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TaoBaiHat.this.onBackPressed();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });

//        btnBaiHat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = tenBaiHat.getText().toString().trim();
//                String tac = tacGia.getText().toString().trim();
//                String loi = loiBaiHat.getText().toString().trim();
//
//                if (name.equals("") || tac.equals("") || loi.equals("")) {
//                    Toast.makeText(TaoBaiHat.this, "Không để trống!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Date date1 = new Date();
//                    final String date = simpleDateFormat.format(date1);
//                    Log.d("duy", " " + name + " " + tac + " " + loi + " " + date);
//                    long val = db.addBaiHat(name, tac, loi, date);
//                    if (val > 0) {
//                        Toast.makeText(TaoBaiHat.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        TaoBaiHat.this.onBackPressed();
//                    } else
//                        Toast.makeText(TaoBaiHat.this, "Thêm hàng gửi thất bại!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


    }
}