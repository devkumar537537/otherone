package com.example.restapiexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.restapiexamples.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var emailedit: EditText
    lateinit var passwordedit:EditText
    lateinit var nameedit:EditText
    lateinit var regiterbtn:Button
    lateinit var apiServices: ApiServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        combinerview()
        apiServices = ApiClient.getRetrofit().create(ApiServices::class.java)
        regiterbtn.setOnClickListener {
       var emailtext = emailedit.text.toString()
            var passwordtext = passwordedit.text.toString()
            var nametext = nameedit.text.toString()
           registeruser(emailtext,passwordtext,nametext)

        }
    }

    private fun registeruser(emailtext: String, passwordtext: String, nametext: String) {
      var registerreponse: Call<RegisterResponse> = apiServices.registeryourself(emailtext,passwordtext,nametext)

   registerreponse.enqueue(object : Callback<RegisterResponse> {
       override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
           Toast.makeText(applicationContext," error ${t.message}",Toast.LENGTH_LONG).show()
       }

       override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
          if(!response.isSuccessful)
          {
              Toast.makeText(applicationContext,"empty body",Toast.LENGTH_LONG).show()
              return
          }
            var registerespone = response.body()
           Toast.makeText(applicationContext," message "+registerespone!!.result,Toast.LENGTH_LONG).show()
       }

   })
    }

    private fun combinerview() {
        emailedit = findViewById(R.id.emailedit)
        passwordedit = findViewById(R.id.ppasswordedit)
        nameedit = findViewById(R.id.nameedit)
        regiterbtn = findViewById(R.id.regiterbtn)
    }
}