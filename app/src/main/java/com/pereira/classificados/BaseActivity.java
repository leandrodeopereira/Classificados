package com.pereira.classificados;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Aluno on 10/01/2017.
 */

public class BaseActivity extends Activity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
//        Log.i(TAG, "Message");
//        Log.w(TAG, "Warning");
//        Log.e(TAG, "Error");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d(TAG, "onBackPressed");
    }
}

