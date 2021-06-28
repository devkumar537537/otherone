package com.example.restapiexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsWithfield extends AppCompatActivity {
TextView comemntst;
EditText postedit;
Button submit;

    ServiceInterface serviceInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_withfield);
        connect();
        serviceInterface = ApiClient.getRetroft().create(ServiceInterface.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idtext = postedit.getText().toString();
                int id = Integer.parseInt(idtext);
                
                fetchcomments(id);
            }
        });
        
    }

    private void fetchcomments(int id) {
        Call<ArrayList<Comments>> call = serviceInterface.getcommentlist(id);

        call.enqueue(new Callback<ArrayList<Comments>>() {
            @Override
            public void onResponse(Call<ArrayList<Comments>> call, Response<ArrayList<Comments>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(CommentsWithfield.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for(Comments comments: response.body())
                {
                    stringBuilder.append("id => "+comments.getId()+"\n");
                    stringBuilder.append("postid => "+comments.getPostId()+"\n");
                    stringBuilder.append("email => "+comments.getEmail()+"\n");
                    stringBuilder.append("name => "+comments.getName()+"\n");
                    stringBuilder.append("body => "+comments.getBody()+"\n\n");

                }
                comemntst.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Comments>> call, Throwable t) {
                Toast.makeText(CommentsWithfield.this, "erroro "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connect() {
        comemntst = findViewById(R.id.commenttextview);
        postedit = findViewById(R.id.postidtext);
        submit = findViewById(R.id.getresponsebtn);
    }
}