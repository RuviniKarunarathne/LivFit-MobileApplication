package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class settings extends AppCompatActivity {

    private TextView editProfile , deleteAccount , logOut , aboutUs , faq;
    private Switch pushNotify , darkTheme;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editProfile = findViewById(R.id.tvLinkEditProfile);
        deleteAccount = findViewById(R.id.tvLinkDeleteAccount);
        logOut = findViewById(R.id.tvLogOut);
        aboutUs = findViewById(R.id.tvAboutUs);
        faq = findViewById(R.id.tvFAQ);
        pushNotify = findViewById(R.id.switchPushNotification);
        darkTheme = findViewById(R.id.switchTheme);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(settings.this,updateAccount.class);
                startActivity(updateIntent);
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete data
                databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

                databaseReference.child("user1").removeValue();

                Toast.makeText(getApplicationContext(),"Account deleted successfully" , Toast.LENGTH_SHORT).show();

                Intent backLog = new Intent(settings.this,UserInterfaceOne.class);
                startActivity(backLog);
                finish();

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logout
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abtUsIntent = new Intent(settings.this,aboutUs.class);
                startActivity(abtUsIntent);
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faqIntent = new Intent(settings.this,faq.class);
                startActivity(faqIntent);
            }
        });
    }
}