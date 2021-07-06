package com.example.LivFit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class DisplayBMI extends AppCompatActivity {

    private TextView bmi , weightType , calgoal;
    private Button goDash;
    private DatabaseReference databaseReference;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_b_m_i);

        bmi = findViewById(R.id.tvBMI);
        weightType = findViewById(R.id.tvBMIResult);
        calgoal = findViewById(R.id.tvCalories);
        goDash = findViewById(R.id.btnVisitDashBoard);

        //get data from previous page
        String getBmi = getIntent().getStringExtra("KeyBMI");
        String getWeightType = getIntent().getStringExtra("KeyWeightType");
        String getCalGoal = getIntent().getStringExtra("KeyCalGoal");

        Double conBmi = Double.parseDouble(getBmi);
        Double conGoal = Double.parseDouble(getCalGoal);

        //setting the text to display on this page
        bmi.setText(df.format(conBmi));
        weightType.setText(getWeightType);
        calgoal.setText(df.format(conGoal));

        goDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get data from database
                databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String calGoal = snapshot.child("calGoal").getValue().toString();
                        String calConsumption = snapshot.child("calConsumption").getValue().toString();
                        String calBurned = snapshot.child("calBurned").getValue().toString();
                        String watercount = snapshot.child("waterCount").getValue().toString();

                        //bind retrieved data to the intent to show in dashboard
                        Intent regDashIntent = new Intent(DisplayBMI.this,Dashboard.class);
                        regDashIntent.putExtra("KeyDashCalGoal" , calGoal);
                        regDashIntent.putExtra("KeyDashCalConsumption" , calConsumption);
                        regDashIntent.putExtra("KeyDashCalBurned" , calBurned);
                        regDashIntent.putExtra("KeyDashWaterCount" , watercount);

                        startActivity(regDashIntent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}