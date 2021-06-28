package com.example.restapiexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.restapiexamples.models.Comments
import com.example.restapiexamples.models.Postmodels
import com.example.restapiexamples.models.QuestionModel
import com.example.restapiexamples.models.Questionlist
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var resonstext: TextView
    lateinit var apiServices: ApiServices
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resonstext = findViewById(R.id.respostext)

        apiServices = ApiClient.getRetrofit().create(ApiServices::class.java)

       // getpostlistss()
     //   getcommentslist()
getQuestions()
    }

    private fun getQuestions() {
       var quetioncall: Call<Questionlist> = apiServices.getquetion("Flutter")

        quetioncall.enqueue(object :Callback<Questionlist>{
            override fun onFailure(call: Call<Questionlist>, t: Throwable) {
                Toast.makeText(applicationContext,"error "+t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Questionlist>, response: Response<Questionlist>) {
                if(!response.isSuccessful){
                    Toast.makeText(applicationContext,"error "+response.body(),Toast.LENGTH_SHORT).show()
                    return
                }
var questionlist = response.body()
                var stringBuilder = StringBuilder()
                for(question in questionlist!!.question)
                {
                    stringBuilder.append("id => ${question.id}  \n")
                    stringBuilder.append("question => ${question.question} \n")
                    stringBuilder.append("Right => ${question.rightoption} \n ")
                    stringBuilder.append("Number => ${question.questionnumber} \n\n")
                }
                resonstext.text = stringBuilder.toString()

            }

        })

    }

    private fun getcommentslist() {
        var commentcall : Call<MutableList<Comments>> = apiServices.getcommetlist(4)
        commentcall.enqueue(object :Callback<MutableList<Comments>>{
            override fun onFailure(call: Call<MutableList<Comments>>, t: Throwable) {
                Toast.makeText(applicationContext,"error "+t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Comments>>, response: Response<MutableList<Comments>>) {
                if(!response.isSuccessful){
                    Toast.makeText(applicationContext,"error "+response.body(),Toast.LENGTH_SHORT).show()
                    return
                }

                var stringBuilder = StringBuilder()
                for(comments in response.body()!!)
                {
                    stringBuilder.append("id => ${comments.id}  \n")
                    stringBuilder.append("userId => ${comments.postId} \n")
                    stringBuilder.append("titile => ${comments.email} \n ")
                    stringBuilder.append("body => ${comments.body} \n\n")
                }
                resonstext.text = stringBuilder.toString()
            }

        })
    }

    private fun getpostlistss() {
        var postcall : Call<MutableList<Postmodels>> = apiServices.getlist()

        postcall.enqueue(object: Callback<MutableList<Postmodels>>{
            override fun onFailure(call: Call<MutableList<Postmodels>>, t: Throwable) {
                Toast.makeText(applicationContext,"error "+t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MutableList<Postmodels>>, response: Response<MutableList<Postmodels>>) {
                if(!response.isSuccessful){
                    Toast.makeText(applicationContext,"error "+response.body(),Toast.LENGTH_SHORT).show()
                    return
                }

                var stringBuilder = StringBuilder()
                for(postmodle in response.body()!!)
                {
                    stringBuilder.append("id => ${postmodle.id}  \n")
                    stringBuilder.append("userId => ${postmodle.userId} \n")
                    stringBuilder.append("titile => ${postmodle.titile} \n ")
                    stringBuilder.append("body => ${postmodle.body} \n\n")
                }
                resonstext.text = stringBuilder.toString()
            }

        })
    }
}