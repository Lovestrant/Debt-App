package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddDebtor extends AppCompatActivity {
    Button add;
    EditText name,Total,list;
    DatabaseHelper DB;
    SharedPreferences prf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);

        add= findViewById(R.id.btn_add);
        Total = findViewById(R.id.add_amount);
        list = findViewById(R.id.add_items);
        name = findViewById(R.id.add_name);

        DB = new DatabaseHelper(this);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String SumOfItems = Total.getText().toString();
                String ItemList = list.getText().toString();
                // int id = Integer.parseInt(null);

                //Get session variable PhoneNumber
                prf = getSharedPreferences("user_details",MODE_PRIVATE);
                String sellerPhone =  prf.getString("phoneNumber",null);

                if (Name.isEmpty()){
                    name.setError("Enter name");
                }
                if (ItemList.isEmpty()){
                    list.setError("Provide items");
                }
                if (SumOfItems.isEmpty()){
                    Total.setError("Total cannot be null");
                }
                else {
                    Boolean checkInsertedData = DB.insertUserData(sellerPhone, Name, ItemList, SumOfItems);
                    if(checkInsertedData == true) {
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        list.setText("");
                        Total.setText("");
                        Intent intent=new Intent(getApplicationContext(),Records.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), " Insertion Failed...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }
}