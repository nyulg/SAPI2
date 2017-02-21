package com.sapi;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherMarket extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Market> data;
    private DataAdapter adapter;
    private FloatingActionButton fab_add_market, fab_market_search;
    SharedPreferences pref;

    public OtherMarket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View othermarket = inflater.inflate(R.layout.fragment_other_market, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        pref = getActivity().getPreferences(0);
        fab_add_market= (FloatingActionButton) this.getActivity().findViewById(R.id.fab_add_market);
        fab_market_search= (FloatingActionButton) this.getActivity().findViewById(R.id.fab_market_search);
        recyclerView = (RecyclerView) othermarket.findViewById(R.id.other_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState ==RecyclerView.SCROLL_STATE_IDLE){
                    if(pref.getBoolean(Constants.IS_LOGGED_IN,true)) {
                        fab_add_market.show();
                    }
                    fab_market_search.show();
                } else {
                    fab_add_market.hide();
                    fab_market_search.hide();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        return othermarket;
    }

}
