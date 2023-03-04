package com.example.Team7project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        login=findViewById(R.id.loginButton);
        signUp=findViewById(R.id.signupText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT);
                toast.show();
                String uname=username.getText().toString();
                String upass=password.getText().toString();
                Log.i("uname", uname+" "+upass);
                if(uname.equals("owner")){
                    Intent i = new Intent(getApplicationContext(),StoreOwner.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(getApplicationContext(),GenreSelect.class);
                    startActivity(i);
                }}
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"sign up", Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
        });
    }

}