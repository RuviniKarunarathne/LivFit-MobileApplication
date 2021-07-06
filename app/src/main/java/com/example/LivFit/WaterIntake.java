package com.example.LivFit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdesekamdem.library.mdtoast.MDToast;

public class WaterIntake extends AppCompatActivity {

    EditText editText;
    Button button, button2;
    DatabaseReference waterIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);

        //binding the view
        editText = (EditText)findViewById(R.id.waternumber);
        button = (Button)findViewById(R.id.submit);
        button2 = (Button)findViewById(R.id.submit2);

        //button to dashboard

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waterIntent = new Intent(WaterIntake.this,Dashboard.class);
                startActivity(waterIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation
                String waterCount = editText.getText().toString();

                if(waterCount.isEmpty()){

                    editText.setError("Water cups count required!");
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "Water cups count required!", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();

                }
                else{
                    //saving data in the db
                    waterIntake = FirebaseDatabase.getInstance().getReference().child("User").child("user1").child("waterCount");
                    waterIntake.setValue(waterCount);
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "Water count entered successfully", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                    mdToast.show();

                }
            }
        });
    }
}