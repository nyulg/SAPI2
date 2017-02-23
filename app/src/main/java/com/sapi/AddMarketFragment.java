package com.sapi;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sapi.R.id.et_password;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMarketFragment extends Fragment implements View.OnClickListener {

    private RadioGroup rg;
    private RadioButton add_alberlet, add_jegyzet, add_egyeb;
    private LinearLayout kerulet;
    private AppCompatButton btn_add_another, btn_add;
    private EditText add_ar, add_telefon, add_email, add_szak, add_leiras, add_cim;
    private Spinner add_ker, add_kar;

    public AddMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View addmarket = inflater.inflate(R.layout.fragment_add_market, container, false);
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
        initViews(addmarket);
        return addmarket;
    }

    private void initViews(View addmarket){
        rg = (RadioGroup) addmarket.findViewById(R.id.add_type);
        add_alberlet = (RadioButton) addmarket.findViewById(R.id.add_alberlet);
        add_jegyzet = (RadioButton) addmarket.findViewById(R.id.add_jegyzet);
        add_egyeb = (RadioButton) addmarket.findViewById(R.id.add_egyeb);
        btn_add_another= (AppCompatButton) addmarket.findViewById(R.id.btn_add_another);
        btn_add= (AppCompatButton) addmarket.findViewById(R.id.btn_add);

        add_ar= (EditText) addmarket.findViewById(R.id.add_ar);
        add_telefon= (EditText) addmarket.findViewById(R.id.add_telefon);
        add_email= (EditText) addmarket.findViewById(R.id.add_email);
        add_szak= (EditText) addmarket.findViewById(R.id.add_szak);
        add_leiras= (EditText) addmarket.findViewById(R.id.add_leiras);
        add_cim= (EditText) addmarket.findViewById(R.id.add_cim);

        add_kar= (Spinner) addmarket.findViewById(R.id.add_kar);
        add_ker= (Spinner) addmarket.findViewById(R.id.add_ker);

        btn_add.setOnClickListener(this);
        btn_add_another.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_add:
                break;

            case R.id.btn_add_another:

                String cim = add_cim.getText().toString();
                String ar = add_ar.getText().toString();
                String telefon = "+36"+add_telefon.getText().toString();
                String leiras = add_leiras.getText().toString();
                String szak = add_szak.getText().toString();
                String email = add_email.getText().toString();

                if(!telefon.isEmpty() || !email.isEmpty()) {
                    AddMarketProcess(cim,ar,telefon,leiras,szak,email);

                } else {

                    Snackbar.make(getView(), "A mezők üresek!", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void AddMarketProcess(String cim, String email,String ar, String telefon, String leiras, String szak){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Request6Interface requestInterface = retrofit.create(Request6Interface.class);

        final AddMarket addM = new AddMarket();
        addM.setCim(cim);
        addM.setEmail(email);
        addM.setLeiras(leiras);
        addM.setSzak(szak);
        addM.setTelefon(telefon);
        addM.setAr(ar);
        JSONResponse addMarket= new JSONResponse();
        addMarket.setAddMarket(addM);
        Call<JSONResponse> call = requestInterface.addMarket(addMarket);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                jsonResponse.setAddMarket(addM);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });





        /*ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ADD_MARKET);
        request.setAddMarket(addMarket);
        Call<ServerResponse> response = requestInterface.addMarket(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });*/
    }
}
