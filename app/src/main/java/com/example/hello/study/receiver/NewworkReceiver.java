package com.example.hello.study.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hello.study.MainActivity;

public class NewworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        ConnectivityManager networkManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = networkManager.getActiveNetworkInfo();

        String text = "network is available";

        if(networkInfo != null && networkInfo.isAvailable()){
            text = "network is available";
        } else {
            text = "network is not available";
        }

        Intent intentActivity = new Intent(context,MainActivity.class);
        intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentActivity);
    }
}
