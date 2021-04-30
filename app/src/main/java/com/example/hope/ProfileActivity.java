package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.printservice.PrintService;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private List<Registration> donorList;
    private LoginCustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String name;
        String mobile;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);


        Bundle bundle = getIntent().getExtras();
            name = bundle.getString("name");
            mobile = bundle.getString("mobile");



        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");

        donorList = new ArrayList<>();
        customAdapter = new LoginCustomAdapter(ProfileActivity.this,donorList);
        listView = findViewById(R.id.listViewId);


        Query query = FirebaseDatabase.getInstance().getReference("Donors")
                .orderByChild("name_Mobile")
                .equalTo(name+"_"+mobile);

        query.addListenerForSingleValueEvent(valueEventListener);


    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            donorList.clear();
            if(dataSnapshot.exists())
            {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Registration registration = snapshot.getValue(Registration.class);
                    donorList.add(registration);

                    final String name = registration.getName();
                    final String gender = registration.getGender();
                    final String age = registration.getAge();
                    final String bloodGroup = registration.getBloodGroup();
                    final String mobile = registration.getMobile();
                    final String city = registration.getCity();
                    String Name_Mobile = registration.getName_Mobile();
                    final String id = registration.getId();


                    Toast.makeText(ProfileActivity.this,"Long Press for Updating Data", Toast.LENGTH_SHORT).show();


                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("gender",gender);
                            intent.putExtra("age",age);
                            intent.putExtra("bg",bloodGroup);
                            intent.putExtra("mobile",mobile);
                            intent.putExtra("city",city);
                            intent.putExtra("id",id);
                            startActivity(intent);

                            return false;
                        }
                    });

                }

                listView.setAdapter(customAdapter);



            }

            else
            {
                Toast.makeText(ProfileActivity.this, "No Valid User!", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

            //Toast.makeText(ProfileActivity.this,"Login Unsucessful",Toast.LENGTH_SHORT).show();

        }
    };

}
