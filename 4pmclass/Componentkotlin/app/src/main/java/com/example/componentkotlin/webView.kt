package com.example.componentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class webView : AppCompatActivity() {
     lateinit var urledit:EditText
    lateinit var openbtn:Button
    lateinit var webview:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        urledit = findViewById(R.id.urledittext)
        openbtn = findViewById(R.id.openwebsite)
        webview = findViewById(R.id.webview)
       webview.webViewClient = MyBrowser()
        openbtn.setOnClickListener {
            var urltext = urledit.text.toString()
            var combined_text = "https://$urltext/"
            webview.settings.javaScriptEnabled = true
            webview.settings.loadsImagesAutomatically = true
            webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webview.loadUrl(combined_text)
            openbtn.visibility = View.GONE
            urledit.visibility = View.GONE
        }
    }
    @Suppress("DEPRECATION")
    private class MyBrowser : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            view?.loadUrl(url!!)
            return true
        }
    }
}