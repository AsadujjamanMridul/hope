package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hope.R.*;
import static com.example.hope.R.id.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button LoginButton;
    private ImageButton  SearchDonorButton, RegisterAsDonorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);



        LoginButton = findViewById(loginButtonId);
        RegisterAsDonorButton = findViewById(R.id.registerAsDonorButtonId);
        SearchDonorButton = findViewById(R.id.searchDonorButtonId);

        LoginButton.setOnClickListener(this);
        RegisterAsDonorButton.setOnClickListener(this);
        SearchDonorButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case loginButtonId:
                Toast.makeText(MainActivity.this, "Login Page", Toast.LENGTH_SHORT).show();
                Intent intentLogin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intentLogin);
                break;

            case R.id.registerAsDonorButtonId:
                Toast.makeText(this, "Registration Page", Toast.LENGTH_SHORT).show();
                Intent intentRegister = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intentRegister);
                break;

            case R.id.searchDonorButtonId:
                Toast.makeText(this, "Search Page", Toast.LENGTH_SHORT).show();
                Intent intentSearch = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intentSearch);
        }
    }
}