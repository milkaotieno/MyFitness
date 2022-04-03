package com.moringaschool.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText firstNum = findViewById(R.id.firstNum);
        final EditText secNum = findViewById(R.id.secondNum);
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
                    float bmi= weight(height_mt * height_mt);
                    Toast.makeText(getApplicationContext(), "BMI = " + bmi, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnAdd1 = findViewById(R.id.NextBtn);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), list_fitnes.class);
                startActivity(i);
            }
        });
    }
}