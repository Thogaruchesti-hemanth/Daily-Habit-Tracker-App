package com.example.habittracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder>{

    private List<Habit> habitList;
    private onHabitCheckedListener listener;

    public interface onHabitCheckedListener {

        public void onHabitChecked(int position,boolean isChecked);
    }

    public HabitAdapter(List<Habit> habitList, onHabitCheckedListener listener) {
        this.habitList = habitList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item,parent,false);
        return new HabitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {

        Habit habit = habitList.get(position);
        holder.habitName.setText(habit.getName());
        holder.streakText.setText("Streak: " + habit.getStreak() + " days");
        holder.checkBox.setChecked(habit.isCompleted());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            listener.onHabitChecked(position, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder{

        TextView habitName;
        TextView streakText;
        CheckBox checkBox;
        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName=itemView.findViewById(R.id.habitName);
            streakText=itemView.findViewById(R.id.streakTextView);
            checkBox=itemView.findViewById(R.id.checkBox);
        }
    }

}