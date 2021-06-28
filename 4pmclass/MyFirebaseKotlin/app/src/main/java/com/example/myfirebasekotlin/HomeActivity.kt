package com.example.myfirebasekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirebasekotlin.models.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {
    lateinit var emailview:TextView
    lateinit var passwordview:TextView
lateinit var firebaseAuth: FirebaseAuth
    lateinit var userrecyclerView: RecyclerView
    lateinit var profileimageview: ImageView
    lateinit var userlist:MutableList<RegisterModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

         userlist = ArrayList<RegisterModel>()
          profileimageview = findViewById(R.id.profitleimage)
        emailview = findViewById(R.id.emailview)
        passwordview = findViewById(R.id.passwordview)
        userrecyclerView = findViewById(R.id.userrecycler)
        var linearlayoutmanager = LinearLayoutManager(applicationContext)
        userrecyclerView.layoutManager = linearlayoutmanager
firebaseAuth = FirebaseAuth.getInstance()

        var userid = firebaseAuth.currentUser!!.uid
        var databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData").child(userid!!)

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"errror "+error.message,Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    var registerModel: RegisterModel? = snapshot.getValue(RegisterModel::class.java)

                    emailview.text = registerModel!!.email
                    passwordview.text = registerModel!!.password

                    if(registerModel.imageurl.equals("default"))
                    {
                        profileimageview.setImageResource(R.mipmap.ic_launcher)
                    }else
                    {
                        Glide.with(applicationContext).load(registerModel.imageurl).into(profileimageview)
                    }

                }

            }

        })
        fetchuser()
    }

    private fun fetchuser() {
        var databaseReference = FirebaseDatabase.getInstance().getReference("RegisterData")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"errror "+error.message,Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userlist.clear()
               for(registermodel in snapshot.children)
               {
                  var registerModell = registermodel.getValue(RegisterModel::class.java)
                   userlist.add(registerModell!!)
               }

                var registeradaper = RecyclerAdapter(userlist,applicationContext)

                userrecyclerView.adapter =registeradaper
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if(id == R.id.logoutbtn)
        {
            firebaseAuth.signOut()
            var intent = Intent(this@HomeActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }else if(id == R.id.uploadimagemove){
           var intent = Intent(this@HomeActivity,UploadImage::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}