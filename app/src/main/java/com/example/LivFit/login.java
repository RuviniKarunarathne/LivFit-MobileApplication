package com.example.LivFit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    private EditText username , password;
    private TextView forgetPW;
    private Button logBtn;
    private AwesomeValidation awesomeValidation;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etEnterUsername);
        password = findViewById(R.id.etPass);
        forgetPW = findViewById(R.id.tvForgotPW);
        logBtn = findViewById(R.id.btnLoginIn);

        //checking the SDK version to create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Forget password notification", "LivFit Notify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //validation patterns
        awesomeValidation.addValidation(this,R.id.etEnterUsername, RegexTemplate.NOT_EMPTY,R.string.emptyUsernameLogin);
        awesomeValidation.addValidation(this,R.id.etPass, RegexTemplate.NOT_EMPTY,R.string.emptyPassword);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking form validation
                if(awesomeValidation.validate()){
                    //getting database instance
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child("user1");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String DBpassword = snapshot.child("pword").getValue().toString().trim();

                            String checkingPW = password.getText().toString().trim();

                            //checking if the password matches
                            if(DBpassword.compareTo(checkingPW) == 0){
                                //if passwords matches getting daily data
                                String calGoal = snapshot.child("calGoal").getValue().toString();
                                String calConsumption = snapshot.child("calConsumption").getValue().toString();
                                String calBurned = snapshot.child("calBurned").getValue().toString();
                                String watercount = snapshot.child("waterCount").getValue().toString();

                                Toast.makeText(getApplicationContext(),"Logged In" , Toast.LENGTH_SHORT).show();

                                //send data to the dashboard through intent
                                Intent logDashIntent = new Intent(login.this,Dashboard.class);
                                logDashIntent.putExtra("KeyDashCalGoal" , calGoal);
                                logDashIntent.putExtra("KeyDashCalConsumption" , calConsumption);
                                logDashIntent.putExtra("KeyDashCalBurned" , calBurned);
                                logDashIntent.putExtra("KeyDashWaterCount" , watercount);

                                startActivity(logDashIntent);

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter login credential correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notify user
                String message = "We sent you a password reset link to your mail";

                NotificationCompat.Builder builder = new NotificationCompat.Builder(login.this,"Forget password notification");
                builder.setContentTitle("You have one new notification from LivFit");
                builder.setContentText(message);
                builder.setSmallIcon(R.drawable.livfit_logo);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(login.this);
                managerCompat.notify(123,builder.build());

            }
        });
    }
}