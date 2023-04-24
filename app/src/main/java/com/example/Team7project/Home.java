package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Home extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<BookItem> bookitem;
    public DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bookitem=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Posts");


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BookAdapter(bookitem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

//        progressBar = findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.VISIBLE);

        auth=FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();

        if(firebaseUser==null){
            Intent intentBack=new Intent(getApplicationContext(),Login.class);
            startActivity(intentBack);
            finish();
        }

        Intent intent = getIntent();
        String genereSelected= intent.getStringExtra("genere");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                        ReadWriteItemDetails post = dataSnapshot1.getValue(ReadWriteItemDetails.class);
                        if (post.genere.equals(genereSelected)) {
                            Date dt = new Date();
                            SimpleDateFormat dateFormat;
                            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String date = "" + dateFormat.format(dt);

                            bookitem.add(new BookItem(post.name, post.description, post.price, date, post.Image_url,post.mobile));
                        }
                    }

                }
//                progressBar.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position){
                Intent i=new Intent(getApplicationContext(),item_info.class);
                i.putExtra("name",bookitem.get(position).getItemName());
                i.putExtra("description",bookitem.get(position).getItemDescription());
                i.putExtra("price",bookitem.get(position).getItemPrice());
                i.putExtra("date",bookitem.get(position).getItemDate());
                i.putExtra("imgUrl",bookitem.get(position).getImageUrl());
                i.putExtra("phone",bookitem.get(position).getMobileNumber());
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        MenuItem AboutItem=menu.findItem(R.id.action_about_app);
        MenuItem ContactItem=menu.findItem(R.id.action_contact_us);



        AboutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent i = new Intent(getApplicationContext(),About_Us.class);
                startActivity(i);
                return false;
            }
        });


        ContactItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent i = new Intent(getApplicationContext(),contact_us.class);
                startActivity(i);
                return false;
            }
        });


        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<BookItem> filteredbookitem=new ArrayList<>();
                for(BookItem book:bookitem){
                    if(book.getItemName().toLowerCase().contains(s.toLowerCase(


                    ))){
                        filteredbookitem.add(book);
                    }

                }
                mAdapter = new BookAdapter(filteredbookitem);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position){
                        Intent i=new Intent(getApplicationContext(),item_info.class);
                        i.putExtra("name",filteredbookitem.get(position).getItemName());
                        i.putExtra("description",filteredbookitem.get(position).getItemDescription());
                        i.putExtra("price",filteredbookitem.get(position).getItemPrice());
                        i.putExtra("date",filteredbookitem.get(position).getItemDate());
                        
                    }
                });

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
