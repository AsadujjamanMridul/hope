package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText registrationName, registrationAge, registrationMobile;
    private RadioGroup genderRadiogroup, bloodGroupRadioGroup;
    private RadioButton genderRadioButton, bloodGroupRadioButton;
    private Button registrationButton;
    DatabaseReference reff;

    private String[] cities;
    private String[] ability;
    private AutoCompleteTextView cityEditText;
    private Spinner abilitySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        reff = FirebaseDatabase.getInstance().getReference("Donors");


        cities = getResources().getStringArray(R.array.cities);
        cityEditText = findViewById(R.id.cityTextViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        cityEditText.setThreshold(1);
        cityEditText.setAdapter(adapter);


        ability = getResources().getStringArray(R.array.ability);
        abilitySpinner = findViewById(R.id.abilitySpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.sample_view_for_spinner, R.id.spinnerTextViewId, ability);
        abilitySpinner.setAdapter(adapter1);


        registrationName = findViewById(R.id.registrationNameId);
        registrationAge = findViewById(R.id.registrationAgeId);
        registrationMobile = findViewById(R.id.registrationMobileId);


        genderRadiogroup = findViewById(R.id.genderRadioGroupId);
        bloodGroupRadioGroup = findViewById(R.id.bgRadioGroupId);


        registrationButton = findViewById(R.id.registrationButtonId);
        registrationButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        saveInformation();

    }

    private void saveInformation() {

        try {
            String name = registrationName.getText().toString().trim();
            String age = registrationAge.getText().toString().trim();
            String mobile = registrationMobile.getText().toString().trim();
            String city = cityEditText.getText().toString().trim();

            String ability = abilitySpinner.getSelectedItem().toString().trim();


            int selectedGenderId = genderRadiogroup.getCheckedRadioButtonId();
            genderRadioButton = findViewById(selectedGenderId);
            String gender = genderRadioButton.getText().toString().trim();


            int selectedBloodGroupId = bloodGroupRadioGroup.getCheckedRadioButtonId();
            bloodGroupRadioButton = findViewById(selectedBloodGroupId);
            String bloodGroup = bloodGroupRadioButton.getText().toString().trim();


            String BgC = bloodGroup + "_" + city;
            String Name_Mobile = name + "_" + mobile;
            String Name_Mobile_Ability = name + "_" + mobile + "_" + ability;


            Query query = FirebaseDatabase.getInstance().getReference("Donors")
                    .orderByChild("name_Mobile")
                    .equalTo(name + "_" + mobile);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists())
                    {

                        Toast.makeText(getApplicationContext(),"Donor had already been registered!",Toast.LENGTH_SHORT).show();

                    }

                    else
                    {
                        String name = registrationName.getText().toString().trim();
                        String age = registrationAge.getText().toString().trim();
                        String mobile = registrationMobile.getText().toString().trim();

                        String city = cityEditText.getText().toString().trim();


                        if(name.isEmpty())
                        {
                            registrationName.setError("Enter name");
                        }

                        else if(age.isEmpty())
                        {
                            registrationAge.setError("Enter age");
                        }

                        else if(mobile.isEmpty())
                        {
                            registrationMobile.setError("Enter mobile number");
                        }

                        else if(city.isEmpty())
                        {
                            cityEditText.setError("Enter city");
                        }

                        else
                        {

                            String ability = abilitySpinner.getSelectedItem().toString().trim();

                            int selectedGenderId = genderRadiogroup.getCheckedRadioButtonId();
                            genderRadioButton = findViewById(selectedGenderId);
                            String gender = genderRadioButton.getText().toString().trim();


                            int selectedBloodGroupId = bloodGroupRadioGroup.getCheckedRadioButtonId();
                            bloodGroupRadioButton = findViewById(selectedBloodGroupId);
                            String bloodGroup = bloodGroupRadioButton.getText().toString().trim();


                            String BgC = bloodGroup + "_" + city;
                            String Bg_City_Ability = bloodGroup + "_" + city + "_" + ability;
                            String Name_Mobile = name + "_" + mobile;



                            String key = reff.push().getKey();

                            Registration registration = new Registration(name, gender, age, bloodGroup, mobile, city, ability, Bg_City_Ability, Name_Mobile, key);
                            reff.child(key).setValue(registration);
                            Toast.makeText(getApplicationContext(), "Donor is successfully registered", Toast.LENGTH_LONG).show();


                            registrationName.setText("");
                            genderRadioButton.setChecked(false);
                            registrationAge.setText("");
                            bloodGroupRadioButton.setChecked(false);
                            registrationMobile.setText("");

                            Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } catch (Exception e) {
            Toast.makeText(this, "Please Enter Valid Values", Toast.LENGTH_SHORT).show();
        }

    }


    /*ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Registration registration = snapshot.getValue(Registration.class);

                    final String name = registration.getName();
                    final String gender = registration.getGender();
                    final String age = registration.getAge();
                    final String bloodGroup = registration.getBloodGroup();
                    final String mobile = registration.getMobile();
                    final String city = registration.getCity();
                    String BgC = registration.getBgC();
                    String Name_Mobile = registration.getName_Mobile();
                    final String id = registration.getId();


                    Toast.makeText(RegisterActivity.this, "Long Press for Updating Data", Toast.LENGTH_SHORT).show();


                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent intent = new Intent(RegisterActivity.this, UpdateActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("gender", gender);
                            intent.putExtra("age", age);
                            intent.putExtra("bg", bloodGroup);
                            intent.putExtra("mobile", mobile);
                            intent.putExtra("city", city);
                            intent.putExtra("id", id);
                            startActivity(intent);

                            return false;
                        }
                    });

                }

                listView.setAdapter(customAdapter);


            } else {
                Toast.makeText(ProfileActivity.this, "No Valid User!", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };*/
}
