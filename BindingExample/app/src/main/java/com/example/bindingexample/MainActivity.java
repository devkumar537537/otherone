package com.example.bindingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.bindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        MainViewModel mone = new MainViewModel(this);
        binding.setUserdata(mone);
        binding.setLifecycleOwner(this);
    }
}