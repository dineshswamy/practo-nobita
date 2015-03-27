package com.application.nobita;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dinesh on 28/3/15.
 */
public class ConfigurationFragment extends Fragment{
    public  ConfigurationFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.configuration_fragment_layout,container,false);
        return v;
    }
}
