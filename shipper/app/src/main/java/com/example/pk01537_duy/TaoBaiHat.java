package com.example.pk01537_duy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TaoBaiHat extends AppCompatActivity {

    EditText tenBaiHat, tacGia, loiBaiHat;
    Button btnBaiHat, btnTinhtrang;
    DatabaseHelper db;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    ListView lv;
    ArrayList<String> arrayList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thembaihat);

        ImageView imgback = findViewById(R.id.imgback);
        arrayList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

        btnBaiHat = findViewById(R.id.button);
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
        btnBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("tinhTrang");
                Query query = reference.orderByChild("tinhTrangg");
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        arrayList.add(dataSnapshot.getValue().toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        findViewById(R.id.btnTrangthai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("tinhTrang");
                EditText edtMa = findViewById(R.id.edtMadonhang);
                EditText edtTrang = findViewById(R.id.edtTrangthai);
                String maDonHang = edtMa.getText().toString().trim();
                String trangThai = edtTrang.getText().toString().trim();
                if (!maDonHang.equals("") && !trangThai.equals("")) {
                    reference.child("1").child(maDonHang).child("trangThai").setValue(trangThai);
                } else {
                    Toast.makeText(TaoBaiHat.this, "vui lòng nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                }

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