package com.example.habittracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private  RecyclerView recyclerView;
    private HabitAdapter habitAdapter;
    private ArrayList<Habit> habitList;

    private Button buttonSettings;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("HabitPreferences",MODE_PRIVATE);

        loadHabitList();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonSettings = findViewById(R.id.buttonSettings);

        // Set up the onClickListener to open SettingsActivity
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsActivity when button is clicked
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        habitAdapter = new HabitAdapter(habitList, new HabitAdapter.onHabitCheckedListener(){
            @Override
            public void onHabitChecked(int position, boolean isChecked) {
                Habit habit = habitList.get(position);
                habit.setCompleted(isChecked);
                updateHabitInSharedPreferences(habit);
            }
        });
        recyclerView.setAdapter(habitAdapter);



        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_habit);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddEditHabitActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loadHabitList() {

        Gson gson = new Gson();
        String json = sharedPreferences.getString("habits",null);
        Type type = new TypeToken<ArrayList<Habit>>() {}.getType();
        habitList = gson.fromJson(json, type);

        if(habitList==null){
            habitList= new ArrayList<>();
        }
    }

    private void updateHabitInSharedPreferences(Habit habit) {
        Gson gson = new Gson();
        String json = gson.toJson(habitList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("habits", json);
        editor.apply();
    }


}