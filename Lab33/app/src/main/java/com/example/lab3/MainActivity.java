package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        list = new ArrayList<>();

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.btnHis).setOnClickListener(this);
        findViewById(R.id.btnBook).setOnClickListener(this);
    }

//    Date callDayTime = new Date(Long.valueOf(callDate));

    private void loadContacts() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.clear();
        String s = "";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                s = "";
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (cursor1.moveToNext()) {
                        String phoneNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        s += "" + id + ", " + name + "\n" + phoneNumber;
                        list.add(s);
                    }
                    cursor1.close();
                }
            }
        }
        cursor.close();
        lv.setAdapter(adapter);
    }

    private void bookMark() {

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.clear();
//        String s = "";
        Uri uri = Uri.parse("content://browser/bookmarks");
        String[] his = new String[]{
                "_id",
                "url",
                "title",
        };
        Cursor cursor = getContentResolver().query(uri, his, null, null, null);
        cursor.moveToNext();
        while (cursor.isAfterLast() == false) {
            String s = "";
            s += cursor.getString(1) + " - " + cursor.getString(2);
            list.add(s);
            s = "";
            cursor.moveToNext();
        }
        cursor.close();
        lv.setAdapter(adapter);
    }


    private void historyCall() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.clear();
        String[] projection = new String[]{
                CallLog.Calls.DATE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };
        Cursor c = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                projection,
                CallLog.Calls.DURATION + "<?", new String[]{"30"},
                CallLog.Calls.DATE + " Asc");
        c.moveToFirst();
        String s = "";
        while (c.isAfterLast() == false) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + " - ";
            }
            s += "\n";
            list.add(s);
            s = "";
            c.moveToNext();
        }
        c.close();
        lv.setAdapter(adapter);
    }

    public void accessMediaStore() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.clear();
        String[] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };
        CursorLoader loader = new CursorLoader
                (this, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        projection, null, null, null);
        Cursor c = loader.loadInBackground();
        c.moveToFirst();
        String s = "";
        while (!c.isAfterLast()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + " - ";
            }
            s += "\n";
            c.moveToNext();
        }
        c.close();
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                loadContacts();
                break;
            case R.id.btnBook:
                bookMark();
                break;
            case R.id.btnHis:
                historyCall();
//                accessMediaStore();
                break;
        }
    }
}