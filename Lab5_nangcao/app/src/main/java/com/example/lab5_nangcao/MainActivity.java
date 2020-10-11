package com.example.lab5_nangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView LvRss;
    TextView txt;
    ArrayList<String> titles;
    ArrayList<String> links;
    Button btn;
    EditText edt;
    String a = "";
    ArrayAdapter<String> adapter;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LvRss = findViewById(R.id.lv);
        btn = findViewById(R.id.button);
        edt = findViewById(R.id.edt);
        txt = findViewById(R.id.textView);

        titles = new ArrayList<String>();
        links = new ArrayList<String>();
        new ProcessInBackground().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uri = edt.getText().toString().trim();
                a = uri;
                if (uri.equals("")){
                    txt.setText("Links cannot blank! Please try again!");
                }else if (count == 0){
                    new ProcessInBackground().execute();
                    adapter.clear();
                    txt.setText("Format ERRO links, Please Try again!");
                }else {
                    txt.setText("Load successfully! Thanks to use!");
                    new ProcessInBackground().execute();
                    adapter.clear();
                }


            }
        });


        LvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Uri uri = Uri.parse(links.get(position));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                String link = links.get(position);
                Intent intent = new Intent(MainActivity.this, web.class);
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });


    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Busy loading rss feed...please wait...");
        }

        @Override
        protected Exception doInBackground(Integer... integers) {
            try {
                URL url = new URL(a);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser xmlPullParser = factory.newPullParser();

                xmlPullParser.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int evenType = xmlPullParser.getEventType();

                while (evenType != XmlPullParser.END_DOCUMENT) {
                    if (evenType == XmlPullParser.START_TAG) {
                        if (xmlPullParser.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                titles.add(xmlPullParser.nextText());
                            }
                        } else if (xmlPullParser.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                links.add(xmlPullParser.nextText());
                            }
                        }
                    } else if (evenType == xmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }

                    evenType = xmlPullParser.next();
                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
            LvRss.setAdapter(adapter);
//            adapter.clear();
            progressDialog.dismiss();
            int a  = adapter.getCount();
            count = a;
            if (a != 0){
                txt.setText("Load successfully! Thanks to use!");
            }else {

            }
        }
    }
}