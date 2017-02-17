package com.sapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CalendarFragment extends Fragment {
    public CalendarView mCal;
    private RecyclerView recyclerView;
    private ArrayList<Events> data;
    private EventsAdapter adapter;
    private int day, month, year;
    private String tipus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View se_cal = inflater.inflate(R.layout.fragment_calendar, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        mCal = (CalendarView) se_cal.findViewById(R.id.SE_cal);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) se_cal.findViewById(R.id.calendar_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_orarend:
                        tipus = "orarend";
                        loadJSON(day, month, year, tipus);
                        break;
                    case R.id.action_vizsga:
                        tipus="pot";
                        loadJSON(day, month, year, tipus);
                        break;
                    case R.id.action_buli:
                        tipus="buli";
                        loadJSON(day, month, year, tipus);
                        break;
                }
                return false;
            }
        });

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month= c.get(Calendar.MONTH)+1;
        day= c.get (Calendar.DAY_OF_MONTH);
        tipus="orarend";
        loadJSON(day, month, year, tipus);
        mCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                day=i2;
                month=i1+1;
                year=i;
                loadJSON(day,month,year,tipus);
            }
        });
        recyclerView = (RecyclerView) se_cal.findViewById(R.id.calendar_recycler_view);
        /*RelativeLayout rellayout= (RelativeLayout) getActivity().findViewById(R.id.myprogress);
        rellayout.setVisibility(View.GONE);*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        return se_cal;
    }
    private void loadJSON(int day, int month, int year, String tipus){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jasehn.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request4Interface request = retrofit.create(Request4Interface.class);
        Map<String, String> params = new HashMap<>();
        params.put("day", String.valueOf(this.day));
        params.put("month", String.valueOf(this.month));
        params.put("year", String.valueOf(this.year));
        params.put("tipus", String.valueOf(this.tipus));
        Call<JSONResponse> call = request.getJSON(params);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getEvents()));
                adapter = new EventsAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

}
