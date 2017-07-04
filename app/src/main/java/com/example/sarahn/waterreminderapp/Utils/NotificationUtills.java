package com.example.sarahn.waterreminderapp.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;


import com.example.sarahn.waterreminderapp.R;
import com.example.sarahn.waterreminderapp.activities.MainScreen;
import com.example.sarahn.waterreminderapp.classes.ClsRequirementCalculator;
import com.example.sarahn.waterreminderapp.classes.MyApplication;
import com.example.sarahn.waterreminderapp.dialogs.ClsTimePickerDialogBuilder;
import com.example.sarahn.waterreminderapp.classes.ConsumedService;

/**
 * Created by SarahN on 6/18/2017.
 */
public class NotificationUtills {

    public final static String ACTION_INCREMENT_WATER = "increment_water";
    public final static String ACTION_CANCEL = "cancel";

    public static void notificationBuilder(Context context){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent(context)).setContentTitle("Notification")
                .setContentText("Drink Water").setSmallIcon(R.drawable.notification)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setLights(0xFF0000FF, 2000, 1000)

                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .addAction(incrementWaterCount(context))
                        .addAction(cancelNotification(context))
               // .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bells))
                .setAutoCancel(true);

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(001, builder.build());
    }

    private static PendingIntent pendingIntent(Context context){
        Intent intent = new Intent(context, MainScreen.class);
        //wrap intent into pending intent
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static NotificationCompat.Action incrementWaterCount(Context context){

        Intent intent = new Intent(context, ConsumedService.class);
        intent.setAction(ACTION_INCREMENT_WATER);

        PendingIntent pendingIntent = PendingIntent.getService(context, 001, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action drinkWater = new NotificationCompat.Action(R.drawable.done, "I did it!",pendingIntent);
        return drinkWater;
    }

    private static NotificationCompat.Action cancelNotification(Context context){

        Intent intent = new Intent(context, ConsumedService.class);
           intent.setAction(ACTION_CANCEL);

        PendingIntent pendingIntent = PendingIntent.getService(context, 001, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action drinkWater = new NotificationCompat.Action(R.drawable.clear, "Cancel",pendingIntent);
        return drinkWater;
    }

    public static void cancelAll(Context context){

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }


    public static int NotificationCounter(){
        int diff = ClsTimePickerDialogBuilder.timeDifference;
        Logging.logMessage("diff in not " + diff);
        int waterGlass = SharedPrefUtils.getRequired(MyApplication.getContext())/250;
        Logging.logMessage("water in not " + waterGlass);

        Logging.logMessage("minutes in not " + (Math.abs(diff)) * 60 / waterGlass);
        int i = (Math.abs(diff)) * 60 / waterGlass;
        return i;
       // return i / 60;

    }
}
