package com.example.hello.study.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hello.study.R;
import com.example.hello.study.model.Factor;
import com.example.hello.study.util.DbHelper;
import com.example.hello.study.util.IotHttpHandler;

import java.util.ArrayList;
import java.util.List;

public class FactorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FactorActivity";

    public static void startActivity(Context context) {

        Log.d(TAG, "startActivity");
        Intent intent = new Intent(context, FactorActivity.class);
        context.startActivity(intent);
    }

    private List<Factor> factorList;
    private Button button_http;
    private Button button_store;
    private Button button_sqlite;
    private ListView listView;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factor);

        button_http = (Button)findViewById(R.id.button_factor_http);
        button_store = (Button)findViewById(R.id.button_factor_store_to_sqlite);
        button_sqlite = (Button)findViewById(R.id.button_factor_sqlite);
        listView = (ListView)findViewById(R.id.list_factor);

        button_http.setOnClickListener(this);
        button_store.setOnClickListener(this);
        button_sqlite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_factor_http:
                factorList = IotHttpHandler.getFactores();
                showFactores();
                break;
            case R.id.button_factor_store_to_sqlite:
                storeToSqlite();
                break;
            case R.id.button_factor_sqlite:

        }
    }

    protected void showFactores(){

        if(factorList == null){
            Log.d(TAG, "list is null");
            return;
        }

        List<String> list = new ArrayList<>();
        for (Factor factor:factorList) {
            String item = String.format("id:%s,name:%s",factor.getId(),factor.getName());
            Log.d(TAG, item);
            list.add(item);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private void storeToSqlite(){

        if(factorList == null){
            Log.d(TAG, "list is null");
            return;
        }

        dbHelper = new DbHelper(this,"factor",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Factor factor:factorList) {

            ContentValues values = new ContentValues();
            values.put("id",factor.getId());
            values.put("name",factor.getName());
            values.put("alias",factor.getAlias());
            values.put("englishName",factor.getEnglishName());
            values.put("unit",factor.getUnit());
            values.put("icon",factor.getIcon());
            values.put("maxValue",factor.getMaxValue());
            values.put("minValue",factor.getMinValue());
            values.put("pricision",factor.getPricision());
            values.put("standard",factor.getStandard());
            values.put("dataType",factor.getDataType());
            values.put("category",factor.getCategory());
            values.put("note",factor.getNote());

            db.insert("factor",null,values);
            Toast.makeText(this,"store " + factor.getName(),Toast.LENGTH_SHORT).show();
        }
    }
}
