package com.pereira.classificados.sevice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by Android on 16/01/2017.
 */
    // executar tarefas de longa duracao, colocar em service
    // nao eh a thread de UI, mas pode tranca-la
    // precisa registra-lo no manifest
public class ToastService extends IntentService {

    public static final String KEY_MSG = "KEY_MSG";
    public static final String ACTION_FILTER = "ACTION_FILTER";

    public ToastService(){
        super("ToastService"); // identificador do service
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final String msg = intent.getStringExtra(KEY_MSG);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!Thread.interrupted()){
                    try {
                        Thread.sleep(5 * 1000); // 5 seg
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // nao funciona o que esta em comentario!!! Toas tem que ser apenas na UI
                    //Toast.makeText(ToastService.this, msg, Toast.LENGTH_SHORT).show();
                    Intent intentBrod = new Intent(ACTION_FILTER);
                    intentBrod.putExtra(KEY_MSG, msg);
                    LocalBroadcastManager.getInstance(ToastService.this).sendBroadcast(intentBrod);
                }
            }
        }).start();
    }
}
