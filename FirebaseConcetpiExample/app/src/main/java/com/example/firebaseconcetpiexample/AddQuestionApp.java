package com.example.firebaseconcetpiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuestionApp extends AppCompatActivity {
EditText addquestion,addfirstotpion,addseconoption,addthirdoption,addforuthoption,addrightanswer,addtopic,addnumber;
Button adddatabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_app);
        connectxml();
        adddatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questiontext = addquestion.getText().toString();
                String firstoption = addfirstotpion.getText().toString();
                String secondoption =addseconoption.getText().toString();
                String thirdoption = addthirdoption.getText().toString();
                String fourthoption = addforuthoption.getText().toString();
                String rightanswer = addrightanswer.getText().toString();
                String topic = addtopic.getText().toString();
                String questionnumber = addnumber.getText().toString();
                insertquestion(questiontext,firstoption,secondoption,thirdoption,fourthoption,rightanswer,topic,questionnumber);
            }
        });
    }

    private void insertquestion(String questiontext, String firstoption, String secondoption, String thirdoption, String fourthoption, String rightanswer, String topic, String questionnumber) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Games").child(topic);
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("question",questiontext);
        hashMap.put("firstOption",firstoption);
        hashMap.put("secondOption",secondoption);
        hashMap.put("thirdOption",thirdoption);
        hashMap.put("fourthOption",fourthoption);
        hashMap.put("topic",topic);
        hashMap.put("rightresponse",rightanswer);
        hashMap.put("questionNumber",questionnumber);
        databaseReference.child(questionnumber).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AddQuestionApp.this, "Question Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void connectxml() {
        addquestion = findViewById(R.id.questionedit);
        addfirstotpion = findViewById(R.id.firstoption);
        addseconoption = findViewById(R.id.secondoption);
        addthirdoption = findViewById(R.id.thirdoption);
        addforuthoption = findViewById(R.id.fourthoption);
        addrightanswer = findViewById(R.id.rightoption);
        addtopic = findViewById(R.id.topic);
        addnumber = findViewById(R.id.numberquestion);

        adddatabtn = findViewById(R.id.addquestionbtn);
    }
}