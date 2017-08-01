package com.example.ahmet.erasmusapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static  ListView özelmesajList;

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
              if (remoteMessage.getData().size() > 0) {
            Log.e("ARKAPALNDA","ÇALIŞTI");
            Log.d("ARKAPLANDA","ÇALIŞTI");
/// arkaplandayken çalışıyor
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String baslikk = remoteMessage.getData().get("baslikk").toString();
                        String mesaj = remoteMessage.getData().get("mesaj").toString();
                        String[] kelime =  baslikk.split(",");
                        SQL_Veritabanı_MesajModel model = new SQL_Veritabanı_MesajModel();
                        SQL_MesajVeritabanı dbHelper = new SQL_MesajVeritabanı(MyFirebaseMessagingService.this);
                        model.setBaskaUserid(Integer.parseInt(kelime[2]));
                        model.setKullanıcıAdi(kelime[0]);
                        model.setTarihZaman(kelime[1]);
                        model.setMesaj(mesaj);
                        dbHelper.insertCountry(model);
                        sendNotification(kelime[0],mesaj);
                        OzelMesaj ms = new OzelMesaj();
                        ms.MesajGeldi(kelime[2]);
                    }
                });
              }
            if (remoteMessage.getNotification() != null) {
              Log.e(TAG, "Mesaj Notification Başlığı: " + remoteMessage.getNotification().getTitle() + " " + "Mesaj Notification İçeriği: " + remoteMessage.getNotification().getBody());
              sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }
    private void sendNotification(String messageTitle,String messageBody) {
        Intent intent = new Intent(this, TabbedActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_ONE_SHOT);
        long[] pattern = {500,500,500,500};//Titreşim ayarı
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.faceiconn)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + this.getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationManager.notify(0 , notificationBuilder.build());
    }

}
