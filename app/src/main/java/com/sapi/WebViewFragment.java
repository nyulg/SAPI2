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
import android.app.FragmentTransaction;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment {


    public WebViewFragment() {
        // Required empty public constructor
    }


    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = (WebView) v.findViewById(R.id.webView3);
        mWebView.loadUrl("http://semmelweis.hu/hirek/");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }


    }
