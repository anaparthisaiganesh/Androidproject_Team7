package com.example.Team7project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class PostItem extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    StorageReference storageReference;
    EditText name,price,description,genere,mobile;
    ImageView item_pic;
    ProgressBar progressBar;
    Spinner spinner;
    Button post;
    static final int PICK_IMAGE_REQ=1;
    Uri uriImage;

    String[] genereOptions={"fiction","humor","thriller","selfhelp","politics","fantasy"};
    String generestr;

    public void uploadPic(String ItemName,String descriptionStr, String priceStr, String genere, String mobileNo){
        if(uriImage != null){
            Random r = new Random();
            int i1 = r.nextInt(45000 - 28) + 28;
            StorageReference fileReference = storageReference.child(firebaseUser.getUid()+i1+"."+getFileExtension(uriImage));
            fileReference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                    Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();

                    final String[] image_url = new String[1];
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            image_url[0] =uri.toString();
//                            Log.i("url img",image_url[0]);
                            ReadWriteItemDetails itemDetailsObject = new ReadWriteItemDetails(ItemName, descriptionStr, priceStr, image_url[0],genere,mobileNo);

                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Posts");
                            referenceProfile.child(firebaseUser.getUid()).push().setValue(itemDetailsObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), StoreOwner.class);
                                        startActivity(i);
                                        finish();
//                            Glide.with(getApplicationContext()).load(url).into(imageView);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed To Save Data", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        };

                    });
//                    Log.i("url img",image_url[0]);

//                    String image_url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                }
            });
        }
    }


    public void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQ);
    }


    public String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQ && resultCode==RESULT_OK && data != null && data.getData() != null){
            uriImage = data.getData();
            item_pic.setImageURI(uriImage);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        auth=FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();
        item_pic=findViewById(R.id.item_photo_upload_view);
        storageReference= FirebaseStorage.getInstance().getReference("PostImages");

        Uri uri= firebaseUser.getPhotoUrl();

        Picasso.with(PostItem.this).load(uri);



        if(firebaseUser == null){
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            finish();
        }

        getSupportActionBar().setTitle("Post Item");

        name=findViewById(R.id.name_edit_text);
        price=findViewById(R.id.Item_price_edit_tv);
        description=findViewById(R.id.Item_description_edit_tv);
        spinner=findViewById(R.id.Item_genere_spinner);
        progressBar=findViewById(R.id.progress_bar);
        mobile=findViewById(R.id.mobile);
        post=findViewById(R.id.post_btn);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_file,genereOptions);
        adapter.setDropDownViewResource(R.layout.item_file);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String genereval =adapterView.getItemAtPosition(i).toString();
                setGenereva(genereval);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        item_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ItemName = name.getText().toString();
                String priceStr = price.getText().toString();
                String descriptionStr = description.getText().toString();
                String mobileNumber=mobile.getText().toString();

                if (ItemName.length() == 0 || priceStr.length() == 0 || descriptionStr.length() == 0 || uriImage == null || mobileNumber.length()==0) {
                    Toast.makeText(getApplicationContext(), "Fill All the Above Info", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                try {
                    uploadPic(ItemName,descriptionStr,priceStr,generestr,mobileNumber);
                }

            }
        });

    }

    public void setGenereva(String val){
        generestr=val;
    }

}
