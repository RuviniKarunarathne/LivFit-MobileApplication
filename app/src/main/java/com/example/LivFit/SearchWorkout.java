package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchWorkout extends AppCompatActivity {
    private ImageButton arms;
    private ImageButton legs;
    private ImageButton chest;
    private ImageButton cardio;
    private ImageButton shoulders;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_workout);

        arms= (ImageButton) findViewById(R.id.imageButtonarms);
        legs= (ImageButton) findViewById(R.id.imageButtonlegs);
        chest= (ImageButton) findViewById(R.id.imageButtonchest);
        cardio= (ImageButton) findViewById(R.id.imageButtoncardio);
        shoulders= (ImageButton) findViewById(R.id.imageButtonshuol);
        back= (ImageButton) findViewById(R.id.imageButtonback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackWorkout();
            }
        });
        shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShoulderWorkout();
            }
        });
        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardioWorkout();
            }
        });
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChestWorkout();
            }
        });
        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLegsWorkout();
            }
        });
        arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openArmsWorkout();
            }

        });
    }

    public void openArmsWorkout(){
        Intent intent = new Intent(this, ArmsWorkout.class);
        startActivity(intent);
    }
    public void openLegsWorkout(){
        Intent intent = new Intent(this, LegsWorkout.class);
        startActivity(intent);
    }
    public void openChestWorkout(){
        Intent intent = new Intent(this, ChestWorkout.class);
        startActivity(intent);
    }
    public void openCardioWorkout(){
        Intent intent = new Intent(this, CardioWorkout.class);
        startActivity(intent);
    }
    private void openShoulderWorkout(){
        Intent intent = new Intent(this, ShoulderWorkout.class);
        startActivity(intent);
    }
    private void openBackWorkout(){
        Intent intent = new Intent(this, BackWorkout.class);
        startActivity(intent);
    }
}