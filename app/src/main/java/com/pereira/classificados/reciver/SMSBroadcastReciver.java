package com.pereira.classificados.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.pereira.classificados.sevice.ToastService;

import java.util.Objects;

/**
 * Created by Android on 16/01/2017.
 */

public class SMSBroadcastReciver extends BroadcastReceiver {
    // registra no manifest, ler informacoes do sms
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Objects.equals( intent.getAction(),"android.provider.Telephony.SMS_RECEIVED")){
            SmsMessage sms = Telephony.Sms.Intents.getMessagesFromIntent(intent)[0];

            Toast.makeText(context, String.format("From: %s \n Msg: %s", sms.getOriginatingAddress(),
                    sms.getDisplayMessageBody()), Toast.LENGTH_SHORT).show();

            Intent intentService = new Intent(context, ToastService.class);
            intentService.putExtra(ToastService.KEY_MSG, "I'm Running!!!");
            context.startService(intentService);
        }
    }
}
