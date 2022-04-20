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
                textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi+" You are Overweight");
            }
            //Obessity BMI
            if(bmi >= 30.0 ){
                textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi +"You are obbesed");
            }


        }
        Button btnAdd1 = findViewById(R.id.prevBtn);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
        Button btnAdd2 = findViewById(R.id.nextadvice);
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("fname",fullname);
                bundle.putFloat("bmi",bmi);
                Toast.makeText(getApplicationContext(), fullname+" Your BMI = " + bmi, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), fitness_advice.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        Button btnGeTwitter = findViewById(R.id.btnGetTwitter);
        btnGeTwitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getApplicationContext(), SearchTwitter.class);
                startActivity(t);
            }
        });
         }
}