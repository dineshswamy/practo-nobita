package com.application.nobita;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

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
public class ConfigurationFragment extends Fragment implements  View.OnClickListener {
    EditText no_of_tokens, choose_clinic;
    Button update_settings;
    TimePicker timePicker;
    String time = "";
    String date_timestamp;
    Context context;
    SharedPreferences _prefs;
    public ConfigurationFragment() {}

    public void setContext (Context context) {
        this.context = context;

    }



    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        _prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configuration_fragment_layout, container, false);
        no_of_tokens = (EditText) view.findViewById(R.id.no_of_tokens);
        choose_clinic = (EditText) view.findViewById(R.id.choose_clinic);
        update_settings = (Button) view.findViewById(R.id.update_settings_btn);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        update_settings.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.update_settings_btn) {
            String no_of_tokens_str = no_of_tokens.getText().toString();
            String choose_clinic_str = choose_clinic.getText().toString();
            timePicker.clearFocus();
            time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute() + ":00";
            date_timestamp = new SimpleDateFormat("yyyy-mm-dd").format(new Date()).toString() + " " + time;
            updateDoctorSettings(no_of_tokens_str, choose_clinic_str);
        }
    }

    public void updateDoctorSettings(final String no_of_tokens_str, final String choose_clinic_str) {
        String url = "http://shizuka2-noalpha.rhcloud.com/doctor-api/put-tokens";
        SharedPreferences.Editor editor = _prefs.edit();
        editor.putString("doctor_location_id",choose_clinic_str);
        editor.putString("no_of_tokens_str",no_of_tokens_str);
        editor.commit();

        StringRequest updateDoctorSettingsRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            if (message.equalsIgnoreCase("success")) {
                                Toast.makeText(context, "Settings updated", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, "Settings update failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "Error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("start_time", time);
                params.put("token_timestamp", date_timestamp);
                params.put("no_of_tokens", no_of_tokens_str);
                params.put("doctor_location_id", choose_clinic_str);

                return  params;
            }


        };
        ((MainActivity)getActivity()).requestQueue.add(updateDoctorSettingsRequest);
    }
}