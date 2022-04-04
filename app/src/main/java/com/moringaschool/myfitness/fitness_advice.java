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
                new listFitness("Under Weight", android.R.drawable.ic_dialog_email),
                new listFitness("Under Weight", android.R.drawable.ic_dialog_info),
                new listFitness("Under Weight", android.R.drawable.ic_delete),
                new listFitness("Under Weight", android.R.drawable.ic_dialog_dialer),
                new listFitness("Under Weight", android.R.drawable.ic_dialog_alert),
                new listFitness("Under Weight", android.R.drawable.ic_dialog_map)
        };
        listFitness[] healthywght = new listFitness[] {
                new listFitness("Healty", android.R.drawable.ic_dialog_email),
                new listFitness("Healty", android.R.drawable.ic_dialog_info),
                new listFitness("Healty", android.R.drawable.ic_delete),
                new listFitness("Healty", android.R.drawable.ic_dialog_dialer),
                new listFitness("Healty", android.R.drawable.ic_dialog_alert),
                new listFitness("Healty", android.R.drawable.ic_dialog_map)
        };
        listFitness[] overwght = new listFitness[] {
                new listFitness("Overwight", android.R.drawable.ic_dialog_email),
                new listFitness("Overwight", android.R.drawable.ic_dialog_info),
                new listFitness("Overwight", android.R.drawable.ic_delete),
                new listFitness("Overwight", android.R.drawable.ic_dialog_dialer),
                new listFitness("Overwight", android.R.drawable.ic_dialog_alert),
                new listFitness("Overwight", android.R.drawable.ic_dialog_map)
        };
        listFitness[] obessedwght = new listFitness[] {
                new listFitness("Obesity", android.R.drawable.ic_dialog_email),
                new listFitness("Obesity", android.R.drawable.ic_dialog_info),
                new listFitness("Obesity", android.R.drawable.ic_delete),
                new listFitness("Obesity", android.R.drawable.ic_dialog_dialer),
                new listFitness("Obesity", android.R.drawable.ic_dialog_alert),
                new listFitness("Obesity", android.R.drawable.ic_dialog_map)
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
