package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener  {

    private EditText updateName, updateAge, updateMobile;
    private RadioGroup genderRadiogroup, bloodGroupRadioGroup;
    private RadioButton genderRadioButton, bloodGroupRadioButton;
    private Button updateButton;
    DatabaseReference databaseReference;

    public String id;

    private String[] cities;
    private String[] ability;
    private AutoCompleteTextView cityEditText;
    private Spinner abilitySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        String name;
        String gender;
        String age;
        String bloodGroup;
        String city;
        String mobile;


        Bundle bundle = getIntent().getExtras();
            name = bundle.getString("name");
            gender = bundle.getString("gender");
            age = bundle.getString("age");
            bloodGroup = bundle.getString("bg");
            city = bundle.getString("city");
            mobile = bundle.getString("mobile");
            id = bundle.getString("id");


        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");


        cities = getResources().getStringArray(R.array.cities);
        cityEditText = findViewById(R.id.cityTextViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        cityEditText.setThreshold(1);
        cityEditText.setAdapter(adapter);


        ability = getResources().getStringArray(R.array.ability);
        abilitySpinner = findViewById(R.id.abilitySpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.sample_view_for_spinner, R.id.spinnerTextViewId, ability);
        abilitySpinner.setAdapter(adapter1);


        updateName = findViewById(R.id.updateNameId);
        updateAge = findViewById(R.id.updateAgeId);
        updateMobile = findViewById(R.id.updateMobileId);


        genderRadiogroup = findViewById(R.id.genderRadioGroupId);
        bloodGroupRadioGroup = findViewById(R.id.bgRadioGroupId);


        updateName.setText(name);
        updateAge.setText(age);
        updateMobile.setText(mobile);


        updateButton = findViewById(R.id.updateButtonId);
        updateButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        updateInformation();

    }

    private void updateInformation() {


        try {

            String updated_name = updateName.getText().toString().trim();
            String updated_age = updateAge.getText().toString().trim();
            String updated_city = cityEditText.getText().toString().trim();
            String updated_mobile = updateMobile.getText().toString().trim();


            if(updated_name.isEmpty())
            {
                updateName.setError("Enter name");
            }

            else if(updated_mobile.isEmpty())
            {
                updateMobile.setError("Enter Mobile number");
            }

            else if(updated_city.isEmpty())
            {
                cityEditText.setError("Enter city name");
            }

            else
            {
                String upadted_ability = abilitySpinner.getSelectedItem().toString().trim();

                int selectedGenderId = genderRadiogroup.getCheckedRadioButtonId();
                genderRadioButton = findViewById(selectedGenderId);
                String updated_gender = genderRadioButton.getText().toString().trim();


                int selectedBloodGroupId = bloodGroupRadioGroup.getCheckedRadioButtonId();
                bloodGroupRadioButton = findViewById(selectedBloodGroupId);
                String updated_bloodGroup = bloodGroupRadioButton.getText().toString().trim();


                String Bg_City_Ability = updated_bloodGroup + "_" + updated_city + "_" + upadted_ability;
                String Name_Mobile = updated_name+"_"+updated_mobile;


                Registration updateDonor =new Registration();

                updateDonor.setName(updated_name);
                updateDonor.setAge(updated_age);
                updateDonor.setGender(updated_gender);
                updateDonor.setBloodGroup(updated_bloodGroup);
                updateDonor.setMobile(updated_mobile);
                updateDonor.setCity(updated_city);
                updateDonor.setAbility(upadted_ability);
                updateDonor.setBg_City_Ability(Bg_City_Ability);
                updateDonor.setName_Mobile(Name_Mobile);
                updateDonor.setId(id);

                databaseReference.child(id).setValue(updateDonor);
                Toast.makeText(UpdateActivity.this,"Donor is successfully updated",Toast.LENGTH_SHORT).show();

                updateName.setText("");
                genderRadioButton.setChecked(false);
                updateAge.setText("");
                bloodGroupRadioButton.setChecked(false);
                updateMobile.setText("");

                Intent intent = new Intent(UpdateActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        }
        catch(Exception e)
        {
            Toast.makeText(this,"Please, enter valid values",Toast.LENGTH_SHORT).show();
        }
    }
}
