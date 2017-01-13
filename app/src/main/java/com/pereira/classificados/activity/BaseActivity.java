package com.pereira.classificados.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.pereira.classificados.R;

/**
 * Created by Aluno on 10/01/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
//        Log.i(TAG, "Message");
//        Log.w(TAG, "Warning");
//        Log.e(TAG, "Error");
    }

    protected SharedPreferences getPref(){
        return getPreferences(MODE_PRIVATE);
    }

    protected void setupToolbar(int title){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        //criar o botao de voltar na toolbar(sem a acao)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Acao do botao de voltar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //nome do botao(id) eh home
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    //Animacao para mostrar lista
    protected  void replaceView(View oldView, View newView ){
        Animation animOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation animIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        oldView.setVisibility(View.INVISIBLE);
        oldView.startAnimation(animOut);

        newView.setVisibility(View.VISIBLE);
        newView.startAnimation(animIn);
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

