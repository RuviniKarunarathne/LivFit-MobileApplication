package com.example.LivFit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.valdesekamdem.library.mdtoast.MDToast;

public class NeutritionInfo extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    DatabaseReference breakfast;
    DatabaseReference lunch;
    DatabaseReference dinner;
    DatabaseReference snack;
    int bcal;
    int bprot;
    int bfat;
    int bcabo;
    int bcholost;
    int bfiber;

    int lcal;
    int lprot;
    int lfat;
    int lcabo;
    int lcholost;
    int lfiber;

    int dcal;
    int dprot;
    int dfat;
    int dcabo;
    int dcholost;
    int dfiber;

    int scal;
    int sprot;
    int sfat;
    int scabo;
    int scholost;
    int sfiber;

    TextView bcall;
    TextView bproo;
    TextView bfatt;
    TextView bcaboo;
    TextView bcholostr;
    TextView bfiberr;

    TextView lcall;
    TextView lproo;
    TextView lfatt;
    TextView lcaboo;
    TextView lcholostr;
    TextView lfiberr;

    TextView dcall;
    TextView dproo;
    TextView dfatt;
    TextView dcaboo;
    TextView dcholostr;
    TextView dfiberr;

    TextView scall;
    TextView sproo;
    TextView sfatt;
    TextView scaboo;
    TextView scholostr;
    TextView sfiberr;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutrition_info);

        button =(Button) findViewById(R.id.button2);

        textView1 = (TextView)findViewById(R.id.tcaloriy);
        textView2 = (TextView)findViewById(R.id.tpro);
        textView3 = (TextView)findViewById(R.id.tfat);
        textView4 = (TextView)findViewById(R.id.tcabo);
        textView5 = (TextView)findViewById(R.id.tcholost);
        textView6 = (TextView)findViewById(R.id.tfiber);

        bcall = (TextView)findViewById(R.id.breakcal);
        bproo = (TextView)findViewById(R.id.breakpro);
        bfatt = (TextView)findViewById(R.id.breakfat);
        bcaboo = (TextView)findViewById(R.id.breakcabo);
        bcholostr = (TextView)findViewById(R.id.breakcholos);
        bfiberr = (TextView)findViewById(R.id.breakfiber);

        lcall = (TextView)findViewById(R.id.lunchcal);
        lproo = (TextView)findViewById(R.id.lunchpro);
        lfatt = (TextView)findViewById(R.id.lunchfat);
        lcaboo = (TextView)findViewById(R.id.lunchcarbo);
        lcholostr = (TextView)findViewById(R.id.lunchcholos);
        lfiberr = (TextView)findViewById(R.id.lunchfiber);

        dcall = (TextView)findViewById(R.id.dinnercal);
        dproo = (TextView)findViewById(R.id.dinnerpro);
        dfatt = (TextView)findViewById(R.id.dinnerfat);
        dcaboo = (TextView)findViewById(R.id.dinnercabo);
        dcholostr = (TextView)findViewById(R.id.dinnercholos);
        dfiberr = (TextView)findViewById(R.id.dinnerfiber);

        scall = (TextView)findViewById(R.id.snackcal);
        sproo = (TextView)findViewById(R.id.snackpro);
        sfatt = (TextView)findViewById(R.id.snackfat);
        scaboo = (TextView)findViewById(R.id.snackcarbo);
        scholostr = (TextView)findViewById(R.id.snackcholos);
        sfiberr = (TextView)findViewById(R.id.snackfiber);

        breakfast = FirebaseDatabase.getInstance().getReference().child("Meal").child("m1");
        breakfast.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get values from the db to variables
                String calories = (snapshot.child("calories").getValue().toString());
                String protain = (snapshot.child("protein").getValue().toString());
                String fat = (snapshot.child("fat").getValue().toString());
                String cabohy = (snapshot.child("carb").getValue().toString());
                String cholostrol = (snapshot.child("cholesterol").getValue().toString());
                String fiber = (snapshot.child("fiber").getValue().toString());

                //set tvs
                bcall.setText(calories+"Kcal");
                bproo.setText(protain+"g");
                bfatt.setText(fat+"g");
                bcaboo.setText(cabohy+"g");
                bcholostr.setText(cholostrol+"g");
                bfiberr.setText(fiber+"g");

                //converts to int
                bcal = Integer.parseInt(calories);
                bprot = Integer.parseInt(protain);
                bfat = Integer.parseInt(fat);
                bcabo = Integer.parseInt(cabohy);
                bcholost = Integer.parseInt(cholostrol);
                bfiber = Integer.parseInt(fiber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lunch = FirebaseDatabase.getInstance().getReference().child("Meal").child("m2");
        lunch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String calories = (snapshot.child("calories").getValue().toString());
                String protain = (snapshot.child("protein").getValue().toString());
                String fat = (snapshot.child("fat").getValue().toString());
                String cabohy = (snapshot.child("carb").getValue().toString());
                String cholostrol = (snapshot.child("cholesterol").getValue().toString());
                String fiber = (snapshot.child("fiber").getValue().toString());

                lcall.setText(calories+"Kcal");
                lproo.setText(protain+"g");
                lfatt.setText(fat+"g");
                lcaboo.setText(cabohy+"g");
                lcholostr.setText(cholostrol+"g");
                lfiberr.setText(fiber+"g");

                lcal = Integer.parseInt(calories);
                lprot = Integer.parseInt(protain);
                lfat = Integer.parseInt(fat);
                lcabo = Integer.parseInt(cabohy);
                lcholost = Integer.parseInt(cholostrol);
                lfiber = Integer.parseInt(fiber);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dinner = FirebaseDatabase.getInstance().getReference().child("Meal").child("m3");
        dinner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String calories = (snapshot.child("calories").getValue().toString());
                String protain = (snapshot.child("protein").getValue().toString());
                String fat = (snapshot.child("fat").getValue().toString());
                String cabohy = (snapshot.child("carb").getValue().toString());
                String cholostrol = (snapshot.child("cholesterol").getValue().toString());
                String fiber = (snapshot.child("fiber").getValue().toString());

                dcall.setText(calories+"Kcal");
                dproo.setText(protain+"g");
                dfatt.setText(fat+"g");
                dcaboo.setText(cabohy+"g");
                dcholostr.setText(cholostrol+"g");
                dfiberr.setText(fiber+"g");

                dcal = Integer.parseInt(calories);
                dprot = Integer.parseInt(protain);
                dfat = Integer.parseInt(fat);
                dcabo = Integer.parseInt(cabohy);
                dcholost = Integer.parseInt(cholostrol);
                dfiber = Integer.parseInt(fiber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        snack = FirebaseDatabase.getInstance().getReference().child("Meal").child("m4");
        snack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String calories = (snapshot.child("calories").getValue().toString());
                String protain = (snapshot.child("protein").getValue().toString());
                String fat = (snapshot.child("fat").getValue().toString());
                String cabohy = (snapshot.child("carb").getValue().toString());
                String cholostrol = (snapshot.child("cholesterol").getValue().toString());
                String fiber = (snapshot.child("fiber").getValue().toString());

                scall.setText(calories+"Kcal");
                sproo.setText(protain+"g");
                sfatt.setText(fat+"g");
                scaboo.setText(cabohy+"g");
                scholostr.setText(cholostrol+"g");
                sfiberr.setText(fiber+"g");

                scal = Integer.parseInt(calories);
                bprot = Integer.parseInt(protain);
                bfat = Integer.parseInt(fat);
                bcabo = Integer.parseInt(cabohy);
                bcholost = Integer.parseInt(cholostrol);
                bfiber = Integer.parseInt(fiber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTotalCalories();
                getTotalProtains();
                getTotalFat();
                getTotalCabohydrade();
                getTotalCholostrol();
                getTotalFiber();

                MDToast mdToast = MDToast.makeText(getApplicationContext(), "Calculated successfully", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                mdToast.show();
            }
        });

    }

    public void getTotalCalories(){

    int a = bcal;
    int b= lcal;
    int c = dcal;
    int d= scal;

    int sum = a+b+c+d;
    textView1.setText(sum+"Kcal");
    System.out.println(sum);
    }

    public void getTotalProtains(){

        int a = bprot;
        int b= lprot;
        int c = dprot;
        int d= sprot;

        int sum = a+b+c+d;
        textView2.setText(sum+"g");
        System.out.println(sum);
    }

    public void getTotalFat(){

        int a = bfat;
        int b= lfat;
        int c = dfat;
        int d= sfat;

        int sum = a+b+c+d;
        textView3.setText(sum+"g");
        System.out.println(sum);
    }

    public void getTotalCabohydrade(){

        int a = bcabo;
        int b= lcabo;
        int c = dcabo;
        int d= scabo;

        int sum = a+b+c+d;
        textView4.setText(sum+"g");
        System.out.println(sum);
    }

    public void getTotalCholostrol(){

        int a = bcholost;
        int b= lcholost;
        int c = dcholost;
        int d= scholost;

        int sum = a+b+c+d;
        textView5.setText(sum+"g");
        System.out.println(sum);
    }

    public void getTotalFiber(){

        int a = bfiber;
        int b= lfiber;
        int c = dfiber;
        int d= sfiber;

        int sum = a+b+c+d;
        textView6.setText(sum+"g");
        System.out.println(sum);
    }

}