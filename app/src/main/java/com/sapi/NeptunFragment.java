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
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        mNeptun = (WebView) neptun.findViewById(R.id.webView2);
        mNeptun.loadUrl("https://neptunweb.semmelweis.hu/hallgato/login.aspx");

        // Enable Javascript
        WebSettings webSettings = mNeptun.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mNeptun.setWebViewClient(new WebViewClient());

        return neptun;
    }
}
