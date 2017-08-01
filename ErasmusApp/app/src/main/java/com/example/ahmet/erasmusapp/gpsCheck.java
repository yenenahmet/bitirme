package com.example.ahmet.erasmusapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;

/**
 * Created by Yenen on 15.4.2017.
 */
public class gpsCheck {

    public static boolean isGPSEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static void enableGps(final Context context, final SwitchCompat switchCompat){
        if(!isGPSEnabled(context)){
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("GPS KONTROL")
                    .setCancelable(false)
                    .setPositiveButton("AYARLAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gpsSettingsIntent(context);
                        }
                    })
                    .setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            switchCompat.setChecked(false);
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public static void gpsSettingsIntent(Context context){
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
