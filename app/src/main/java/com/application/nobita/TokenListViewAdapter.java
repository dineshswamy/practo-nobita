package com.application.nobita;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TokenListViewAdapter extends RecyclerView.Adapter<TokenListViewAdapter.ViewHolder> implements View.OnClickListener{
    private ArrayList<ClinicToken> clinicTokenlist;
    private String doctor_clinic_id="";
    SharedPreferences _prefs ;
    Context context;
    RequestQueue requestQueue;
    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        ClinicToken clinicToken = clinicTokenlist.get(position);
        if (v.getId() == R.id.completed_btn){
            clinicTokenlist.remove(position);
            setTokenStatusBackend("completed",clinicToken);
            notifyDataSetChanged();
        } else if (v.getId() == R.id.delete){
            clinicTokenlist.remove(position);
            setTokenStatusBackend("deleted",clinicToken);
            notifyDataSetChanged();
        }
    }

    private void setTokenStatusBackend(final String status,final ClinicToken clinicToken) {
        String url = "http://shizuka2-noalpha.rhcloud.com/doctor-api/update-token";

        requestQueue = Volley.newRequestQueue(this.context);
        StringRequest getTokenDetailsRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.w("list-tokens-response", response);
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "Error");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id",clinicToken.getToken_id());
                params.put("status",status);
                Log.w("token list view adapter",params.toString());
                return params;
            }
        };
        requestQueue.add(getTokenDetailsRequest);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView patient_name,patient_phone_no,token_serial_no,token_status,patient_reason;
        public Button completed_btn,delete_btn;
        public CardView cardView;
        public ViewHolder(View view) {
            super(view);
            patient_name = (TextView)view.findViewById(R.id.patient_name);
            patient_phone_no = (TextView)view.findViewById(R.id.patient_phone_no);
            token_serial_no = (TextView)view.findViewById(R.id.token_serial_no);
            token_status = (TextView)view.findViewById(R.id.token_status);
            patient_reason = (TextView)view.findViewById(R.id.patient_reason);
            completed_btn = (Button)view.findViewById(R.id.completed_btn);
            cardView = (CardView)view.findViewById(R.id.card_view);
            delete_btn = (Button)view.findViewById(R.id.delete);
        }
    }


    public TokenListViewAdapter(ArrayList<ClinicToken> clinicTokenlist,Context context) {
        this.clinicTokenlist = clinicTokenlist;
        this.context = context;
        _prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        doctor_clinic_id = _prefs.getString("doctor_clinic_id","2");
        requestQueue = Volley.newRequestQueue(this.context);
    }


    @Override
    public TokenListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View token_list_fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.token_list_item, parent, false);

        ViewHolder vh = new ViewHolder(token_list_fragment);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ClinicToken clinicToken = (ClinicToken)clinicTokenlist.get(position);
        holder.patient_name.setText(clinicToken.getPatient_name());

        holder.patient_phone_no.setText(clinicToken.getPatient_phone_no());

        holder.token_serial_no.setText(clinicToken.getToken_serial_no());
        holder.token_status.setText(clinicToken.getToken_status());
        holder.patient_reason.setText(clinicToken.getPatient_reason());
        holder.completed_btn.setOnClickListener(this);
        holder.delete_btn.setOnClickListener(this);
        holder.completed_btn.setTag(position);
        holder.delete_btn.setTag(position);
        if (clinicToken.getToken_status() == "complete") {
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        } else if ((clinicToken.getToken_status() == "removed")) {
            holder.cardView.setCardBackgroundColor(Color.RED);
        }

    }


    @Override
    public int getItemCount() {
        return clinicTokenlist.size();
    }
}