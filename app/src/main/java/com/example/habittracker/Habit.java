package com.example.habittracker;

public class Habit {
    private String name;
    private String description;
    private boolean isCompleted;
    private int streak;

    public Habit(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted =false;
        this.streak =0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
