package com.example.sarahn.waterreminderapp.Utils;

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


import com.example.sarahn.waterreminderapp.R;
import com.example.sarahn.waterreminderapp.activities.ActMainScreen;
import com.example.sarahn.waterreminderapp.classes.ClsComfirmationDialog;
import com.example.sarahn.waterreminderapp.classes.ClsRequirementCalculator;
import com.example.sarahn.waterreminderapp.classes.ClsTimePickerDialogBuilder;

/**
 * Created by SarahN on 6/18/2017.
 */
public class NotificationUtills {

    public static void notificationBuilder(Context context){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent(context)).setContentTitle("Notification")
                .setContentText("Im notification").setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setLights(0xFF0000FF, 4000, 1000)

                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))

               // .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bells))
                .setAutoCancel(true);

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(001, builder.build());
    }

    private static PendingIntent pendingIntent(Context context){
        Intent intent = new Intent(context, ActMainScreen.class);
        //wrap intent into pending intent
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

//    private static NotificationCompat.Action incrementWaterCount(Context context){
//
//        Intent intent = new Intent(context, IntentServiceWaterReminder.class);
//        intent.setAction(ReminderTask.ACTION_INCREMENT_WATER);
//
//        PendingIntent pendingIntent = PendingIntent.getService(context, 001, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        NotificationCompat.Action drinkWater = new NotificationCompat.Action(R.mipmap.ic_launcher, "I did it!",pendingIntent);
//        return drinkWater;
//    }


    public static float NotificationCounter(){
        float diff = ClsTimePickerDialogBuilder.timeDifference;
        int waterGlass = ClsRequirementCalculator.mlToGlass();

        return  (Math.abs(diff)) * 60 / waterGlass;
    }





}
