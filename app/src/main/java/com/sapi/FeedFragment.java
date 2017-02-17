package com.sapi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;


public class FeedFragment extends Fragment {
    public FeedFragment (){
        // Required empty public constructor
    }


    public WebView mFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View feed = inflater.inflate(R.layout.fragment_feed, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        /*RelativeLayout relativeLayout= (RelativeLayout) feed.findViewById(R.id.myprogressbar);
        relativeLayout.setVisibility(View.GONE);*/
        mFeed = (WebView) feed.findViewById(R.id.webView1);
        mFeed.loadUrl("http://semmelweis.hu/hirek/");

        // Enable Javascript
        WebSettings webSettings = mFeed.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mFeed.setWebViewClient(new WebViewClient());

        return feed;
    }
}
