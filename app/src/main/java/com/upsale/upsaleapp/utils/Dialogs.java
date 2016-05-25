package com.upsale.upsaleapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by fabiomurarolli on 5/4/15.
 */
public class Dialogs {
    public static void showDialog(final Activity activity, final String title, final String message){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                alertDialog.setTitle(title);
                alertDialog.setMessage(message);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

    }


    public static void showDialog(final Activity activity, final String title, final String[] items){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(title);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        Log.i("DIALOG", "Escolha: " + items[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
}
