package com.example.habittracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddEditHabitActivity extends AppCompatActivity {

    private EditText editTextName, editTextDescription;
    private NumberPicker numberPickerGoal;
    private SharedPreferences sharedPreferences;

    private boolean isEditMode = false;

    private int habitIndex=-1;


    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_edit_habit);


        editTextName=findViewById(R.id.editTextHabitName);
        editTextDescription=findViewById(R.id.editTextDescription);
        numberPickerGoal=findViewById(R.id.numberPickerGoal);
        Button saveButton = findViewById(R.id.buttonSaveHabit);

        sharedPreferences = getSharedPreferences("HabitPreferences",MODE_PRIVATE);

        numberPickerGoal.setMinValue(1);
        numberPickerGoal.setMaxValue(30);

        saveButton.setOnClickListener(v->saveHabit());
    }

    private void saveHabit(){

        String name = editTextName.getText().toString();
        String desription = editTextDescription.getText().toString();
        int goal = numberPickerGoal.getValue();

        if(name.isEmpty()){
            Toast.makeText(this,"Please enter habit name",Toast.LENGTH_SHORT).show();
            return;
        }
        Habit newHabit = new Habit(name,desription);
        newHabit.setStreak(0);


        Gson gson = new Gson();
        String json = sharedPreferences.getString("habits",null);
        ArrayList<Habit> habitList = gson.fromJson(json,ArrayList.class);

        if(habitList==null)
        {
            habitList = new ArrayList<>();
        }
        habitList.add(newHabit);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        String updatedJson = gson.toJson(habitList);
        editor.putString("habits",updatedJson);
        editor.apply();

        Toast.makeText(this,"habit saved!",Toast.LENGTH_SHORT).show();
        finish();
    }

}