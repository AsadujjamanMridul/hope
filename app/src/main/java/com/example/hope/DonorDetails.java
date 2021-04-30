package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.Queue;

public class DonorDetails extends AppCompatActivity {


    private ListView listView;
    DatabaseReference databaseReference;
    private List<Registration> donorList;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String bloodGroup;
        String city;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        Bundle bundle = getIntent().getExtras();

            bloodGroup = bundle.getString("bg");
            city = bundle.getString("city");


        databaseReference = FirebaseDatabase.getInstance().getReference("Donors");

        donorList = new ArrayList<>();
        customAdapter = new CustomAdapter(DonorDetails.this,donorList);
        listView = findViewById(R.id.listViewId);


        ///To see all the donors:
            //databaseReference.addListenerForSingleValueEvent(valueEventListener);


        Query query = FirebaseDatabase.getInstance().getReference("Donors")
                .orderByChild("bg_City_Ability")
                .equalTo(bloodGroup+"_"+city+"_I can donate now");

        query.addListenerForSingleValueEvent(valueEventListener);

    }

    /*@Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                donorList.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Registration registration = dataSnapshot1.getValue(Registration.class);
                    donorList.add(registration);
                }

                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }*/


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
                }

                listView.setAdapter(customAdapter);
            }

            else
            {
                Toast.makeText(DonorDetails.this, "Sorry, Donor Not Found!", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
