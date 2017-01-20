package com.pereira.classificados.sevice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pereira.classificados.R;
import com.pereira.classificados.activity.ListActivity;

/**
 * Created by Android on 20/01/2017.
 */
//MESSAGEM DO FIREBASE
public class FCMMessageService extends FirebaseMessagingService {

    private static final String TAG = "FCMMessageService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification().getBody() != null){
            Log.d(TAG, remoteMessage.getNotification().getBody());

            Message msg = new Message();
            msg.obj = remoteMessage.getNotification().getBody();

            new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(android.os.Message msg) {
                    super.handleMessage(msg);
//                    Toast.makeText(getApplicationContext(), msg.obj.toString(),
//                            Toast.LENGTH_SHORT).show();

                    NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Nova Mensagem")
                            .setContentText(msg.obj.toString())
                            .setAutoCancel(true); //quando clicar sumir
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                            0, intent, PendingIntent.FLAG_ONE_SHOT);

                    notification.setContentIntent(pendingIntent);

                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    manager.notify(0,notification.build());


                }
            }.sendMessage(msg);
        }

        if(remoteMessage.getData() != null){
            Log.d(TAG, remoteMessage.getData().toString());
        }
    }
}
