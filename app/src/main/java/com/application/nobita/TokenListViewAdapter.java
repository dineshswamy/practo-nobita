package com.application.nobita;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TokenListViewAdapter extends RecyclerView.Adapter<TokenListViewAdapter.ViewHolder> {
    private ArrayList<ClinicToken> clinicTokenlist;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView patient_name,patient_phone_no,token_serial_no,token_status,patient_reason;

        public ViewHolder(View view) {
            super(view);
            patient_name = (TextView)view.findViewById(R.id.patient_name);
            patient_phone_no = (TextView)view.findViewById(R.id.patient_phone_no);
            token_serial_no = (TextView)view.findViewById(R.id.token_serial_no);
            token_status = (TextView)view.findViewById(R.id.token_status);
            patient_reason = (TextView)view.findViewById(R.id.patient_reason);

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
                .inflate(R.layout.token_list_fragment, parent, false);

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

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return clinicTokenlist.size();
    }
}