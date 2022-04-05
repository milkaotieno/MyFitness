package com.moringaschool.myfitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fitness_advice extends AppCompatActivity {
    public float bmi;
    public String fullname;
    Bundle extras = new Bundle();
    Bundle bundle = new Bundle();
    TextView textView ;
    listfitnessAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitness_advice);
        textView=(TextView)  findViewById(R.id.txtwelcomef);

        listFitness[] underwght = new listFitness[] {
                new listFitness("Buy in foods that you enjoy", android.R.drawable.ic_dialog_email),
                new listFitness("Try to eat something every few hours even if it is only a small snack.", android.R.drawable.ic_dialog_info),
                new listFitness("Aim for 3 small meals and 3 small snacks per day.", android.R.drawable.ic_delete),
                new listFitness("You may find that if you eat breakfast you will eat better for the rest of the day.", android.R.drawable.ic_dialog_dialer),
                new listFitness("Share cooking and eating at mealtimes with family or friends if possible.", android.R.drawable.ic_dialog_alert),
                new listFitness("Keep a store cupboard of easily prepared foods e.g. tinned meat and fish, tinned macaroni or ravioli, UHT or long life milk, dried potato, packet soups, tins or instant puddings.", android.R.drawable.ic_dialog_map),
                new listFitness("Use convenience foods with require little preparation e.g. quiche, pie, burger, sausage roll, bridie, pizza or ready meals.", android.R.drawable.ic_dialog_alert),
                new listFitness("Take milk, milky drinks, ordinary fizzy juices or fruit juices instead of just tea and coffee.", android.R.drawable.ic_dialog_map),
                new listFitness("Try to make food appetising and serve smaller portions. You can always have seconds if you are still hungry.", android.R.drawable.ic_dialog_alert),
                new listFitness("https://www.theyellowpractice.co.uk/keeping-healthy/advice-for-those-who-need-to-gain-weight/", android.R.drawable.ic_dialog_map)

        };
        listFitness[] healthywght = new listFitness[] {
                new listFitness("Exercise often.", android.R.drawable.ic_dialog_email),
                new listFitness("Eat a healthy breakfast daily.", android.R.drawable.ic_dialog_info),
                new listFitness("Stay hydrated.", android.R.drawable.ic_delete),
                new listFitness(" Eat responsibly and mindfully.", android.R.drawable.ic_dialog_dialer),
                new listFitness(" Decrease screen time.", android.R.drawable.ic_dialog_alert),
                new listFitness("Get cookbooks.", android.R.drawable.ic_dialog_map)

        };
        listFitness[] overwght = new listFitness[] {
                new listFitness("Changing your habits", android.R.drawable.ic_dialog_email),
                new listFitness("Practice Weight-management programs", android.R.drawable.ic_dialog_info),
                new listFitness("Healthy eating plan and regular physical activity", android.R.drawable.ic_delete),
                new listFitness("Weight-loss medicines", android.R.drawable.ic_dialog_dialer),
                new listFitness("Plan your meals ahead of time.", android.R.drawable.ic_dialog_alert),
                new listFitness("Special diets", android.R.drawable.ic_dialog_map)

        };
        listFitness[] obessedwght = new listFitness[] {
                new listFitness(" Bariatric surgery", android.R.drawable.ic_dialog_email),
                new listFitness("Weight-loss devices", android.R.drawable.ic_dialog_info),
                new listFitness("Practice Weight-management programs", android.R.drawable.ic_delete),
                new listFitness("Special diets", android.R.drawable.ic_dialog_dialer),
                new listFitness("Changing your habits", android.R.drawable.ic_dialog_alert),
                new listFitness("Healthy eating plan and regular physical activity", android.R.drawable.ic_dialog_map)
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (getIntent().getExtras() != null)
        {
            extras = getIntent().getExtras();
            fullname= extras.getString("fname");
            bmi=extras.getFloat("bmi");
            //setting The text


            //Underweight BMI
            if(bmi < 18.0){
                adapter= new listfitnessAdapter(underwght);
            }
            //normal BMI
            if(bmi >= 18.0 && bmi < 24.9){
                adapter= new listfitnessAdapter(healthywght);
            }
            //Overweight  BMI
            if(bmi >= 25.0 && bmi < 29.9){
                adapter= new listfitnessAdapter(overwght);
            }
            //Obessity BMI
            if(bmi >= 30.0 ){
                adapter= new listfitnessAdapter(obessedwght);
            }


        }
        textView.setText("Welcome  "+fullname +"  Your BMI is "+bmi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Button btnAdd1 = findViewById(R.id.backBtn);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("fname",fullname);
                bundle.putFloat("bmi",bmi);
                Intent i = new Intent(getApplicationContext(), list_fitnes.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

}
