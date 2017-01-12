package com.pereira.classificados.fragment;

import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.pereira.classificados.App;
import com.pereira.classificados.R;

/**
 * Created by Aluno on 11/01/2017.
 */

public class ChronometerFragment extends Fragment {

    private Chronometer mChronometer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container);

        mChronometer = (Chronometer) view.findViewById(R.id.chronometer);

        return view;
    }

    private App getApp(){
        return (App) getActivity().getApplication();
    }

    @Override
    public void onResume() {
        super.onResume();

        mChronometer.setBase(getApp().getCurrentTime() + SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        getApp().setCurrentTime(mChronometer.getBase() - SystemClock.elapsedRealtime());
    }
}
