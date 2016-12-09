package com.sapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nyulg on 2016. 12. 09..
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private ArrayList<Events> android;

    public EventsAdapter(ArrayList<Events> android) {
        this.android = android;
    }

   /* @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrow_contacts, viewGroup, false);
            return new ViewHolder(view);
            }*/

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrow_calendar, viewGroup, false);
        return new EventsAdapter.ViewHolder(view3);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_esname.setText(android.get(i).getEsNev());
        viewHolder.tv_esdate.setText(android.get(i).getDatum());
        viewHolder.tv_eshelyszin.setText(android.get(i).getHelyszin());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_esname,tv_esdate,tv_eshelyszin;
        public ViewHolder(View view) {
            super(view);

            tv_esname = (TextView)view.findViewById(R.id.tv_esname);
            tv_esdate = (TextView)view.findViewById(R.id.tv_esdate);
            tv_eshelyszin = (TextView)view.findViewById(R.id.tv_eshelyszin);

        }
    }
}
