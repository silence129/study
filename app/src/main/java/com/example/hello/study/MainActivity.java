package com.example.hello.study;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.hello.study.activity.CameraActivity;
import com.example.hello.study.activity.ContactActivity;
import com.example.hello.study.activity.FactorActivity;
import com.example.hello.study.activity.FullscreenActivity;
import com.example.hello.study.activity.GpsActivity;
import com.example.hello.study.activity.JPushActivity;
import com.example.hello.study.activity.JsonActivity;
import com.example.hello.study.activity.OkhttpActivity;
import com.example.hello.study.activity.WebviewActivity;
import com.example.hello.study.util.DbHelper;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Button button_save_to_file;
    Button button_save_to_preferences;
    Button button_save_to_sqlite;
    Button button_open_contacts;
    Button button_send_notification;
    Button button_camera;
    Button button_open_webview;
    Button button_json;
    Button button_gps;
    Button button_factor;
    Button button_jpush;
    Button button_okhttp;

    DbHelper dbHelper;

    private static final int REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button_save_to_file = (Button)findViewById(R.id.button_svae_to_file);
        button_save_to_preferences = (Button)findViewById(R.id.button_svae_to_perefences);
        button_save_to_sqlite = (Button)findViewById(R.id.button_svae_to_sqlite);
        button_open_contacts = (Button)findViewById(R.id.button_open_contacts);
        button_send_notification = (Button)findViewById(R.id.button_send_notice);
        button_camera = (Button)findViewById(R.id.button_camera);
        button_open_webview = (Button)findViewById(R.id.button_webview);
        button_json = (Button)findViewById(R.id.button_json);
        button_gps = (Button)findViewById(R.id.button_gps);
        button_factor = (Button)findViewById(R.id.button_factor);
        button_jpush = (Button)findViewById(R.id.button_jpush);
        button_okhttp = (Button)findViewById(R.id.button_okhttp);

        button.setOnClickListener(this);
        button_save_to_file.setOnClickListener(this);
        button_save_to_preferences.setOnClickListener(this);
        button_save_to_sqlite.setOnClickListener(this);
        button_open_contacts.setOnClickListener(this);
        button_send_notification.setOnClickListener(this);
        button_camera.setOnClickListener(this);
        button_open_webview.setOnClickListener(this);
        button_json.setOnClickListener(this);
        button_gps.setOnClickListener(this);
        button_factor.setOnClickListener(this);
        button_jpush.setOnClickListener(this);
        button_okhttp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_okhttp:
                OkhttpActivity.startActivity(this);
                break;
            case R.id.button_jpush:
                JPushActivity.startActivity(this);
                break;
            case R.id.button_factor:
                FactorActivity.startActivity(this);
                break;
            case R.id.button_webview:
                WebviewActivity.startAcivity(this);
                break;
            case R.id.button_gps:
                GpsActivity.startActivity(this);
                break;
            case R.id.button_json:
                JsonActivity.startActivity(this);
                break;
            case R.id.button_camera:
                Intent openCameraIntent = new Intent(this,CameraActivity.class);
                startActivity(openCameraIntent);
                break;
            case R.id.button:
                Intent intent = new Intent("com.example.hello.BROADCAST");
                sendBroadcast(intent);
                break;
            case R.id.button_send_notice:

                break;
            case R.id.button_open_contacts:
                Intent intentContact = new Intent(this,ContactActivity.class);
                startActivity(intentContact);
                break;
            case R.id.button_svae_to_file:
                saveToFile("test","test");
                break;
            case R.id.button_svae_to_perefences:
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("test","test");
                editor.commit();
                Toast.makeText(this,"save to sharedpreference",Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                String content = sharedPreferences.getString("test","null");
                Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_svae_to_sqlite:
                dbHelper = new DbHelper(this,"book",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("name","android study");
                values.put("pages",111);
                values.put("author","tom");
                db.insert("book",null,values);
                Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();

                values.clear();
                values.put("name","android study2");
                values.put("pages",111);
                values.put("author","tom2");
                db.insert("book",null,values);
                Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();

                values.clear();
                values.put("author","not tom");
                db.update("book",values,"id=?",new String[]{"1"});

                Cursor countCurse = db.rawQuery("select count(*) from book",null);
                if(countCurse.moveToFirst()){
                    int count = countCurse.getInt(0);
                    Toast.makeText(this,"count" + count,Toast.LENGTH_SHORT).show();
                }

                db.delete("book","id>?",new String[]{"2"});
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();

                Cursor cursor = db.query("book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));

                        String hints = String.format("name:%s,author:%s,pages:%s", name, author, pages);
                        Toast.makeText(this, hints, Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                Toast.makeText(this,"create database success",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void saveToFile(String name,String content){

        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;

        try {
            fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            writer.write(content);
            Toast.makeText(this,"save to file success",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
        }
        finally {
            if(writer != null){
                try{
                    writer.close();
                } catch (Exception e){

                }
            }
        }
    }
}
