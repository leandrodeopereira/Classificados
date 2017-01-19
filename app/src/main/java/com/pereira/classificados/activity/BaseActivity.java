package com.pereira.classificados.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.pereira.classificados.R;
import com.pereira.classificados.sevice.ToastService;

/**
 * Created by Aluno on 10/01/2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    protected Toolbar mToolbar;

    private BroadcastReceiver receiverToast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra(ToastService.KEY_MSG),
                    Toast.LENGTH_SHORT).show();
        }
    };

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

    public void hideView(View view){
        Animation animOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        view.setVisibility(View.INVISIBLE);
        view.startAnimation(animOut);
    }

    //Animacao para mostrar lista
    public void replaceView(View oldView, View newView ){

        Animation animIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        hideView(oldView);

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

        // sempre no resume vai registrar o receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
                receiverToast, new IntentFilter(ToastService.ACTION_FILTER)
        );
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
        // desregistrar o receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiverToast);
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

