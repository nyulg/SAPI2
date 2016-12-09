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
        if (android.get(i).getSzak()!=null && android.get(i).getKar()!=null) {
            viewHolder.tv_kar_szak.setText(android.get(i).getKar() + '-' + android.get(i).getSzak());
        }
        viewHolder.tv_leiras.setText(android.get(i).getLeiras());
        viewHolder.tv_elerhetoseg.setText(android.get(i).getElerhetoseg());
        viewHolder.tv_ar.setText(android.get(i).getPrice());
        if (android.get(i).getKerulet() != null) {
            viewHolder.tv_ker.setText(android.get(i).getKerulet());
        }
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_kar_szak,tv_leiras, tv_elerhetoseg, tv_ar, tv_ker;
        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView)view.findViewById(R.id.tv_title);
            tv_leiras = (TextView)view.findViewById(R.id.tv_leiras);
            tv_elerhetoseg = (TextView)view.findViewById(R.id.tv_elerhetoseg);
            tv_ar = (TextView)view.findViewById(R.id.tv_ar);
            tv_kar_szak= (TextView) view.findViewById(R.id.tv_kar_szak);
            tv_ker= (TextView) view.findViewById(R.id.tv_ker);

        }
    }

}
