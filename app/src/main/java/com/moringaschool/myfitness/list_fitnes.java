package com.moringaschool.myfitness;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class list_fitnes extends AppCompatActivity {
    public String fullname;
    public float bmi;
    TextView textView ;
    Bundle extras = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting the text view by id
        setContentView(R.layout.activity_list_fitnes);
        //creating new instance of
        Bundle bundle= new Bundle();
        //getting content from the bundle.
        //We check to ensure that bundle is not empty or null to avoid throwing errors
        textView=(TextView)  findViewById(R.id.txtwelcome);
        if (getIntent().getExtras() != null)
        {
            extras = getIntent().getExtras();
             fullname= extras.getString("fname");
             bmi=extras.getFloat("bmi");
             //setting The text


             //Underweight BMI
             if(bmi < 18.0){
                 textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi+" You are under weight");
             }
            //normal BMI
            if(bmi >= 18.0 && bmi < 24.9){
                textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi+" You are Healthy");
            }
            //Overweight  BMI
            if(bmi >= 25.0 && bmi < 29.9){
                textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi+" You are Overwight");
            }
            //Obessity BMI
            if(bmi >= 30.0 ){
                textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi +"You are obbesed");
            }


        }

         }
}