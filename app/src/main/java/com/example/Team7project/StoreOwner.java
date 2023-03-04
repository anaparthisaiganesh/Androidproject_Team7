package com.example.Team7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StoreOwner extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<BookItem> bookitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner);

        bookitem=new ArrayList<>();
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.fantasy));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.fiction));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.humor));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.thriller));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.politics));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.self_help));
        bookitem.add(new BookItem("Book","Descrption1","Price1","Date:03-03-2023",R.drawable.fiction));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BookAdapter(bookitem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }



}