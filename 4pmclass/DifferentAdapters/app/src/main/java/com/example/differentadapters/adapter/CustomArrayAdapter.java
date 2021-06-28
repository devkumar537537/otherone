package com.example.differentadapters.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.differentadapters.BaseAdapterExample;
import com.example.differentadapters.RecyclerExample;
import com.example.differentadapters.SimpleAdapterExamples;
import com.example.differentadapters.models.ModelItem;
import com.example.differentadapters.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CustomArrayAdapter  extends ArrayAdapter<ModelItem> {
    ArrayList<ModelItem> userlist;
    Context context;

    public CustomArrayAdapter(@NonNull Context context, int resource,ArrayList<ModelItem> userlist) {
        super(context, resource);
        this.context =context;
        this.userlist = userlist;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        LayoutInflater layoutInflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       view = layoutInflater.inflate(R.layout.listitem,parent,false);
        ImageView imageView = view.findViewById(R.id.imageview);
        TextView textView = view.findViewById(R.id.usernumber);
        Button button = view.findViewById(R.id.clickbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(button,"This is snackbar",Snackbar.LENGTH_LONG)
                        .setAction("Click", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast toast = new Toast(context);
                                LayoutInflater layoutInflater1 =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view  =layoutInflater1.inflate(R.layout.custom_toast,null);

                                ImageView imageView1 = view.findViewById(R.id.imageviewtoast);
                                TextView textView1 = view.findViewById(R.id.textviewtoast);


                                textView1.setText("Runntime");
                                imageView1.setImageResource(R.drawable.naturetwo);
                                toast.setView(view);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL,150,250);
                                toast.show();
                            }
                        }).show();

               Intent intent =  new Intent(context, RecyclerExample.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        ModelItem itemModel = userlist.get(position);

        textView.setText(itemModel.getUsernumber());
        imageView.setImageResource(itemModel.getImageurl());

        return view;
    }
}
