package com.application.nobita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

/**
 * Created by dinesh on 28/3/15.
 */
public class TokenListFragment  extends Fragment implements View.OnClickListener {



    RequestQueue requestQueue;
    Button start_tokens_activity;
    LinearLayoutManager layoutManager;
    public void onCreate(Bundle onsavedInstanceState) {
        super.onCreate(onsavedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity());

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View layout = inflater.inflate(R.layout.token_list_fragment,null,false);
        start_tokens_activity = (Button)layout.findViewById(R.id.start_tokens_activity);
        start_tokens_activity.setOnClickListener(this);
        return layout;
    }



    public void onResume(){
        super.onResume();

    }



    @Override
    public void onClick(View v) {

        getActivity().finish();
        Intent intent = new Intent(getActivity(),TokenListViewActivity.class);
        startActivity(intent);
    }
}
