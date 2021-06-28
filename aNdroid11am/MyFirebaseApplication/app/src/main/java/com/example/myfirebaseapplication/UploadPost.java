package com.example.myfirebaseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.HashMap;

public class UploadPost extends AppCompatActivity {
EditText stausediet,nameedit;
ImageView selectedimage;
Button uploadbtn,choosefromcamera,choosefromgallary;
String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,Manifest.permission.CAMERA};
int gallaryrequest = 123;
int camrearequest = 124;
int permissionrequest = 34;
ProgressBar progressBar;

Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);
      connectxml();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission( Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permission,permissionrequest);
            }
        }
        selectedimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromcamera.setVisibility(View.VISIBLE);
                choosefromgallary.setVisibility(View.VISIBLE);
            }
        });
        choosefromgallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromcamera.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,gallaryrequest);
            }
        });
uploadbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(imageuri != null)
        {
            progressBar.setVisibility(View.VISIBLE);
            uploadimage(imageuri);
        }
    }
});
        choosefromcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosefromgallary.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,camrearequest);

            }
        });
    }

    private void uploadimage(Uri imageuri) {
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("userimage").child(userid).child("2");

        storageReference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Imagedata").child(userid);

                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("userid",userid);
                        hashMap.put("imagurl",url);

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(UploadPost.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(UploadPost.this, "Some thing bac happended", Toast.LENGTH_SHORT).show();
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
        if(requestCode == camrearequest && resultCode == Activity.RESULT_OK && data != null)
        {
            Bitmap bitmap =(Bitmap) data.getExtras().get("data");




            imageuri = getUriFromBitmap(bitmap,getApplicationContext());

          selectedimage.setImageURI(imageuri);
        }else if(requestCode == gallaryrequest && resultCode == Activity.RESULT_OK && data != null);
        {
            imageuri = data.getData();
            selectedimage.setImageURI(imageuri);
        }
    }

    private Uri getUriFromBitmap(Bitmap bitmap, Context applicationContext) {
        ByteArrayOutputStream bytobj = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bytobj);
        String path = MediaStore.Images.Media.insertImage(applicationContext.getContentResolver(),bitmap,"Title","Somethidng");
        return Uri.parse(path);
    }

    private void connectxml() {
        stausediet = findViewById(R.id.status);
        nameedit = findViewById(R.id.uplaodname);
        selectedimage = findViewById(R.id.imageview);
        uploadbtn = findViewById(R.id.uploadbtn);
        choosefromgallary = findViewById(R.id.choosefromgallary);
        choosefromcamera = findViewById(R.id.chooseFromcamera);
        progressBar = findViewById(R.id.uploadporogressbar);
    }


}