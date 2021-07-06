package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CardioWorkout extends AppCompatActivity {



    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_workout);

        btn6=(Button)findViewById(R.id.mntnbtn);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w6");
            }
        });
        btn5=(Button)findViewById(R.id.crunches);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w2");
            }
        });
        btn4=(Button)findViewById(R.id.plank);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w3");
            }
        });
        btn3=(Button)findViewById(R.id.jumpingjackbtn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w4");
            }
        });
        btn2=(Button)findViewById(R.id.hykneebtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDynamicWorkout("w5");
            }
        });
        btn1=(Button)findViewById(R.id.pshups);
        btn1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                openDynamicWorkout("w1");
            }
        });
    }

    public void openDynamicWorkout(String wtypy){
        Intent intent = new Intent(this, PushupWorkout.class);
        intent.putExtra("workout",wtypy);
        startActivity(intent);
    }
}
