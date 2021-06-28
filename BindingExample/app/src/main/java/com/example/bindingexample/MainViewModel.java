package com.example.bindingexample;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bindingexample.apprepositry.Logirepo;

public class MainViewModel  extends BaseObservable {

    private String email;
    private String password;
    private Context context;
    private String remeberme;
    Logirepo logirepo = new Logirepo();
    private static final String TAG = "MainViewModel";

    public MainViewModel(Context context) {
        this.context = context;
    }


    @Bindable
    public String getRemeberme()
    {
        if(remeberme == null)
        {
            return "";
        }
        return remeberme;
    }

    public void setRemeberme(String remeberme)
    {
        this.remeberme =remeberme;
        notifyPropertyChanged(BR.remeberme);
    }
    @Bindable
    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable({"email"})
    public String getEmailError() {
        if (getEmail().isEmpty()) {
            return "EMAIL IS EMPTY";
        }
        return "";
    }

    @Bindable
    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable({"password"})
    public String getPasswordError() {
        if (getPassword().isEmpty()) {
            return "PASSWORD IS EMPTY";
        } else {
            return "";
        }
    }


    public void loginmethod(View view){
        String email = getEmail();
        String password = getPassword();
          boolean result = logirepo.logrepomethod(email,password);
        Log.e(TAG, "view model loginmethod: "+result );
          if(result)
          {
              Toast.makeText(context, "Login Successfull", Toast.LENGTH_SHORT).show();
          }else
          {
              Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
          }
        Toast.makeText(context, "email => "+email+"\n password "+password, Toast.LENGTH_SHORT).show();
    }

}
