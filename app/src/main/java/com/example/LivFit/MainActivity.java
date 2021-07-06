package com.example.LivFit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button button1;//buttons for nut and water
    Button button2;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8;
    DatabaseReference breakfast;//ref for database
    DatabaseReference lunch;
    DatabaseReference dinner;
    DatabaseReference snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding the views
        button1 = (Button)findViewById(R.id.nutrition);
        button2 = (Button)findViewById(R.id.water);
        textView1 = (TextView)findViewById(R.id.b1);
        textView2 = (TextView)findViewById(R.id.b2);
        textView3 = (TextView)findViewById(R.id.l1);
        textView4 = (TextView)findViewById(R.id.l2);
        textView5 = (TextView)findViewById(R.id.d1);
        textView6 = (TextView)findViewById(R.id.d2);
        textView7 = (TextView)findViewById(R.id.s1);
        textView8 = (TextView)findViewById(R.id.s2);
        imageButton1 = (ImageButton)findViewById(R.id.bdelete);
        imageButton2 = (ImageButton)findViewById(R.id.bedit);
        imageButton3 = (ImageButton)findViewById(R.id.ldelete);
        imageButton4 = (ImageButton)findViewById(R.id.ledit);
        imageButton5 = (ImageButton)findViewById(R.id.ddelete);
        imageButton6 = (ImageButton)findViewById(R.id.dedit);
        imageButton7 = (ImageButton)findViewById(R.id.sdelete);
        imageButton8 = (ImageButton)findViewById(R.id.sedit);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move from one act to another
                Intent intent = new Intent(MainActivity.this, NeutritionInfo.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterIntake.class);
                startActivity(intent);
            }
        });

        //extract info from the meal table
        breakfast = FirebaseDatabase.getInstance().getReference().child("Meal").child("m1");
        //method to retrive data
        breakfast.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //object to retrive data
                String name = snapshot.child("mname").getValue().toString();//assign to a variable
                String calories = snapshot.child("calories").getValue().toString();

                textView1.setText(" "+name);//catt tv to set the retrived value
                textView2.setText("Calories:- "+calories);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lunch = FirebaseDatabase.getInstance().getReference().child("Meal").child("m2");
        lunch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("mname").getValue().toString();
                String calories = snapshot.child("calories").getValue().toString();

                textView3.setText(" "+name);
                textView4.setText("Calories:- "+calories);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dinner = FirebaseDatabase.getInstance().getReference().child("Meal").child("m3");
        dinner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("mname").getValue().toString();
                String calories = snapshot.child("calories").getValue().toString();

                textView5.setText(" "+name);
                textView6.setText("Calories:- "+calories);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        snack = FirebaseDatabase.getInstance().getReference().child("Meal").child("m4");
        snack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("mname").getValue().toString();
                String calories = snapshot.child("calories").getValue().toString();

                textView7.setText(" "+name);
                textView8.setText("Calories:- "+calories);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //delete button for brkfast
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to delete your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                breakfast.removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to delete your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                lunch.removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to delete your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dinner.removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to delete your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                snack.removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to update your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MainActivity.this, BreakfastUpdate.class);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to update your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MainActivity.this, LunchUpdate.class);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder1.create();
                alert.show();
            }
        });

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to update your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(MainActivity.this, DinnerUpdate.class);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        //refer updates in main acts
        //pass from alert box to update page
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you want to update your details?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(MainActivity.this, SnackUpdate.class);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }
}