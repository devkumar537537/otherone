package com.example.myfiretfirebaseconcetpe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class UploadImage extends AppCompatActivity {

    ImageView imageView;
    Button chooseFromgallary,choosefromCamera,uploadimagebtn;
    ProgressBar progressBar;
 Uri imageuri;
    String[] permissions ={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    int permission = 123;
    int gallaryrequest = 32;
    int camerarequest=33;

    FirebaseAuth firebaseAuth;
    String userid;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        combine();
firebaseAuth = FirebaseAuth.getInstance();
 userid = firebaseAuth.getCurrentUser().getUid();
storageReference = FirebaseStorage.getInstance().getReference("registerimage").child(userid);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,permission);
            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromCamera.setVisibility(View.VISIBLE);
                chooseFromgallary.setVisibility(View.VISIBLE);
                uploadimagebtn.setVisibility(View.VISIBLE);
            }
        });

        chooseFromgallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromCamera.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,gallaryrequest);
            }
        });

        choosefromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFromgallary.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,camerarequest);
            }
        });

        uploadimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromCamera.setVisibility(View.GONE);
                chooseFromgallary.setVisibility(View.GONE);

                if(imageuri != null)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    uploadimage();
                }
                uploadimagebtn.setVisibility(View.GONE);
            }
        });
    }

    private void uploadimage() {
        storageReference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                   @Override
                   public void onComplete(@NonNull Task<Uri> task) {
                      String url = task.getResult().toString();

                       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RegisterImage").child(userid);

                       HashMap<String,String> imagemap =new HashMap<>();
                       imagemap.put("imageurl",url);
                       imagemap.put("userid",userid);

                       databaseReference.setValue(imagemap).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful())
                               {
                                   progressBar.setVisibility(View.GONE);
                                   Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                               }else{
                                   progressBar.setVisibility(View.GONE);
                                   Toast.makeText(UploadImage.this, "error "+task.getException(), Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                   }
               });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == camerarequest && resultCode == Activity.RESULT_OK && data != null)
        {
            Bitmap bitmap =(Bitmap) data.getExtras().get("data");



            imageView.setImageBitmap(bitmap);
            imageuri = getUriFromBitmap(bitmap,getApplicationContext());


        }else if(requestCode == gallaryrequest && resultCode == Activity.RESULT_OK && data != null);
        {
            imageuri = data.getData();
            imageView.setImageURI(imageuri);
        }

    }

    private Uri getUriFromBitmap(Bitmap bitmap, Context applicationContext) {
        ByteArrayOutputStream bytobj = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bytobj);
        String path = MediaStore.Images.Media.insertImage(applicationContext.getContentResolver(),bitmap,"Title",null);
        return Uri.parse(path);
    }

    private void combine() {
        imageView = findViewById(R.id.selectedimage);
        choosefromCamera = findViewById(R.id.choosefromcamera);
        chooseFromgallary = findViewById(R.id.choosefromgallary);
        uploadimagebtn = findViewById(R.id.upload_imagebtn);
        progressBar = findViewById(R.id.imageprogressbar);
    }
}