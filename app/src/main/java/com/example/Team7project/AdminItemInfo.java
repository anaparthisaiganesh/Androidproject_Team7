package com.example.Team7project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminItemInfo extends AppCompatActivity {

    TextView itemName,itemDescription,itemPrice;
    ImageView itemImage;
    Button contact_owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_info);

        itemName=findViewById(R.id.item_name_tv);
        itemDescription=findViewById(R.id.description_tv);
        itemPrice=findViewById(R.id.price_tv);
        itemImage=findViewById(R.id.item_img_view);
        contact_owner=findViewById(R.id.contact_owner_Btn);

        Intent i=getIntent();
        String name=i.getStringExtra("name");
        String description=i.getStringExtra("description");
        String price=i.getStringExtra("price");
        String ownerkey=i.getStringExtra("ownerkey");
        String postkey=i.getStringExtra("postuid");
        String imgUrl=i.getStringExtra("imgUrl");
        itemName.setText("Item: "+name);
        itemDescription.setText(description);
        itemPrice.setText("price: "+price+"$");
        

        contact_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Posts").child(ownerkey).child(postkey).setValue(null);
                Intent i=new Intent(getApplicationContext(),StoreOwner.class);
                startActivity(i);
                finish();
            }
        });


    }
}
