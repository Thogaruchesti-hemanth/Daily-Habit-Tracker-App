package com.example.habittracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.util.Calendar;

public class NotificationHelper {

    public static void setReminder(Context context, int hour, int minute){

        AlarmManager alarmManager=(AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent intent = new Intent(context,ReminderReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(context,"Reminder set for "+hour+":"+minute,Toast.LENGTH_SHORT).show();

    }

    public  static void cancelReminder(Context context){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context,"Reminder canceled",Toast.LENGTH_SHORT).show();
    }
}
