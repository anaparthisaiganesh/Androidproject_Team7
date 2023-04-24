package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class GenreSelect extends AppCompatActivity {

    GridLayout gl;
    ImageView fiction,humor,thriller,selfhelp,politics,fantasy;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_select);

        gl=findViewById(R.id.grid_layout);
        fiction=findViewById(R.id.fiction_image_view);
        humor=findViewById(R.id.humor_image_view);
        thriller=findViewById(R.id.thriller_image_view);
        selfhelp=findViewById(R.id.selfhelp_image_view);
        politics=findViewById(R.id.politics_image_view);
        fantasy=findViewById(R.id.fantasy_image_view);

        auth= FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();

        if(firebaseUser==null){
            Intent intentBack=new Intent(getApplicationContext(),Login.class);
            startActivity(intentBack);
            finish();
        }

        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","fiction");
                startActivity(i);
            }
        });

        humor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","humor");
                startActivity(i);
            }
        });

        thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","thriller");
                startActivity(i);
            }
        });

        selfhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","selfhelp");
                startActivity(i);
            }
        });

        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","politics");
                startActivity(i);
            }
        });

        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("genere","fantasy");
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);

        MenuItem logoutItem=menu.findItem(R.id.action_logout);

        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                FirebaseAuth.getInstance().signOut();
                
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
