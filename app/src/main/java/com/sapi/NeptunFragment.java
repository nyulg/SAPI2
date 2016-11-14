package com.sapi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class NeptunFragment extends Fragment {
    public NeptunFragment() {
        // Required empty public constructor
    }


    public WebView mNeptun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View neptun = inflater.inflate(R.layout.fragment_neptun, container, false);
        mNeptun = (WebView) neptun.findViewById(R.id.webView2);
        mNeptun.loadUrl("https://neptunweb.semmelweis.hu/hallgato/login.aspx");

        // Enable Javascript
        WebSettings webSettings = mNeptun.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mNeptun.setWebViewClient(new WebViewClient());

        return neptun;
    }
}
