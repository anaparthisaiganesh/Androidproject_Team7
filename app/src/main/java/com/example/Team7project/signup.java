package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    EditText name,age,mobile,gender,email,password,confirmPassword;
    TextView loginRedirectTextView;
    Button signupButton;
    FirebaseAuth mAuth;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i =new Intent(this,Login.class);
            startActivity(i);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name_edit_text_view);
        age=findViewById(R.id.age_edit_text_view);
        mobile=findViewById(R.id.mobile_edit_text_view);
        gender=findViewById(R.id.gender_edit_text_view);
        email=findViewById(R.id.email_edit_text_view);
        password=findViewById(R.id.password_edit_text_view);
        confirmPassword=findViewById(R.id.confirmPassword_edit_text_view);
        loginRedirectTextView=findViewById(R.id.loginText);
        signupButton =findViewById(R.id.signUp_btn);

        mAuth=FirebaseAuth.getInstance();

        loginRedirectTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr=name.getText().toString().trim();
                String ageStr=age.getText().toString();
                String mobileStr=mobile.getText().toString();
                String genderStr=gender.getText().toString().trim();
                String emailStr=email.getText().toString().trim();
                String passwordStr=password.getText().toString().trim();
                String conformPasswordStr=confirmPassword.getText().toString().trim();

                if(nameStr.length() ==0 || ageStr.length() ==0 || mobileStr.length() ==0 || genderStr.length() ==0 || emailStr.length() ==0 || passwordStr.length() ==0 || conformPasswordStr.length() ==0){
                    Toast.makeText(signup.this, "Fill All Field's", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!(passwordStr.equals(conformPasswordStr))){
                    Toast.makeText(signup.this, "Password and Confirm password Should Be Same", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user in firebase
                mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser FirebaseUser=mAuth.getCurrentUser();

                                    ReadWriteUserDetails itemDetailsObject=new ReadWriteUserDetails(nameStr,ageStr,mobileStr,genderStr,false);

                                    DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registered Users");
                                    referenceProfile.child(FirebaseUser.getUid()).setValue(itemDetailsObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_SHORT).show();
                                                FirebaseUser user=mAuth.getCurrentUser();
                                                Intent i =new Intent(getApplicationContext(),Login.class);
                                                startActivity(i);
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),"Failed To Save Data",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"Sign Up Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}