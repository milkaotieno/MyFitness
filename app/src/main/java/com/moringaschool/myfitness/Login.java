package com.moringaschool.myfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private Integer isregisted = 0;
    private FirebaseAuth mAuth;
    //undle to get regitered flag
    Bundle extras = new Bundle();
    // image for login
    ImageView loginimage ;
    // Define the pic id
    private static final int pic_id = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        // initialising all views through id defined above
        emailTextView = findViewById(R.id.lemail);
        passwordTextView = findViewById(R.id.lpassword);
        Btn = findViewById(R.id.login);
        // get login imagevw
         loginimage =findViewById(R.id.logininage);

         //set login imageview onclick listner
           loginimage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   // Create the camera_intent ACTION_IMAGE_CAPTURE
                   // it will open the camera for capture the image
                   Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   // Start the activity with camera_intent,
                   // and request pic id
                   startActivityForResult(camera_intent, pic_id);
               }
           });

        progressbar = findViewById(R.id.progressBar);
        // setting is registered  flag fron extras
        if (getIntent().getExtras() != null)
        {
            extras = getIntent().getExtras();
            isregisted= extras.getInt("registered");

        }
        /* setting the button for login and setting its on click listener   */
        Button reg= findViewById(R.id.btnreg);
        if(isregisted==1){
            reg.setVisibility(View.GONE);
        }
        else{
            reg.setVisibility(View.VISIBLE);
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), Registration.class);
                    startActivity(i);
                }
            });
        }

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent= new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            loginimage.setImageBitmap(photo);
        }
    }
}