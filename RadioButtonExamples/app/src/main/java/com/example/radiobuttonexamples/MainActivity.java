package com.example.radiobuttonexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
RadioGroup radioGroup;
RadioButton guessanswer;
RadioButton radionbtnone,radionbtntwo,radionbtnthree,radionbtnfour;
TextView rightanswer,wronganser,questionstext;
String[] questions = {"What is my name?","Who is the prime minister of india ?","How much are the moons"};

String[] righanser = {"Dev","Narendra Modi","one"};

String[] firstoption={"Ram","Babeg","three"};
String[] wrongone = {"Dev","Mukesh ","one"};
String[] wrongtwo = {"Raju","Narendra Modi","Three"};
String[] wrongthree = {"Ram","Jef","Fourt"};
Button votingbtn;
String guesstext;
int count = 0;
boolean res = true;
int rightcount = 0;
int wrongcount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         combineView();




        questionstext.setText(questions[count]);
            radionbtnone.setText(wrongone[count]);
            radionbtntwo.setText(wrongtwo[count]);
            radionbtnthree.setText(firstoption[count]);
            radionbtnfour.setText(wrongthree[count]);


        votingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(count <questions.length)
           {
               int id =-1;
               id = radioGroup.getCheckedRadioButtonId();
               if(id < 0)
               {
                   Toast.makeText(MainActivity.this, "select one option", Toast.LENGTH_SHORT).show();
               }else
               {
                   guessanswer = findViewById(id);

                   guesstext = guessanswer.getText().toString();

                   if(guesstext.equals(righanser[count]))
                   {

                       rightcount= rightcount+1;
                       count = count+1;
                       if(count < questions.length)
                       {
                           questionstext.setText(questions[count]);
                           radionbtnone.setText(wrongone[count]);
                           radionbtntwo.setText(wrongtwo[count]);
                           radionbtnthree.setText(firstoption[count]);
                           radionbtnfour.setText(wrongthree[count]);
                       }else
                       {
                           Toast.makeText(MainActivity.this, "Questions are not availble", Toast.LENGTH_SHORT).show();
                       }
                   }else
                   {

                     wrongcount=  wrongcount+1;
                       count = count+1;
                       if(count < questions.length)
                       {
                           questionstext.setText(questions[count]);
                           radionbtnone.setText(wrongone[count]);
                           radionbtntwo.setText(wrongtwo[count]);
                           radionbtnthree.setText(firstoption[count]);
                           radionbtnfour.setText(wrongthree[count]);
                       }else
                       {
                           Toast.makeText(MainActivity.this, "Questions are not availble", Toast.LENGTH_SHORT).show();
                       }

                   }
                   if(count == questions.length)
                   {
                       rightanswer.setText(" "+rightcount+"");

                       wronganser.setText(" "+wrongcount+"");

                   }
               }
           }else{
               Toast.makeText(MainActivity.this, "No questions are availble", Toast.LENGTH_SHORT).show();
           }


            }
        });
    }

    private void combineView() {
        radioGroup = findViewById(R.id.radiogroup);
        votingbtn = findViewById(R.id.votingbtn);
        questionstext = findViewById(R.id.questions);
        radionbtnone = findViewById(R.id.first);
        radionbtntwo = findViewById(R.id.second);
        radionbtnthree = findViewById(R.id.third);
        radionbtnfour = findViewById(R.id.fourth);
        rightanswer = findViewById(R.id.answerright);
        wronganser = findViewById(R.id.answerwrong);
    }
}