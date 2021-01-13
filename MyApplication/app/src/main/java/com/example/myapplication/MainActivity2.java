package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView3, textView4, textView5;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        Bundle bundle = getIntent().getExtras();
        String valor_tit = bundle.getString("Tituloi");
        String valor_ano = bundle.getString("anoi");
        String valor_user = bundle.getString("desrii");


        textView3.setText(valor_tit);
        textView4.setText(valor_ano);
        textView5.setText(valor_user);

        webView1 = (WebView) findViewById(R.id.webView);
        WebSettings conf = webView1.getSettings();
        conf.setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://" + bundle.getString("urli"));
    }

    public void volver(View view) {
        finish();
    }
}

