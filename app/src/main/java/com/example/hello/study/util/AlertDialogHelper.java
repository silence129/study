package com.example.hello.study.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogHelper {

    public static AlertDialog.Builder build(Context context, String title, String message,
                                     DialogInterface.OnClickListener negativeListener,
                                     DialogInterface.OnClickListener positiveListener){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(message);

        if(negativeListener == null){
            dialogBuilder.setCancelable(false);
        } else {
            dialogBuilder.setPositiveButton("Cancel",negativeListener);
        }
        if (positiveListener != null){
            dialogBuilder.setPositiveButton("OK",positiveListener);
        }

        return dialogBuilder;
    }
}
