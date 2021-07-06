package com.example.LivFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.LivFit.Model.User;
import com.google.common.collect.Range;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_fitness_data extends AppCompatActivity {

    private EditText age, weight , height , targetWeight;
    private Button createBtn;
    private RadioGroup genderGrp ;
    private AwesomeValidation awesomeValidation;
    private Double BMI , calorieGoal;
    private String gender , weightType;
    private DatabaseReference databaseReference;
    private User user;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fitness_data);

        age = findViewById(R.id.etEnterAge);
        weight = findViewById(R.id.etNowWeight3);
        height = findViewById(R.id.etNowHeight);
        targetWeight = findViewById(R.id.etTargetWeight);
        createBtn = findViewById(R.id.btnCreateAccount);
        genderGrp = findViewById(R.id.radioGrpGender);

        //checking if the user is already logged in
        firebaseAuth = FirebaseAuth.getInstance();

        //crating the table
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //validation patterns
        awesomeValidation.addValidation(this,R.id.etEnterAge, RegexTemplate.NOT_EMPTY,R.string.enter_age);
        awesomeValidation.addValidation(this,R.id.etEnterAge, Range.closed(13, 70),R.string.ageValid);
        awesomeValidation.addValidation(this,R.id.etNowWeight3,RegexTemplate.NOT_EMPTY,R.string.enterNowWeight);
        awesomeValidation.addValidation(this,R.id.etNowHeight,RegexTemplate.NOT_EMPTY,R.string.enterNowHeight);
        awesomeValidation.addValidation(this,R.id.etTargetWeight,RegexTemplate.NOT_EMPTY,R.string.enterTargetWeight);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking validations
                if(awesomeValidation.validate()){
                    //checking radio button
                    int checkedID = genderGrp.getCheckedRadioButtonId();
                    if(checkedID == -1){
                        Toast.makeText(getApplicationContext(),"Please select the gender",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //get the BMI
                        BMI = calculateBMI(weight,height);

                        //get weight type
                        weightType = getWeightType(BMI);

                        //to get the gender type
                        findGender(checkedID);

                        //calculate the calorieGoal
                        calorieGoal = calculateCalorieGoal(gender , weight , height,age);

                        //getting data from the previous intent
                        String firstName = getIntent().getStringExtra("KeyregOneFname");
                        String lastName = getIntent().getStringExtra("KeyregOneLname");
                        String username = getIntent().getStringExtra("KeyregOneUname");
                        String password = getIntent().getStringExtra("KeyregOnepword");
                        String Email = getIntent().getStringExtra("KeyregOneemail");

                        Integer DBage = Integer.parseInt(age.getText().toString().trim());
                        Double DBWeight = Double.parseDouble(weight.getText().toString().trim());
                        Double DBHeight = Double.parseDouble(height.getText().toString().trim());
                        Double DBTargetWeight = Double.parseDouble(targetWeight.getText().toString().trim());
                        String DBGender = gender.trim();
                        Double DBBmi = BMI;
                        Double DBCalGoal = calorieGoal;
                        Double DBCalConsumption = 0.0;
                        Double DBCalBurned = 0.0;
                        Integer DBWaterCount = 0;

                        user = new User();

                        user.setFname(firstName);
                        user.setLname(lastName);
                        user.setUname(username);
                        user.setPword(password);
                        user.setEmail(Email);
                        user.setAge(DBage);
                        user.setNowWeight(DBWeight);
                        user.setHeight(DBHeight);
                        user.setTargetWeight(DBTargetWeight);
                        user.setBMI(DBBmi);
                        user.setGender(DBGender);
                        user.setCalGoal(DBCalGoal);
                        user.setCalConsumption(DBCalConsumption);
                        user.setCalBurned(DBCalBurned);
                        user.setWaterCount(DBWaterCount);

                        //register user
                        firebaseAuth.createUserWithEmailAndPassword(Email,password);

                        //inserting data to the table
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("User/user1");
                        databaseReference.setValue(user);

                        //fixating BMI , Calorie goal and weight type to display in the next page
                        String sendBmi = String.valueOf(BMI);
                        String sendWeightType = weightType;
                        String sendCalGoal = String.valueOf(calorieGoal);

                        Intent nextIntent = new Intent(Register_fitness_data.this,DisplayBMI.class);
                        //binding with the intent
                        nextIntent.putExtra("KeyBMI" , sendBmi);
                        nextIntent.putExtra("KeyWeightType" , sendWeightType);
                        nextIntent.putExtra("KeyCalGoal" , sendCalGoal);
                        startActivity(nextIntent);

                        //show message
                        Toast.makeText(getApplicationContext(),"Account created successfully",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter your information correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    //get gender
    public void findGender(int checkedID) {
        //checking if the case is male or female
        switch (checkedID){
            case R.id.Male:
                gender = "Male";
                break;
            case R.id.Female:
                gender = "Female";
                break;
        }
    }

    //calculate BMI
    public Double calculateBMI(EditText weight , EditText height){
        Double convertedWeight = Double.parseDouble(weight.getText().toString());
        Double convertedHeight = Double.parseDouble(height.getText().toString());

        return (convertedWeight / (convertedHeight*convertedHeight)) * 10000;
    }

    //calcualte weight type
    public String getWeightType(Double BMIPass){

        if(BMIPass < 18.5){
            return "Underweight";
        }
        else if (BMIPass >= 18.5 && BMIPass <= 24.9){
            return "Normal";
        }
        else if(BMIPass >= 25.0 && BMIPass <= 29.9){
            return  "Overweight";
        }
        else if(BMIPass >= 30.0 && BMIPass <= 34.9){
            return "Obese";
        }
        else{
            return "Extremely Obese";
        }

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