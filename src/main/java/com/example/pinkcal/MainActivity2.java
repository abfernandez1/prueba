package com.example.pinkcal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pinkcal.ui.notifications.NotificationsFragment;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.fragment_alarma);

                findViewById(R.id.change_notification).setOnClickListener(view -> {
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(MainActivity2.this, new TimePickerDialog.OnTimeSetListener() {

                                private int alarmID;

                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        String finalHour, finalMinute;
                                        finalHour = "" + selectedHour;
                                        finalMinute = "" + selectedMinute;
                                        if (selectedHour < 10) finalHour = "0" + selectedHour;
                                        if (selectedMinute < 10) finalMinute = "0" + selectedMinute;
                                        NotificationsFragment.setText(finalHour + ":" + finalMinute);
                                        Calendar today = Calendar.getInstance();
                                        today.set(Calendar.HOUR_OF_DAY, selectedHour);
                                        today.set(Calendar.MINUTE, selectedMinute);
                                        today.set(Calendar.SECOND, 0);

                                        Toast.makeText(MainActivity2.this, getString(R.string.changed_to, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();


                                        setAlarm(alarmID, today.getTimeInMillis(), MainActivity2.this);
                                }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle(getString(R.string.select_time));
                        mTimePicker.show();


                });
        }


        private static void setAlarm(int i, Long timestamp, Context ctx) {

       AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(ctx, i, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
        }

        }
