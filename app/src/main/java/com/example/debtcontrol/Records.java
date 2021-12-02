package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Records extends AppCompatActivity {
    DatabaseHelper DB;
    SharedPreferences prf;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        recyclerView = findViewById(R.id.RecycleView);
        //get the object of the Database helper
        DB = new DatabaseHelper(this);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        String phoneNumber = prf.getString("phoneNumber",null);

        ArrayList<Model> dataHolder = new ArrayList<>();

        Cursor checkData = DB.getdata(phoneNumber);
        if(checkData.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data in database", Toast.LENGTH_SHORT).show();
        }else {
            Model obj;
            while (checkData.moveToNext()) {
               String id = checkData.getString(0);
               String  Name = checkData.getString(2);
               String  List = checkData.getString(3);
                String Total = checkData.getString(4);
                obj = new Model(id, Name, List, Total);
                dataHolder.add(obj);
            }

                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(llm);
                myAdapter adapter = new myAdapter(dataHolder);
                recyclerView.setAdapter(adapter);


        }
    }
}