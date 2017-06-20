package com.example.sarahn.waterreminderapp.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.example.sarahn.waterreminderapp.Utils.AlarmManagerUtils;
import com.example.sarahn.waterreminderapp.activities.ActMainScreen;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by SarahN on 6/19/2017.
 */
public class ClsComfirmationDialog {


    public static SweetAlertDialog displayDialog(final Context context){

       SweetAlertDialog dialog =  new SweetAlertDialog (context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Selected Time Span")
                .setContentText("From " + ClsTimePickerDialogBuilder.fromHour +
                        ": " + ClsTimePickerDialogBuilder.fromMin + " " + ClsAMOrPM.isAMOrPM(ClsTimePickerDialogBuilder.fromHour) + "To " + ClsTimePickerDialogBuilder.toHour +
                        ": " + ClsTimePickerDialogBuilder.toMin + " " + ClsAMOrPM.isAMOrPM(ClsTimePickerDialogBuilder.fromMin))

                .setCancelText("Set again")
                .setConfirmText("Confirm")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.cancel();

                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Selected Time Span")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        AlarmManagerUtils.setStartAlarm(context);
                                        AlarmManagerUtils.setEndAlarm(context);
                                        sweetAlertDialog.cancel();



                                        Intent intent = new Intent();
                                        intent.setClass(context, ActMainScreen.class);
                                        context.startActivity(intent);
                                    }
                                })
                                .setContentText("You can always change settings anytime from setting tab!")
                                .show();

                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        ClsTimePickerDialogBuilder.showDialog(context).show(((Activity) context).getFragmentManager(), "timepicker");
                        sDialog.cancel();

                    }
                });
              //  .show();

        return dialog;
    }
}
