package com.example.firebaseconcetpiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseconcetpiexample.models.QuestionsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayGame extends AppCompatActivity {
RadioGroup radioGroup;
RadioButton firstoption,secondoption,thirdoption,fourthoption,guessanswer;
Button submintanswer;
String guesstext;
TextView wrongtextview,righttext,questiontextview;
ArrayList<QuestionsModel> questionslist;
    int count = 0;
    boolean res = true;
    int rightcount = 0;
    int wrongcount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        joinxml();
        questionslist = new ArrayList<>();
        DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReference("Games").child("Java");
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
     questionslist.clear();
        for(DataSnapshot snapshot1: snapshot.getChildren())
        {
            QuestionsModel questionsModel = snapshot1.getValue(QuestionsModel.class);
            questionslist.add(questionsModel);
        }
      filldata(count);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});


submintanswer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(count <questionslist.size())
        {
            int id =-1;
            id = radioGroup.getCheckedRadioButtonId();
            if(id < 0)
            {
                Toast.makeText(PlayGame.this, "select one option", Toast.LENGTH_SHORT).show();
            }else
            {
                guessanswer = findViewById(id);

                guesstext = guessanswer.getText().toString();

                if(guesstext.equals(questionslist.get(count).getRightresponse()))
                {

                    rightcount= rightcount+1;
                    count = count+1;
                    if(count < questionslist.size())
                    {

                        filldata(count);
                    }else
                    {
                        Toast.makeText(PlayGame.this, "Questions are not availble", Toast.LENGTH_SHORT).show();
                    }
                }else
                {

                    wrongcount=  wrongcount+1;
                    count = count+1;
                    if(count < questionslist.size())
                    {
                        filldata(count);
                    }else
                    {
                        Toast.makeText(PlayGame.this, "Questions are not availble", Toast.LENGTH_SHORT).show();
                    }

                }
                if(count == questionslist.size())
                {
                    righttext.setText("Right Answer are "+rightcount+"");

                    wrongtextview.setText("Wrong Answer are "+wrongcount+"");

                }
            }
        }else{
            Toast.makeText(PlayGame.this, "No questions are availble", Toast.LENGTH_SHORT).show();
        }


    }
    });


    }
private  void filldata(int count)
{
    questiontextview.setText(questionslist.get(count).getQuestion());
    firstoption.setText(questionslist.get(count).getFirstOption());
    secondoption.setText(questionslist.get(count).getSecondOption());
    thirdoption.setText(questionslist.get(count).getThirdOption());
    fourthoption.setText(questionslist.get(count).getFourthOption());
}
    private void joinxml() {
        radioGroup = findViewById(R.id.radiogrout);
        firstoption = findViewById(R.id.firstoption);
        secondoption = findViewById(R.id.secondoption);
        thirdoption = findViewById(R.id.thirdoption);
        fourthoption = findViewById(R.id.fourthoption);
        submintanswer = findViewById(R.id.submint);
        wrongtextview = findViewById(R.id.wronganswer);
        righttext = findViewById(R.id.rightanswer);
        questiontextview = findViewById(R.id.questiontext);

    }
}