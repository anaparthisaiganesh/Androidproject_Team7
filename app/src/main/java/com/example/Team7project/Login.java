package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView signUp;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    DatabaseReference readRef;
    DatabaseReference dref;
    FirebaseUser user;


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            progressBar=findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            goToHomePage();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username_edit_text);
        password=findViewById(R.id.password_edit_text);
        login=findViewById(R.id.login_btn);
        signUp=findViewById(R.id.signupText);
        progressBar=findViewById(R.id.progress_bar);

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = username.getText().toString();
                String passwordStr = password.getText().toString();

                if( emailStr.length() ==0 || passwordStr.length() ==0){
                    Toast.makeText(Login.this, "Fill All Field's", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check user  exist or not authentication
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    goToHomePage();
                                }

                                else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),signup.class);
                startActivity(i);

            }
        });
    }

    public void goToHomePage(){
        user = mAuth.getCurrentUser();
        readRef = FirebaseDatabase.getInstance().getReference("Registered Users");
        dref = readRef.child(user.getUid());

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                if (dataSnapshot.hasChildren()) {
                    if ((dataSnapshot.child("storeowner").getValue().toString()) == "true") {
                        Intent i = new Intent(getApplicationContext(), StoreOwner.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(getApplicationContext(), GenreSelect.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}