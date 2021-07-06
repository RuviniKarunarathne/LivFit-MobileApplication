package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArmsWorkout extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_workout);

        btn4=(Button)findViewById(R.id.tricep);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w9");
            }
        });
        btn3=(Button)findViewById(R.id.pcurl);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w8");
            }
        });
        btn2=(Button)findViewById(R.id.bicurl);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w7");
            }
        });
        btn1=(Button)findViewById(R.id.barcurl);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w10");
            }
        });
    }

    public void openDynamicWorkout(String wtypy){
        Intent intent = new Intent(this, PushupWorkout.class);
        intent.putExtra("workout",wtypy);
        startActivity(intent);
    }
}