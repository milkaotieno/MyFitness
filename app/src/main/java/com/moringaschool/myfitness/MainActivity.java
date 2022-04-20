package com.moringaschool.myfitness;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.annotations.NonNull;

public class MainActivity extends AppCompatActivity {
    public float  bmi;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private EditText edittextWeight,edittextHeight,edittextFname,edittextlname,edittextAge ;
    private Button firebase;
    private ProgressBar progressbar;
    NavigationView mynav;

    // creating a variable for our Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database Reference for Firebase.
    DatabaseReference databaseReference;
    // creating a variable for our object class
    person personInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mynav =findViewById(R.id.navmenu);
        mynav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                Toast.makeText(getApplicationContext()," clicked"+id,Toast.LENGTH_SHORT);
                if(id == R.id.nav_logout){
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
            }
        });
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // initializing our edittext and button
        edittextWeight = findViewById(R.id.firstNum);
        edittextHeight = findViewById(R.id.secondNum);
        edittextFname = findViewById(R.id.fname);
        edittextlname = findViewById(R.id.lname);
        edittextAge = findViewById(R.id.age);
        progressbar = findViewById(R.id.progressbar1);
        // below line is used to get the instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance("https://myfitness-f92bf-default-rtdb.asia-southeast1.firebasedatabase.app");
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("person");
        // initializing our object class variable.
        personInfo = new person();
        firebase= findViewById(R.id.firebselgn);
        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                if(edittextFname.getText().toString().isEmpty() || edittextlname.getText().toString().isEmpty()||edittextAge.getText().toString().isEmpty()||edittextWeight.getText().toString().isEmpty()||edittextHeight.getText().toString().isEmpty()) {
                    // if the text fields are empty then show the below message.
                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();


                } else {
                    // getting text from our edittext fields.
                    String name = edittextFname.getText().toString();
                    String  lastname= edittextlname.getText().toString();
                    Integer age = Integer.parseInt(edittextAge.getText().toString());
                    Integer weight = Integer.parseInt(edittextWeight.getText().toString());
                    Integer height = Integer.parseInt(edittextHeight.getText().toString());
                    bmi = calculateBMI(weight,height);
                    // call the method to add data to our database.
                    addDatatoFirebase(name,lastname,age,weight,height,bmi);

                }

            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            int id = item.getItemId();
            Toast.makeText(getApplicationContext()," clicked"+id,Toast.LENGTH_SHORT);
            if(id == R.id.nav_logout){
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected Float calculateBMI(Integer wight,Integer height){
        //var to hold bmi
        Float bmi;
        //onvert weight to float
        Float weight=Float.parseFloat(wight.toString());
        //conver height int meters
        Float heightmt = Float.parseFloat(height.toString())/100;
        bmi= weight/(heightmt * heightmt);
        return  bmi;
    }
    private void addDatatoFirebase(String name, String lastname, int  age,int weight,int height ,Float bmi) {
        // below 6 lines of code is used to set data in our object class.
        personInfo.setFirstname(name);
        personInfo.setLastname(lastname);
        personInfo.setAge(age);
        personInfo.setWeight(weight);
        personInfo.setHeight(height);
        personInfo.setBmi(bmi);
        // we are use add value event listener method which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /**inside the method of on Data change we are setting
                 * our object class to our database reference.
                 * data base reference will sends data to firebase. */
                databaseReference.setValue(personInfo);
                // after adding this data we are showing toast message.
                Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);
                Intent i = new Intent(getApplicationContext(), PeopleActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message. */
                Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}