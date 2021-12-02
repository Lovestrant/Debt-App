package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    TextView displayList, displayTotal,displayName;
    String name;
    EditText list,DebtorName;
    EditText total;DatabaseHelper DB;
    Button edit;
    String Name;
    String List;
    String Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        displayName = findViewById(R.id.displayName);
        displayList = findViewById(R.id.displayList);
        displayTotal = findViewById(R.id.displayTotal);
        DebtorName = findViewById(R.id.DebtorName);

        list = findViewById(R.id.mylist);
        total = findViewById(R.id.mytotal);
        edit = findViewById(R.id.edit);




        DB = new DatabaseHelper(this);

        //Code to display Record by Id and match to front End
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        Cursor cursor = DB.CheckData(id);
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {

                 Name = cursor.getString(2);
                 List = cursor.getString(3);
                 Total = cursor.getString(4);
                //Map data to textViews
                displayName.setText(Name);
                displayList.setText(List);
                displayTotal.setText(Total);

                list.setText(List);
                DebtorName.setText(Name);

            }
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get phonenumber from session
                SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);
                String phone = prf.getString("phoneNumber",null);
                //Get Input values
                List=list.getText().toString();
                 Total = total.getText().toString();

                //Logic to Edit
                if (Total.isEmpty()){
                    total.setError("Total amount is required");
                    total.requestFocus();
                    return;
                }
                else
                {
                    Boolean updateUserData = DB.updateUserData(id,phone,Name,List,Total);
                    if(updateUserData == true) {
                        Toast.makeText(getApplicationContext(), "Edit Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Records.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Edit Failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}