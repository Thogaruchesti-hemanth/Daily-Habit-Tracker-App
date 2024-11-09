package com.example.habittracker;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.widget.Switch;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.Buffer;

public class SettingsActivity extends AppCompatActivity {

    private Switch notifcationSwitch;
    private TimePicker timePicker;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_settings);

        sharedPreferences = getSharedPreferences("HabitPreferences",MODE_PRIVATE);


        notifcationSwitch =findViewById(R.id.switchNotifications);
        timePicker=findViewById(R.id.timePicker);


        loadSettings();

        notifcationSwitch.setOnCheckedChangeListener((buttonView,isChecked)->{

            saveSettings(isChecked,timePicker.getCurrentHour(),timePicker.getCurrentMinute());
            if(isChecked){
                Toast.makeText(SettingsActivity.this,"Notifications Enabled",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SettingsActivity.this,"Notifications Disabled",Toast.LENGTH_SHORT).show();
            }
        });

        timePicker.setOnTimeChangedListener((view,hourOfDay,minute)->{
            saveSettings(notifcationSwitch.isChecked(),hourOfDay,minute);
        });

    }

    private void loadSettings(){

        boolean isNotificationsEnabled = sharedPreferences.getBoolean("notificationsEnabled",false);
        int hour = sharedPreferences.getInt("notificationHour",8);
        int minute = sharedPreferences.getInt("notifcationMinute",0);
    }

    private void saveSettings(boolean notificationsEnabled , int hour,int minute){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("notficationEnabled",notificationsEnabled);
        editor.putInt("notifcationHour",hour);
        editor.putInt("notifcationMinute",minute);
        editor.apply();
    }
}
