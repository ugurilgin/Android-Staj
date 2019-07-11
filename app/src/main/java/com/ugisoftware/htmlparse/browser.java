package com.ugisoftware.htmlparse;

import android.net.http.SslError;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class browser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_browser );
        WebView webView=(WebView)findViewById( R.id.webView );
        String a=getIntent().getStringExtra( "sayfa" );
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(a);
        //webView.enableJavaScript();
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // DOT CALL SUPER METHOD
                super.onReceivedSslError(view, handler, error);
            }
        });
    }
}
