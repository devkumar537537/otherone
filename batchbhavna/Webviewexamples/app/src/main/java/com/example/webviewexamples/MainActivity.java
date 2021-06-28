package com.example.webviewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText urledit;
    WebView webView;
    Button opernurlbtn,showprevieowbtn;
    String urltext = "https://www.google.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connetcxml();
        webView.loadUrl(urltext);

        webView.setWebViewClient(new MyWebviewclient());

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);


        opernurlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urltext = urledit.getText().toString().trim();
                String newurltext = "https://"+urltext+"/";

                webView.loadUrl(newurltext);
                urledit.setVisibility(View.GONE);
                showprevieowbtn.setVisibility(View.VISIBLE);
                opernurlbtn.setVisibility(View.GONE);
            }
        });

        showprevieowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urledit.setVisibility(View.VISIBLE);
                showprevieowbtn.setVisibility(View.GONE);
                opernurlbtn.setVisibility(View.VISIBLE);
            }
        });
    }
    private void connetcxml()
    {
        urledit = findViewById(R.id.urledittext);
        webView = findViewById(R.id.webivew);
        opernurlbtn = findViewById(R.id.openrulbtn);
        showprevieowbtn = findViewById(R.id.showprevius);

    }

    private  class MyWebviewclient  extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);

            return true;
        }
    }
}