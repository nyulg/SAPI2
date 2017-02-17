package com.sapi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ContactsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Contacts> data;
    private ContactsAdapter adapter;
    public FloatingActionButton fab_TO, fab_activeTO;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contacts = inflater.inflate(R.layout.fragment_contacts, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        recyclerView = (RecyclerView) contacts.findViewById(R.id.contacts_recycler_view);
        /*RelativeLayout rellayout= (RelativeLayout) getActivity().findViewById(R.id.myprogress);
        rellayout.setVisibility(View.GONE);*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        fab_TO = (FloatingActionButton) contacts.findViewById(R.id.fab_to);
        fab_TO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://jasehn.eu/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Request1Interface request = retrofit.create(Request1Interface.class);
                Call<JSONResponse> call = request.getJSON();
                call.enqueue(new Callback<JSONResponse>() {
                    @Override
                    public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                        JSONResponse jsonResponse = response.body();
                        data = new ArrayList<>(Arrays.asList(jsonResponse.getContacts()));
                        adapter = new ContactsAdapter(data);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<JSONResponse> call, Throwable t) {
                        Log.d("Error",t.getMessage());
                    }
                }  );
                fab_TO.setVisibility(View.GONE);
                fab_activeTO.setVisibility(View.VISIBLE);
            }
        });
        fab_activeTO = (FloatingActionButton) contacts.findViewById(R.id.fab_to_active);
        fab_activeTO.setVisibility(View.GONE);
        fab_activeTO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadJSON();
                fab_activeTO.setVisibility(View.GONE);
                fab_TO.setVisibility(View.VISIBLE);
            }

        });
        loadJSON();
        return contacts;
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jasehn.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request0Interface request = retrofit.create(Request0Interface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getContacts()));
                adapter = new ContactsAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
