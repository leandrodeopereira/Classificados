package com.pereira.classificados.sevice;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Android on 20/01/2017.
 */
//REGISTRA O FIREBASE
public class FCMIntanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMIntanceIdService", token);

        //Mandar novo token para seu server
    }
}
