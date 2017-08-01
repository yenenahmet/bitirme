package com.example.ahmet.erasmusapp.Socket_Servis;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.TabbedActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Yenen on 9.4.2017.
 */
public class Cevrimici_Kontrol_Servis_Socket extends Service implements LocationListener {
    private LocationManager locationManager;
    public static final  int zaman  = 1000 * 60*100; /// 1dakika
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//TODO do something useful
        return Service.START_STICKY;
    }
    @Override
    public void onCreate() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
       locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, zaman, 0, this);

    }


    @Override
    public void onLocationChanged(final Location location) {
        SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(Cevrimici_Kontrol_Servis_Socket.this);
        SharedPreferences.Editor editor = preferences.edit();
       final int userid = preferences.getInt("userid",0);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    Log.e("update oldu servis ",String.valueOf(location.getLatitude()));
                    TCPIstemci.main(location,userid);
                } catch (IOException e) {
                    Log.e("Servis","Socket patladi");
                }
            }
        };

        thread.start();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
 class TCPIstemci {
     public static void main(Location location,int userid) throws IOException {
         Socket socket = null;
         PrintWriter out = null;
         BufferedReader in = null;
         final String hOST = "localhost";
         final int pORT = 8001;
         try {
             socket = new Socket("192.168.43.201", pORT); // "localhost" ya da sunucu IP adresi
             // input stream ve output stream olusuyor
             out = new PrintWriter(socket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         } catch (UnknownHostException e) {
             Log.e("Sunucuya bulunamadi.","Socket");
         } catch (IOException e) {
             Log.e("Sunucuya bulunamadi.",e.getMessage());
         }
         Log.e("Sunucuya baglanildi.","Socket");


        try {
            out.println(String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()+","+String.valueOf(userid)));
            Log.e("Sunucudan Gelen :",String.valueOf(in.readLine()));
            out.close();
            in.close();
            socket.close();
        }catch (Exception ex) {
        }
         Log.e("Baglanti kesiliyor...","Socket");

     }

 }

