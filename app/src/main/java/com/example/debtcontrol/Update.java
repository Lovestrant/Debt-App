package com.example.debtcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    Button resetBtn,backToLogin;
    EditText securityKey, password, phoneNum, confirmPassword; DbHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //binding
        resetBtn = findViewById(R.id.resetBtn);
        backToLogin = findViewById(R.id.backToLogin);
        securityKey = findViewById(R.id.securityKey);
        phoneNum = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passConfirm);

        Db = new DbHelper(this);

        //intent to index activity
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //main button

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumbr = phoneNum.getText().toString();
                String Security = securityKey.getText().toString();
                String pass = password.getText().toString();
                String passConfirm =confirmPassword .getText().toString();

                if (phoneNumbr.isEmpty()) {
                    phoneNum.setError("provide phone number");
                }
                if (Security.isEmpty()){
                    securityKey.setError("Provide security key");
                }
                if (pass.isEmpty()){
                    password.setError("Provide password");
                }
                if (passConfirm.isEmpty()) {
                    confirmPassword.setError("Confirm password");
                }

                else {
                    if(pass.equals(passConfirm)) {
                        //check if security key is correct
                        //update credentials

                        Boolean CheckUpdateData = Db.updateUserData(phoneNumbr,pass,Security);
                        if(CheckUpdateData == true) {
                            Toast.makeText(getApplicationContext(), "Update success,You can now login", Toast.LENGTH_SHORT).show();
                            //Directing to login Activity after update

                            securityKey.setText("");
                            phoneNum.setText("");
                            password.setText("");
                            confirmPassword.setText("");

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "Failed! Check your credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password must match with its confirmation", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}