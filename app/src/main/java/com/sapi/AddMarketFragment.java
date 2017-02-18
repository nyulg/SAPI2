package com.sapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMarketFragment extends Fragment{

    private RadioGroup rg;
    private RadioButton add_alberlet;
    private LinearLayout kerulet;

    public AddMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View addmarket = inflater.inflate(R.layout.fragment_add_market, container, false);
        this.getActivity().findViewById(R.id.sote_text).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.back).setVisibility(View.VISIBLE);
        this.getActivity().findViewById(R.id.map_list).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search).setVisibility(View.GONE);
        this.getActivity().findViewById(R.id.map_search_text).setVisibility(View.GONE);
        rg = (RadioGroup) addmarket.findViewById(R.id.add_type);
        add_alberlet= (RadioButton) addmarket.findViewById(R.id.add_alberlet);
        kerulet= (LinearLayout) addmarket.findViewById(R.id.kerulet);
        kerulet.setVisibility(View.INVISIBLE);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (add_alberlet.isChecked()){
                    kerulet.setVisibility(View.VISIBLE);
                } else {
                    kerulet.setVisibility(View.INVISIBLE);
                }
            }
        });
        Spinner kar = (Spinner) addmarket.findViewById(R.id.add_kar);
        ArrayAdapter<CharSequence> karadapter=ArrayAdapter.createFromResource(this.getActivity(), R.array.kar_array, android.R.layout.simple_spinner_item);
        karadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kar.setAdapter(karadapter);
        Spinner ker = (Spinner) addmarket.findViewById(R.id.add_ker);
        ArrayAdapter<CharSequence> keradapter=ArrayAdapter.createFromResource(this.getActivity(), R.array.ker_array, android.R.layout.simple_spinner_item);
        keradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ker.setAdapter(keradapter);

        return addmarket;
    }

}
