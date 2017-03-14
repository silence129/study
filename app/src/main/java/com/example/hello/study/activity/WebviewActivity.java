package com.example.hello.study.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hello.study.R;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private EditText editText;
    private Button button;

    public static void startAcivity(Context packageContext){

        Intent intent = new Intent(packageContext,WebviewActivity.class);
        packageContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);

        webView = (WebView)findViewById(R.id.webview);
        button = (Button)findViewById(R.id.button_browser);
        editText = (EditText)findViewById(R.id.text_url);

        button.setOnClickListener(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }

    @Override
    public void onClick(View v) {

        String url = editText.getText().toString();
        if(!url.startsWith("http")){
            url= "http://" + url;
            editText.setText(url);
        }

        Toast.makeText(this,"Broswer " + url,Toast.LENGTH_SHORT).show();
        webView.loadUrl(url);
    }
}
