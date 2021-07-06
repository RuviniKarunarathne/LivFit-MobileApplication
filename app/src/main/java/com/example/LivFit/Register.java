package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private EditText fname , lname , uname , pword , email;
    private Button continuebtn;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assining to IDS
        fname = findViewById(R.id.ptEnterFName);
        lname = findViewById(R.id.ptEnterLName);
        uname = findViewById(R.id.ptEnterUserName);
        pword = findViewById(R.id.pwEnterPassword);
        email = findViewById(R.id.emEnterEmail);
        continuebtn = findViewById(R.id.btContinue);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //adding validation for inputs
        awesomeValidation.addValidation(this,R.id.ptEnterFName, RegexTemplate.NOT_EMPTY,R.string.enter_name);
        awesomeValidation.addValidation(this, R.id.ptEnterFName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.FnameError);
        awesomeValidation.addValidation(this,R.id.ptEnterLName, RegexTemplate.NOT_EMPTY,R.string.enter_lname);
        awesomeValidation.addValidation(this, R.id.ptEnterLName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.LnameError);
        awesomeValidation.addValidation(this,R.id.ptEnterUserName, RegexTemplate.NOT_EMPTY,R.string.enter_Uname);
        awesomeValidation.addValidation(this,R.id.pwEnterPassword, ".{6,}",R.string.valid_passwaord);
        awesomeValidation.addValidation(this,R.id.emEnterEmail, Patterns.EMAIL_ADDRESS,R.string.valid_mail);

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking validation
                if(awesomeValidation.validate()){
                    String firstName = fname.getText().toString();
                    String lastname = lname.getText().toString();
                    String username = uname.getText().toString();
                    String password = pword.getText().toString();
                    String Email = email.getText().toString();

                    Intent conintent = new Intent(Register.this,Register_fitness_data.class);
                    conintent.putExtra("KeyregOneFname" , firstName);
                    conintent.putExtra("KeyregOneLname" , lastname);
                    conintent.putExtra("KeyregOneUname" , username);
                    conintent.putExtra("KeyregOnepword" , password);
                    conintent.putExtra("KeyregOneemail" , Email);

                    startActivity(conintent);
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter information correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}