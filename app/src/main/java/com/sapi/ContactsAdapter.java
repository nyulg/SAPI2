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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private ArrayList<Contacts> android;

    public ContactsAdapter(ArrayList<Contacts> android) {
            this.android = android;
            }

   /* @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrow_contacts, viewGroup, false);
            return new ViewHolder(view);
            }*/

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardrow_contacts, viewGroup, false);
        return new ViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(android.get(i).getNev());
        viewHolder.tv_phone.setText(android.get(i).getNumber());
        viewHolder.tv_mail.setText(android.get(i).getMail());
        viewHolder.tv_room.setText(android.get(i).getSzoba());
        viewHolder.tv_pozicio.setText(android.get(i).getPozicio());
        if (android.get(i).getTO()==1) {
            viewHolder.tv_felfogadas.setText(android.get(i).getFelfogadas());
        }
    }

    @Override
    public int getItemCount() {
            return android.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_phone,tv_mail, tv_room, tv_pozicio, tv_felfogadas;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_phone = (TextView)view.findViewById(R.id.tv_phone);
            tv_mail = (TextView)view.findViewById(R.id.tv_mail);
            tv_room = (TextView)view.findViewById(R.id.tv_room);
            tv_pozicio= (TextView) view.findViewById(R.id.tv_pozicio);
            tv_felfogadas= (TextView) view.findViewById(R.id.tv_felfogadas);

        }
    }
}
