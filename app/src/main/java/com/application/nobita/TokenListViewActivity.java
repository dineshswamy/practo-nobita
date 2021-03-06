package com.application.nobita;

import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinesh on 28/3/15.
 */
public class TokenListViewActivity  extends ActionBarActivity{

    View layout;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    TokenListViewAdapter tokenListViewAdapter;
    LinearLayoutManager layoutManager;
    SharedPreferences _prefs;
    public void onCreate(Bundle onsavedInstanceState) {
        super.onCreate(onsavedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.token_list_activity);

        recyclerView = (RecyclerView)findViewById(R.id.token_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        _prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }


    public void getDoctorTokenList(){

        String url = "http://shizuka2-noalpha.rhcloud.com/doctor-api/list-tokens";
        final int doctor_location_id = 3;
        StringRequest getTokenDetailsRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.w("list-tokens-response",response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray  tokens = jsonObject.getJSONArray("message");
                            ArrayList<ClinicToken> clinicTokenArrayList = new ArrayList<ClinicToken>();
                            for (int i =0;i<tokens.length();i++) {
                                JSONObject token = tokens.getJSONObject(i);
                                ClinicToken clinicToken = new ClinicToken();
                                clinicToken.setPatient_name(token.getString("patient_name"));
                                clinicToken.setPatient_phone_no(token.getString("patient_phone_no"));
                                clinicToken.setPatient_id(token.getString("patient_id"));
                                clinicToken.setToken_id(token.getString("token_id"));
                                //clinicToken.setPatient_reason(token.getString("patient_phone_no"));
                                clinicToken.setToken_serial_no(token.getString("token_serial_no"));
                                clinicToken.setToken_status(token.getString("token_status"));
                                clinicTokenArrayList.add(clinicToken);
                            }
                            setListViewAdapter(clinicTokenArrayList);
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

                params.put("token_timestamp","2015-04-28 00:00:00");
                params.put("doctor_location_id", doctor_location_id+"");
                Log.w("Doctor",params.toString());
                return params;
            }
        };
        requestQueue.add(getTokenDetailsRequest);

    }

    public void onResume(){
        super.onResume();
        getDoctorTokenList();

    }

    public void setListViewAdapter( ArrayList<ClinicToken> clinicTokenArrayList ){

        tokenListViewAdapter = new TokenListViewAdapter(clinicTokenArrayList,this);
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(tokenListViewAdapter);
        tokenListViewAdapter.notifyDataSetChanged();

    }

}
