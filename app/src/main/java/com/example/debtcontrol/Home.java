
package com.example.debtcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
   TextView displaySessionvar, logout;
    SharedPreferences prf;
    ImageView addDebtors, EditDebtors,showDebtors,img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDebtors = findViewById(R.id.imageView3);
        EditDebtors = findViewById(R.id.imageView4);
        showDebtors = findViewById(R.id.imageView);
        img5 = findViewById(R.id.imageView5);
        logout = findViewById(R.id.logout);
        displaySessionvar = findViewById(R.id.DisplaySession);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);

        displaySessionvar.setText(" Hi Your accId is: "+prf.getString("phoneNumber",null));

        addDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AddDebtor.class);
                startActivity(intent);
            }

        });

        EditDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });

        showDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Records.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                //Direct To Main Activity

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Log out success", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
