package com.ugisoftware.htmlparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class browser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_browser );
        WebView webView=(WebView)findViewById( R.id.webView );
        String a=getIntent().getStringExtra( "sayfa" );
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(a);

    }
}
