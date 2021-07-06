package com.example.LivFit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;

public class SnackUpdate extends AppCompatActivity {

    TextView textView1, textView2;
    EditText editText;
    Button button;
    DatabaseReference snack;
    String name;
    String calories;
    String quantity;
    int textCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_update);

        button = (Button)findViewById(R.id.supdate);
        textView1 = (TextView)findViewById(R.id.sname);
        textView2 = (TextView)findViewById(R.id.scal);
        editText = (EditText)findViewById(R.id.sqty);

        snack = FirebaseDatabase.getInstance().getReference().child("Meal").child("m4");
        snack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.child("mname").getValue().toString();
                calories = snapshot.child("calories").getValue().toString();

                textView1.setText(" "+name);
                textView2.setText(calories);

                textCal = Integer.parseInt(textView2.getText().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantity = editText.getText().toString();

                if(quantity.isEmpty()){
                    editText.setError("Quantity is required");
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "Quantity field can't be empty", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if(editText.getText().toString().length()>1){
                    editText.setError("Enter Valid Quantity");
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "Enter valid quantity", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else{
                    getcalsnak();
                }
            }
        });
    }

    public void getcalsnak(){

        int a = textCal;
        int b = Integer.parseInt(editText.getText().toString());
        int cal = a*b;

        HashMap map = new HashMap();
        map.put("calories", cal);
        snack.updateChildren(map);

        MDToast mdToast = MDToast.makeText(getApplicationContext(), "Calculated Successfully", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.show();

    }
}