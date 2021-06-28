package com.example.firebaseconcepts;

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
    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,Manifest.permission.CAMERA};
    int permissioncode = 123;
    int gallaryrequest = 234;
    int camerarequest = 112;
    Uri imageuri;
StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        combine();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission( Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permission,permissioncode);
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
        choosefromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFromgallary.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,camerarequest);
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

            }
        });
    }

    private void uploadimage() {
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("UserImage").child(userid);
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
                                   Toast.makeText(UploadImage.this, "UploadImage Successfully", Toast.LENGTH_SHORT).show();
                               }else
                               {
                                   progressBar.setVisibility(View.GONE);
                                   Toast.makeText(UploadImage.this, "eroro "+task.getException(), Toast.LENGTH_SHORT).show();
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
//            selectedimage.setImageURI(imageuri);
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