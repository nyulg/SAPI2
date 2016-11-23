package com.sapi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }



    public CalendarView mCal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View se_cal = inflater.inflate(R.layout.fragment_calendar, container, false);
        mCal = (CalendarView) se_cal.findViewById(R.id.SE_cal);

        return se_cal;
    }


}
