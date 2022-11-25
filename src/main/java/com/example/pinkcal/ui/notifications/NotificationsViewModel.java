package com.example.pinkcal.ui.notifications;

import static android.provider.Settings.System.getString;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pinkcal.MainActivity2;
import com.example.pinkcal.R;

public class NotificationsViewModel extends IntentService {
    private static int ID = 3;
    private NotificationsViewModel notificationsViewModel;
    private PendingIntent pendingIntent;
    private static int NOTIFICATION_ID = 1;
    Notification notification;
    private NotificationManager notificationManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationsViewModel(String name) {
        super(name);
    }

    public NotificationsViewModel(){
        super("SERVICE");
    }

    @SuppressLint("ServiceCast")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String NOTIFICATION_CHANNEL_ID= getApplicationContext().getString(R.string.app_name);
        Context context = this.getApplicationContext();
        notificationsViewModel=(NotificationsViewModel) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity2.class);
        Resources resources = this.getResources();
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        String message= getString(R.string.new_notificacion);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            final int NOTIFY_ID = 0; //TD of notifications
            String id = NOTIFICATION_CHANNEL_ID; //default channel id
            String title = NOTIFICATION_CHANNEL_ID; //default channel
            PendingIntent pendingIntent;
            NotificationCompat.Builder builder;
            NotificationManager notificationManager= (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
            if (notificationManager==null){
                notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            }
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = notificationManager.getNotificationChannel(id);
            }
            if (notificationChannel==null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = new NotificationChannel(id,title,importance);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel.enableVibration(true);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400 });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
            builder =new NotificationCompat.Builder(context,id);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent=PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle(getString(R.string.app_name)).setCategory(Notification.CATEGORY_SERVICE)
                    .setSmallIcon(R.mipmap.logo_pinkcal) //required
                    .setContentText(message)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.button_rounded))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{100,200,300,400,500,400,300,200,400 });
            Notification notification=builder.build();
            notificationManager.notify(NOTIFICATION_ID, notification);

            startForeground(1,notification);

        } else {
            pendingIntent=PendingIntent.getActivity(context,1,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            notification=new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.logo_pinkcal)
                    .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.button_rounded))
                    .setSound(soundUri)
                    .setAutoCancel(true)
                    .setContentTitle(getString(R.string.app_name)).setCategory(Notification.CATEGORY_SERVICE)
                    .setContentText(message).build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
}




