package com.sapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {
    private CardView aok,ekk,etk,fok,gyok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View noteView=inflater.inflate(R.layout.fragment_note, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        aok= (CardView) noteView.findViewById(R.id.card_aok);
        ekk= (CardView) noteView.findViewById(R.id.card_ekk);
        etk= (CardView) noteView.findViewById(R.id.card_etk);
        fok= (CardView) noteView.findViewById(R.id.card_fok);
        gyok= (CardView) noteView.findViewById(R.id.card_gyok);
        aok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "ÁOK!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(),text,duration);
                toast.show();
            }
        });
        ekk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "EKK!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(),text,duration);
                toast.show();
            }
        });
        etk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "ETK!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(),text,duration);
                toast.show();
            }
        });
        fok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "FOK!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(),text,duration);
                toast.show();
            }
        });
        gyok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "GYOK!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(),text,duration);
                toast.show();
            }
        });
        return noteView;
    }

}
