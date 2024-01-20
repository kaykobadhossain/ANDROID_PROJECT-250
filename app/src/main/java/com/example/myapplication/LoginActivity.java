package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginName,loginPassword;
    Button loginButton;
    TextView newRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName=findViewById(R.id.editTextLoginName);
        loginPassword=findViewById(R.id.editTextTextPasswordLogin);
        loginButton=findViewById(R.id.btnLogin);
        newRegister=findViewById(R.id.textViewRegisterLogin);


        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DoctorRegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=loginName.getText().toString();
                String Password=loginPassword.getText().toString();
                Database db =new Database(getApplicationContext(),"anihelp",null,1);


                if (userName.length()==0 || Password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (db.login(userName,Password)==1)
                    {
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedpreferences =getSharedPreferences("shared_pres", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedpreferences.edit();
                        editor.putString("username",userName);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Username And Password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }
}