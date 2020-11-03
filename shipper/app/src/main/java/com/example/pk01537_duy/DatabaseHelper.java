package com.example.pk01537_duy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "new.sqlite";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public long addBaiHat( String ten, String tac, String loi,  String ngay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenBaiHat", ten);
        contentValues.put("TacGia", tac);
        contentValues.put("LoiBaiHat", loi);
        contentValues.put("NgayGui", ngay);

        long res = db.insert("BaiHat", null, contentValues);
        db.close();
        return res;

    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", user);
        contentValues.put("Pass", password);
        long res = db.insert("DangNhap", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = db.rawQuery("Select * from DangNhap where Ten = ? ", new String[]{username});

        int count = cursor1.getCount();
        cursor1.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getTenNguoiDung(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DangNhap where Ten = ?", new String[]{username});
        return cursor;
    }


    public boolean check(String name, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from DangNhap where Ten=? and Pass=?", new String[]{name, pass});

        if (cursor.getCount() > 0) return true;
        else return false;
    }
}
