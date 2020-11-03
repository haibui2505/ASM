package com.example.pk01537_duy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrangChu extends AppCompatActivity {

    FloatingActionButton fab, fabAddNew, fabContact;
    boolean spr = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pho_bien);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new phoBien();
                    break;
                case R.id.nav_don:
                    fragment = new babihat_Frag();
                    break;
                case R.id.nav_money:
                    fragment = new khoaHoc();
                    break;
                case R.id.nav_note:
                    fragment = new playList();
                    break;
                case R.id.nav_danh:
                    fragment = new yeuThich();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    fragment).commit();
            return true;
        }
    };

}