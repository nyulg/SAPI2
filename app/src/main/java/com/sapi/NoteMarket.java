package com.sapi;

import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoteMarket extends Fragment{
    private RecyclerView recyclerView;
    private ArrayList<Market> data;
    private DataAdapter adapter;
    private FloatingActionButton fab_add_market, fab_market_search;
    SharedPreferences pref;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View notemarket = inflater.inflate(R.layout.fragment_note_market, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        pref = getActivity().getPreferences(0);
        fab_add_market= (FloatingActionButton) this.getActivity().findViewById(R.id.fab_add_market);
        fab_market_search= (FloatingActionButton) this.getActivity().findViewById(R.id.fab_market_search);
        recyclerView = (RecyclerView) notemarket.findViewById(R.id.notes_recycler_view);
        /*RelativeLayout rellayout= (RelativeLayout) getActivity().findViewById(R.id.myprogress);
        rellayout.setVisibility(View.GONE);*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
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
        return notemarket;
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jasehn.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request2Interface request = retrofit.create(Request2Interface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getMarket()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}