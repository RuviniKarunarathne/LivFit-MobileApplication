package com.example.LivFit.Model;

public class Workout {
    String name;
    int  calburnt;

    public Workout(String name, int calburnt) {
        this.name = name;
        this.calburnt = calburnt;
    }

    public String getName() {
        return name;
    }

    public int getCalburnt() {
        return calburnt;
    }
}