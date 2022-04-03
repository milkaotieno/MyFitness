package com.moringaschool.myfitness;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class list_fitnes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_fitnes);
        Button btnAdd = findViewById(R.id.fnextBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(list_fitnes.this, fitness_advice.class);
                startActivity(i);
            }
        });
        Button fprev1Btn = findViewById(R.id.fprevtBtn);
        fprev1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(list_fitnes.this, list_fitnes.class);
                startActivity(i);
            }
        });
    }
}