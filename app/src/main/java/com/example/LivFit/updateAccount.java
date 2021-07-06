package com.example.LivFit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.Range;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;

public class updateAccount extends AppCompatActivity {

    private EditText username , age , weight , height , targetWeight ;
    private Button updateBtn ;
    private DatabaseReference databaseReference , databaseReferencec;
    private AwesomeValidation awesomeValidation;
    private Double BMI , calorieGoal;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        username = findViewById(R.id.etUpdateUserName);
        age = findViewById(R.id.etUpdateAge);
        weight = findViewById(R.id.etUpdateCurWeight);
        height = findViewById(R.id.etUpdateCurrHeight);
        targetWeight = findViewById(R.id.etUpdateTargetWeight);
        updateBtn = findViewById(R.id.btnUpdateAccount);

        //checking the SDK version to create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Update account notification", "LivFit Notify Update", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //validate
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.etUpdateUserName , RegexTemplate.NOT_EMPTY,R.string.emptyUsernameLogin);
        awesomeValidation.addValidation(this,R.id.etUpdateAge, RegexTemplate.NOT_EMPTY,R.string.enter_age);
        awesomeValidation.addValidation(this,R.id.etUpdateAge, Range.closed(13, 70),R.string.ageValid);
        awesomeValidation.addValidation(this,R.id.etUpdateCurWeight,RegexTemplate.NOT_EMPTY,R.string.enterNowWeight);
        awesomeValidation.addValidation(this,R.id.etUpdateCurrHeight,RegexTemplate.NOT_EMPTY,R.string.enterNowHeight);
        awesomeValidation.addValidation(this,R.id.etUpdateTargetWeight,RegexTemplate.NOT_EMPTY,R.string.enterTargetWeight);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //get Gender
                            String DBgender = snapshot.child("gender").getValue().toString();

                            //call calculation methods
                            BMI = calculateBMI(weight,height);

                            //calculate the calorieGoal
                            calorieGoal = calculateCalorieGoal(DBgender , weight , height,age);

                            //get data from input fields
                            String updateUsername = username.getText().toString();
                            String updateWeight = weight.getText().toString();
                            String updateHeight = height.getText().toString();
                            String updateTargetWeight = targetWeight.getText().toString();
                            Double updateBMI = BMI;
                            Double updateCalGoal = calorieGoal;

                            //hashmap
                            HashMap hashMap = new HashMap();
                            hashMap.put("uname",updateUsername);
                            hashMap.put("nowWeight",updateWeight);
                            hashMap.put("height" ,updateHeight);
                            hashMap.put("targetWeight" , updateTargetWeight);
                            hashMap.put("bmi",updateBMI);
                            hashMap.put("calGoal" , updateCalGoal);

                            //update db
                            databaseReferencec  = FirebaseDatabase.getInstance().getReference().child("User");
                            databaseReferencec.child("user1").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Notification
                                    String message = "Your updated BMI is " + df.format(BMI) + " and updated daily calorie goal is " + df.format(calorieGoal);

                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(updateAccount.this,"Update account notification");
                                    builder.setContentTitle("You have one new notification from LivFit");
                                    builder.setContentText(message);
                                    builder.setSmallIcon(R.drawable.livfit_logo);
                                    builder.setAutoCancel(true);
                                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));


                                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(updateAccount.this);
                                    managerCompat.notify(1234,builder.build());

                                    Toast.makeText(getApplicationContext(),"Account updated successfully!" , Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter details correctly" , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //calculate BMI
    public Double calculateBMI(EditText weight , EditText height){
        Double convertedWeight = Double.parseDouble(weight.getText().toString());
        Double convertedHeight = Double.parseDouble(height.getText().toString());

        return (convertedWeight / (convertedHeight*convertedHeight)) * 10000;
    }

    //calculate calorie goal
    public Double calculateCalorieGoal(String gender , EditText weight , EditText height , EditText age){
        Double cal = 0.0;
        Double BMR;
        Double convertedWeight = Double.parseDouble(weight.getText().toString());
        Double convertedHeight = Double.parseDouble(height.getText().toString());
        Integer convertedAge = Integer.parseInt(age.getText().toString());

        //checking the sex and getting the BMR value
        if(gender == "Male"){
            BMR = 88.362 + (13.397 * convertedWeight) + (4.799 * convertedHeight) - (5.677 * convertedAge);
        }
        else{
            BMR = 447.593 + (9.247 * convertedWeight) + (3.098 * convertedHeight) - (4.330 * convertedAge);
        }

        //calculating calorie amount
        cal = BMR * 1.2;

        return cal;
    }
}