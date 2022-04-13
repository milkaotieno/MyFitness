package com.moringaschool.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 public float  bmi;
 public String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText firstNum = findViewById(R.id.firstNum);
        final EditText secNum = findViewById(R.id.secondNum);
        final EditText fulname = findViewById(R.id.fname);
        Button firebase= findViewById(R.id.firebselgn);
        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        Button reg= findViewById(R.id.btnreg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);
            }
        });
        Button btnAdd = findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstNum.getText().toString().isEmpty() || secNum.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    float weight = Float.parseFloat(firstNum.getText().toString());
                    //coverting centimeter to meter
                    float height_mt = Float.parseFloat(secNum.getText().toString())/100;
                    //calculating BMI
                     bmi= weight/(height_mt * height_mt);
                     //Get fullname
                    fullname = fulname.getText().toString();
                    Toast.makeText(getApplicationContext(), fullname+" Your BMI = " + bmi, Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    //passing fullname and bmi to the list fitness
                    bundle.putString("fname",fullname);
                    bundle.putFloat("bmi",bmi);
                    Intent i = new Intent(getApplicationContext(), list_fitnes.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });

    }
}