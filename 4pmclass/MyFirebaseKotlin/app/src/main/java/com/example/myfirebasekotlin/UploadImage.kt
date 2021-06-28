package com.example.myfirebasekotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.ObjectInput

class UploadImage : AppCompatActivity() {

    private var imageuri: Uri? = null
    lateinit var imageVieww: ImageView
    lateinit var choosefromCamera:Button
    lateinit var chooseFromgallary:Button
    lateinit var uploadimagebtn:Button
    lateinit var progressBar:ProgressBar
    lateinit var firebaseAuth: FirebaseAuth
var permisson:Array<String> = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    val requestcodes = 123
    val gallaryrequest = 34
    val camerrequest = 56

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        connectxml()
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permisson,requestcodes)
            }
        }
        firebaseAuth = FirebaseAuth.getInstance()
        imageVieww.setOnClickListener {
            choosefromCamera.visibility = View.VISIBLE
            chooseFromgallary.visibility = View.VISIBLE
            uploadimagebtn.visibility = View.VISIBLE
        }

        chooseFromgallary.setOnClickListener {
            choosefromCamera.visibility = View.GONE

            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, gallaryrequest)

        }
        choosefromCamera.setOnClickListener {
          chooseFromgallary.visibility = View.GONE
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            startActivityForResult(intent,camerrequest)

        }
        uploadimagebtn.setOnClickListener {
            chooseFromgallary.visibility = View.GONE
            choosefromCamera.visibility = View.GONE
            if(imageuri != null)
            {
                progressBar.visibility = View.VISIBLE
                uploadimage()
            }
        }
    }

    private fun uploadimage() {
        var userid = firebaseAuth.currentUser!!.uid
        var storageReference: StorageReference = FirebaseStorage.getInstance().getReference("OurImage").child(userid)

        storageReference.putFile(imageuri!!).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>{
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
        storageReference.getDownloadUrl().addOnSuccessListener(object : OnSuccessListener<Uri?>{
            override fun onSuccess(imageuri: Uri?) {
                var imageurl = imageuri.toString()

                val imagpemap  = HashMap<String,Any> () as MutableMap<String,Any>

          imagpemap.put("imageurl",imageurl)

var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("RegisterData").child(userid)
                databaseReference.updateChildren(imagpemap).addOnCompleteListener(object : OnCompleteListener<Void?>{
                    override fun onComplete(p0: Task<Void?>) {
                        if(p0.isSuccessful)
                        {
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show()
                        }else
                        {
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext,"error ${p0.exception}",Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }


        })

            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == gallaryrequest && resultCode == Activity.RESULT_OK && data !=null)
        {
              imageuri = data!!.data
            imageVieww.setImageURI(imageuri)
        }else if( requestCode == camerrequest && resultCode == Activity.RESULT_OK && data != null)
        {
            val extrass = data.extras
            var bitmap =extrass!!["data"] as Bitmap?
            imageuri = getUriFrombitmap(bitmap,applicationContext)
            imageVieww.setImageBitmap(bitmap)
        }
    }

    private fun getUriFrombitmap(bitmap: Bitmap?, applicationContext: Context?): Uri? {

        var bytobj = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG,100,bytobj)
        var path = MediaStore.Images.Media.insertImage(applicationContext!!.contentResolver,bitmap,"Title","somethign")

        return Uri.parse(path)
    }

    private fun connectxml() {
        imageVieww = findViewById(R.id.selectedimage)
        choosefromCamera = findViewById(R.id.choosefromcamera)
        chooseFromgallary = findViewById(R.id.choosefromgallary)
        uploadimagebtn = findViewById(R.id.upload_imagebtn)
        progressBar = findViewById(R.id.imageprogressbar)
    }
}