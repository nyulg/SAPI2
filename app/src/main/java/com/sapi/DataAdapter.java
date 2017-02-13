package com.sapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nyulg on 2016. 11. 30..
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Market> android;

    public DataAdapter(ArrayList<Market> android) {
        this.android = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrow_notes, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_title.setText(android.get(i).getCim());
        if (android.get(i).getSzak()!="" && android.get(i).getKar()!="") {
            viewHolder.tv_kar_szak.setText(android.get(i).getKar() + '-' + android.get(i).getSzak());
        } else {
            viewHolder.tv_kar_szak.setVisibility(View.GONE);
        }
        viewHolder.tv_leiras.setText(android.get(i).getLeiras());
        if (android.get(i).geteMail()!="") {
            viewHolder.tv_e_mail.setText(android.get(i).geteMail());
        } else {
            viewHolder.tv_e_mail.setVisibility(View.GONE);
        }
        if (android.get(i).getPhone()!="") {
            viewHolder.tv_phone.setText(android.get(i).getPhone());
        } else {
            viewHolder.tv_phone.setVisibility(View.GONE);
        }
        viewHolder.tv_ar.setText("Ár: " + android.get(i).getPrice()+"Ft");
        if (android.get(i).getKerulet() != "") {
            viewHolder.tv_ker.setText(android.get(i).getKerulet());
        } else {
            viewHolder.tv_ker.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_kar_szak,tv_leiras, tv_e_mail, tv_phone, tv_ar, tv_ker;
        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView)view.findViewById(R.id.tv_title);
            tv_leiras = (TextView)view.findViewById(R.id.tv_leiras);
            tv_e_mail = (TextView)view.findViewById(R.id.tv_e_mail);
            tv_phone= (TextView) view.findViewById(R.id.tv_telefon);
            tv_ar = (TextView)view.findViewById(R.id.tv_ar);
            tv_kar_szak= (TextView) view.findViewById(R.id.tv_kar_szak);
            tv_ker= (TextView) view.findViewById(R.id.tv_ker);

        }
    }

}
