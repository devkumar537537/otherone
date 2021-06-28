package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstlibrary.MyCustomClass;

public class MainActivity extends AppCompatActivity {
WebView webView;
String customurl = "https://www.google.com/";

EditText urledit;
Button openurl,showpreviewo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     connetxml();

        webView.loadUrl(customurl);
        webView.setWebViewClient(new MyWebView());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
openurl.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String text = urledit.getText().toString().trim();
        String newurl = "https://"+text+"/";

        webView.loadUrl(newurl);
        urledit.setVisibility(View.GONE);
        openurl.setVisibility(View.GONE);
        showpreviewo.setVisibility(View.VISIBLE);
    }
});
showpreviewo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        urledit.setVisibility(View.VISIBLE);
        openurl.setVisibility(View.VISIBLE);
    }
});
    }

    private class MyWebView extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }
    private void connetxml()
    {
        webView = findViewById(R.id.webview);
        urledit = findViewById(R.id.urledittext);
        openurl = findViewById(R.id.openrulbtn);
        showpreviewo = findViewById(R.id.showprevius);
    }
}