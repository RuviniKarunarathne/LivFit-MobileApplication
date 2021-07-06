package com.example.LivFit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class PushupWorkout extends AppCompatActivity {

    ImageView imageView;
    private Button confirm,update;
    TextView a,b,burntnum;
    DatabaseReference reff;
    private EditText duration;
    private Double calburn, totcal;
    int count;
    String wtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushup_workout);

        imageView=(ImageView)findViewById(R.id.imageViewpshups);
        a=(TextView)findViewById(R.id.textViewpshups);
        b=(TextView)findViewById(R.id.textViewcalpmin);

        Intent intent = getIntent();
        wtype=intent.getStringExtra("workout");
        reff=FirebaseDatabase.getInstance().getReference().child("Workout").child(wtype);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("name").getValue().toString();
                String calburnt = snapshot.child("calburnt").getValue().toString();
                String link = snapshot.child("image").getValue(String.class);

                Picasso.get().load(link).into(imageView);
                a.setText(name);
                b.setText(calburnt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        duration =(EditText)findViewById(R.id.editTextdurationw);
        burntnum=(TextView)findViewById(R.id.textViewbrntnum);

        confirm =(Button) findViewById(R.id.dinbtn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(duration.getText())){
                    duration.setError("Enter Duration");
                }else {
                    calburn = calculateCalBurnt(duration, b);
                    String getcalburn = String.valueOf(calburn);
                    burntnum.setText(getcalburn);
                    Toast.makeText(PushupWorkout.this, "Calories burnt calculated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update =(Button) findViewById(R.id.wcalinbtn);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Double burntcal = Double.parseDouble(burntnum.getText().toString());
                    reff = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           // String calBurned = snapshot.child("calBurned").getValue().toString();
                          //  String btnum = burntnum.toString();
                            // totcal = calDisplay(calBurned, btnum);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                    HashMap hashMap = new HashMap();
                    hashMap.put("calBurned", burntcal);
                    reff.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(PushupWorkout.this, "Calories Burnt Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        });
    }

    public void increment(View v){
        duration =(EditText)findViewById(R.id.editTextdurationw);
        count++;
        duration.setText(""+count);
    }
    public void decrement(View v){
        duration =(EditText)findViewById(R.id.editTextdurationw);
        if(count<=0) count=0;
        else count--;
        duration.setText(""+count);
    }

    public Double calculateCalBurnt(EditText duration, TextView b){

        int cvduration = Integer.parseInt(duration.getText().toString());
        Double cvcalburn = Double.parseDouble(b.getText().toString());

        return cvcalburn*cvduration;
    }

    public Double calDisplay(String calburned, String burnedav){
        Double cvcalburned=Double.parseDouble(calburned);
        Double cvburnedav=Double.parseDouble(burnedav);
        Double totcal;

        totcal=cvburnedav+cvcalburned;
        return totcal;
    }
}



