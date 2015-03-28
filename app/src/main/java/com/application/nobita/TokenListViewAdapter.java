package com.application.nobita;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TokenListViewAdapter extends RecyclerView.Adapter<TokenListViewAdapter.ViewHolder> implements View.OnClickListener{
    private ArrayList<ClinicToken> clinicTokenlist;

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        if (v.getId() == R.id.completed_btn){
            clinicTokenlist.remove(position);
            notifyDataSetChanged();
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView patient_name,patient_phone_no,token_serial_no,token_status,patient_reason;
        public Button completed_btn;
        public ViewHolder(View view) {
            super(view);
            patient_name = (TextView)view.findViewById(R.id.patient_name);
            patient_phone_no = (TextView)view.findViewById(R.id.patient_phone_no);
            token_serial_no = (TextView)view.findViewById(R.id.token_serial_no);
            token_status = (TextView)view.findViewById(R.id.token_status);
            patient_reason = (TextView)view.findViewById(R.id.patient_reason);
            completed_btn = (Button)view.findViewById(R.id.completed_btn);



        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TokenListViewAdapter(ArrayList<ClinicToken> clinicTokenlist) {
        this.clinicTokenlist = clinicTokenlist;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TokenListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View token_list_fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.token_list_item, parent, false);

        ViewHolder vh = new ViewHolder(token_list_fragment);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ClinicToken clinicToken = (ClinicToken)clinicTokenlist.get(position);
        holder.patient_name.setText(clinicToken.getPatient_name());
        holder.patient_phone_no.setText(clinicToken.getPatient_phone_no());
        holder.token_serial_no.setText(clinicToken.getToken_serial_no());
        holder.token_status.setText(clinicToken.getToken_status());
        holder.patient_reason.setText(clinicToken.getPatient_reason());
        holder.completed_btn.setOnClickListener(this);
        holder.completed_btn.setTag(position);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return clinicTokenlist.size();
    }
}