package com.example.myapplication;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;


public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

     @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG,token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
    }

}
