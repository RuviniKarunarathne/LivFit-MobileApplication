package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.internal.InternalTokenProvider;

import java.text.DecimalFormat;
import java.util.function.DoubleUnaryOperator;

public class Dashboard extends AppCompatActivity {

    private TextView goal , remaining , consumption , burned , water;
    private ImageButton toMeals , toWater , toWorkouts , toSteps , toSettings;
    private String remainCals;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        goal = findViewById(R.id.displayGoal);
        remaining = findViewById(R.id.displayRemCal);
        consumption = findViewById(R.id.displayConsumptionCal);
        burned = findViewById(R.id.displayBurnCal);
        water = findViewById(R.id.displayWaterConsumption);
        toMeals = findViewById(R.id.imgBtnAddMeals);
        toWater = findViewById(R.id.imgBtnAddWater);
        toWorkouts = findViewById(R.id.imgBtnAddWorkout);
        toSteps = findViewById(R.id.imgBtnSteps);
        toSettings = findViewById(R.id.imgBtnSettings);

        String getCalGoal = getIntent().getStringExtra("KeyDashCalGoal");
        String getCalConsumption = getIntent().getStringExtra("KeyDashCalConsumption");
        String getCalBurned = getIntent().getStringExtra("KeyDashCalBurned");
        String getWaterCount = getIntent().getStringExtra("KeyDashWaterCount");

        //calculation for remaining calories
        remainCals = calculateRemainingCalories(getCalGoal,getCalConsumption,getCalBurned);

        Double conCalGoal = Double.parseDouble(getCalGoal);
        Double conCalConsumption = Double.parseDouble(getCalConsumption);
        Double conCalBurned = Double.parseDouble(getCalBurned);
        Double conRemain = Double.parseDouble(remainCals);

        goal.setText(df.format(conCalGoal));
        remaining.setText(df.format(conRemain));
        consumption.setText(df.format(conCalConsumption));
        burned.setText(df.format(conCalBurned));
        water.setText(getWaterCount);

        //redirecting to relevent pages
        toWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waterIntent = new Intent(Dashboard.this,WaterIntake.class);
                startActivity(waterIntent);
            }
        });

        toWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workoutIntent =  new Intent(Dashboard.this,SearchWorkout.class);
                startActivity(workoutIntent);
            }
        });

        toSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stepIntent = new Intent(Dashboard.this,StepCounter.class);
                startActivity(stepIntent);
            }
        });

        toMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mealsIntent = new Intent(Dashboard.this,MainActivity.class);
                startActivity(mealsIntent);
            }
        });

        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(Dashboard.this,settings.class);
                startActivity(settingsIntent);
            }
        });


    }

    public String calculateRemainingCalories(String getCalGoal, String getCalConsumption, String getCalBurned) {
        Double convertedGoal = Double.parseDouble(getCalGoal);
        Double convertedConsumption = Double.parseDouble(getCalConsumption);
        Double convertedBurned = Double.parseDouble(getCalBurned);
        Double calculatedRemain;
        String sendRemain;

        calculatedRemain = convertedGoal - convertedConsumption + convertedBurned;

        sendRemain = calculatedRemain.toString();

        return sendRemain;

    }


}