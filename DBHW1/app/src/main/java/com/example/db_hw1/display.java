package com.example.db_hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class display extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Integer> id;
    ArrayList<String> name,six;
    ArrayList<Float> base , total , rate;
    item customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        id = new ArrayList<>();
        name = new ArrayList<>();
        six = new ArrayList<>();
        base = new ArrayList<>();
        total = new ArrayList<>();
        rate = new ArrayList<>();

        recyclerView = findViewById(R.id.rv);

        StoreDataInArray();
        customeAdapter=new item(display.this , id , name , six , base , total , rate);
        recyclerView.setAdapter(customeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(display.this));


    }

    public void StoreDataInArray() {
        SQLiteDatabase myDatabase = null;
        try {
            myDatabase = openOrCreateDatabase("firstDB.db", MODE_PRIVATE, null);
        } catch (Exception e) {
            Toast.makeText(this, "This Database does not Exist", Toast.LENGTH_LONG).show();
        }

        assert myDatabase != null;
        Cursor resutl = myDatabase.rawQuery("select * from employee ;",new String[]{});

        if (resutl.getColumnCount() < 1) {
            Toast.makeText(this, "This Table does not Exist", Toast.LENGTH_LONG).show();
        } else if (resutl.getCount() < 1)
            Toast.makeText(this, "This Employee does not Exist", Toast.LENGTH_LONG).show();

        else {
            while(resutl.moveToNext()) {
                id.add(resutl.getInt(0));
                name.add(resutl.getString(1));
                six.add(resutl.getString(2));
                base.add(resutl.getFloat(3));
                total.add(resutl.getFloat(4));
                rate.add(resutl.getFloat(5));
            }
        }

    }
}



