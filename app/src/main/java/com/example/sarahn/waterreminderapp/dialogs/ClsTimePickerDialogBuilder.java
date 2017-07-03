package com.example.sarahn.waterreminderapp.dialogs;

import android.content.Context;

import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.example.sarahn.waterreminderapp.Utils.SharedPrefUtils;
import com.example.sarahn.waterreminderapp.classes.MyApplication;

import java.util.Calendar;

/**
 * Created by SarahN on 6/18/2017.
 */
public class ClsTimePickerDialogBuilder{

    public static int fromHour;
    public static int fromMin;
    public static int toHour;
    public static int toMin;
    public static int timeDifference;


    public static TimePickerDialog showDialog(final Context context){
        Calendar now = Calendar.getInstance();

        TimePickerDialog tpd;

        tpd = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                      @Override
                                                      public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1, int i2, int i3) {
                                                          fromHour = i;
                                                          fromMin = i1;
                                                          toHour = i2;
                                                          toMin = i3;

                                                          SharedPrefUtils.setStartHour(MyApplication.getContext(), i);
                                                          SharedPrefUtils.setEndHour(MyApplication.getContext(), i2);
                                                          SharedPrefUtils.setStartMin(MyApplication.getContext(), i1);
                                                          SharedPrefUtils.setEndMin(MyApplication.getContext(), i3);

                                                        ClsComfirmationDialog.displayDialog(context).show();
                                                          timeDifference = (fromMin - toMin) + ( fromHour - toHour);
                                                          timeDifferenceUtil(timeDifference, context);
                                                      }}, now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false);


        return tpd;
    }


    private static void timeDifferenceUtil(float td, Context context){
        if(timeDifference > 0){
            ClsComfirmationDialog.displayWarningDialog(context).show();
        }else if(Math.abs(timeDifference) <= 2 ){
            ClsComfirmationDialog.displayWarningDuration(context).show();
        }
    }

}