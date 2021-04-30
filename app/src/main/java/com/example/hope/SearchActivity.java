package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup searchLayoutBloodGroupRadioGroup;
    private RadioButton searchLayoutbloodGroupRadioButton;

    private String[] cities;
    private AutoCompleteTextView cityEditText;

    private Button homeButtonId, searchButton;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cities = getResources().getStringArray(R.array.cities);
        cityEditText = findViewById(R.id.cityTextViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        cityEditText.setThreshold(1);
        cityEditText.setAdapter(adapter);


        homeButtonId = findViewById(R.id.searchLayoutHomeButtonId);
        homeButtonId.setOnClickListener(this);

        searchLayoutBloodGroupRadioGroup = findViewById(R.id.searchLayoutBloodGroupRadioGroupId);


        searchButton = findViewById(R.id.searchLayoutSearchButtonId);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    int selectedBloodGroupId = searchLayoutBloodGroupRadioGroup.getCheckedRadioButtonId();
                    searchLayoutbloodGroupRadioButton = findViewById(selectedBloodGroupId);
                    String bloodGroup = searchLayoutbloodGroupRadioButton.getText().toString().trim();

                    String city = cityEditText.getText().toString().trim();
                    if(city.isEmpty())
                    {
                        cityEditText.setError("Enter city");
                    }

                    else
                    {
                        Intent intentDetails = new Intent(SearchActivity.this, DonorDetails.class);
                        intentDetails.putExtra("bg",bloodGroup);
                        intentDetails.putExtra("city",city);
                        startActivity(intentDetails);
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(SearchActivity.this,"Enter valid values",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.searchLayoutHomeButtonId:
                Toast.makeText(SearchActivity.this, "Home Page", Toast.LENGTH_SHORT).show();
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                break;

        }
    }
}
