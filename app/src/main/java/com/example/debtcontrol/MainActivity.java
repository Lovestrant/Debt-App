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

public class MainActivity extends AppCompatActivity {

    Button loginBtn; TextView resetBtn, registerBtn;
    EditText phoneNumber, password;
    DbHelper DB;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.btn_sign);
        resetBtn = findViewById(R.id.forgot_password);
        registerBtn = findViewById(R.id.sign_redirect);
        phoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password_register);

        DB = new DbHelper(this);

//        //check if session variable exists

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        if(pref.contains("phoneNumber")) {
            Toast.makeText(getApplicationContext(), "You still logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        }else {
            //do nothing
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Update.class);
                startActivity(intent);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perform login operation
                String phone = phoneNumber.getText().toString();
                String pass = password.getText().toString();

                if(!phone.equals("") && !pass.equals("")) {
                    Boolean CheckGetCredentials = DB.checkUser(phone,pass);

                    if(CheckGetCredentials == true) {


                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("phoneNumber",phone);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();

                        password.setText("");
                        phoneNumber.setText("");
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);



                    }else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
                else {

                    if (phone.isEmpty()) {
                        phoneNumber.setError("provide phone number");
                    }
                    if (pass.isEmpty()){
                        password.setError("Provide password");
                    }
                }


            }
        });



    }
}