package com.example.Team7project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class GenreSelect extends AppCompatActivity {

    GridLayout gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_select);

        gl=findViewById(R.id.grid_layout);

        gl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"Genre Selected", Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

    }
}