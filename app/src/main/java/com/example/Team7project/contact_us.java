package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class contact_us extends AppCompatActivity {

    EditText name,email,messges;
    Button sendbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        mAuth= FirebaseAuth.getInstance();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email_edit_tv);
        messges=findViewById(R.id.message_edit_tv);
        sendbtn=findViewById(R.id.sendButton);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr=name.getText().toString();
                String emailStr=email.getText().toString();
                String messageStr=messges.getText().toString();

                if(nameStr.length() ==0 || emailStr.length() ==0 || messageStr.length() ==0){
                    Toast.makeText(contact_us.this, "Fill All Field's", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                String uid=currentFirebaseUser.getUid();

                ReadWriteContactDetails itemDetailsObject=new ReadWriteContactDetails(nameStr,emailStr,messageStr);
                DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("contacts");

                referenceProfile.push().setValue(itemDetailsObject).addOnCompleteListener(new OnCompleteListener<Void>() {
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
            }
        });


    }
}
