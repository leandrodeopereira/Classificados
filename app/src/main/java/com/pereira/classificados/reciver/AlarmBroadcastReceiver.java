package com.pereira.classificados.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.pereira.classificados.activity.ListActivity;

/**
 * Created by Android on 17/01/2017.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    public static final String MSG_KEY = "MSG_KEY";

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra(MSG_KEY);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

//        Intent intent2 = new Intent(context, ListActivity.class);
//        context.startActivity(intent2);
    }
}
