package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button homeButton, loginButton;
    private EditText nameEditText, mobileEditText;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");


        nameEditText = findViewById(R.id.loginLayoutNameEditTextId);
        mobileEditText = findViewById(R.id.loginLayoutMobileEditTextId);


        homeButton = findViewById(R.id.loginLayoutHomeButtonId);
        homeButton.setOnClickListener(this);


        loginButton = findViewById(R.id.loginLayoutloginButtonId);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String name = nameEditText.getText().toString().trim();
                    String mobile = mobileEditText.getText().toString().trim();

                    if(name.isEmpty())
                    {
                        nameEditText.setError("Enter donors name");
                    }

                    else if(mobile.isEmpty())
                    {
                        mobileEditText.setError("Enter mobile number");
                    }

                    else if(name.isEmpty() && mobile.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this,"Name and mobile number required",Toast.LENGTH_SHORT).show();
                    }

                    else if(!name.isEmpty() && !mobile.isEmpty())
                    {
                        nameEditText.setText("");
                        mobileEditText.setText("");

                        Intent intentDetails = new Intent(LoginActivity.this, ProfileActivity.class);
                        intentDetails.putExtra("name",name);
                        intentDetails.putExtra("mobile",mobile);
                        startActivity(intentDetails);
                    }

            }

        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.loginLayoutHomeButtonId:
                Toast.makeText(LoginActivity.this, "Home Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

        }
    }

}