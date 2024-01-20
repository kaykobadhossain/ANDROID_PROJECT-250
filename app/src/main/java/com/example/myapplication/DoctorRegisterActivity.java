package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorRegisterActivity extends AppCompatActivity {

    EditText name,email,phone,password,confirmPassword;
    Button registerButton;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        name=findViewById(R.id.editTextRegisterName);
        email=findViewById(R.id.editTextText3);
        phone=findViewById(R.id.editTextRegisterPhone);
        password=findViewById(R.id.editTextTextRegisterPassword);
        confirmPassword=findViewById(R.id.editTextTextRegiterConfirmPassword);
        registerButton=findViewById(R.id.btnRegister);
        reg=findViewById(R.id.textViewRegisterLogin);

        registerButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String n=name.getText().toString();
                String e=email.getText().toString();
                String ph=phone.getText().toString();
                String p=password.getText().toString();
                String cp=confirmPassword.getText().toString();
                Database db =new Database(getApplicationContext(),"anihelp",null,1);



                if (name.length()==0 || email.length()==0 ||phone.length()==0 || password.length()==0 || confirmPassword.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(p.equals(cp))
                    {
                        if(isValid(p))
                        {
                            db.register(n,e,p);
                            Toast.makeText(getApplicationContext(),"registered successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DoctorRegisterActivity.this, LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters having letter,digit and special character ",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password ans Confirm Password did not match",Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),"Redirecting to Login Page",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorRegisterActivity.this, LoginActivity.class));
            }
        });

    }

    public static boolean isValid(String password)
    {
        int f1=0,f2=0,f3=0;

        if (password.length()<8)
            return false;
        else
        {
            for (int p=0;p<password.length();p++)
            {
                if(Character.isLetter(password.charAt(p))) f1=1;
            }
            for (int p=0;p<password.length();p++)
            {
                if(Character.isDigit(password.charAt(p))) f2=1;
            }
            for (int p=0;p<password.length();p++)
            {
                char c=password.charAt(p);
                if(c>=33 && c<=46 || c==64 ) f3=1;
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }


}