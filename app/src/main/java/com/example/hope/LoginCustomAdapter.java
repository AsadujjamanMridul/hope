package com.example.hope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LoginCustomAdapter extends ArrayAdapter<Registration> {

    private Activity context;
    private List<Registration> donorList;


    public LoginCustomAdapter(Activity context, List<Registration> donorList) {
        super(context, R.layout.sample_layout_for_profile, donorList);
        this.context = context;
        this.donorList = donorList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout_for_profile,null,false);

        Registration registration = donorList.get(position);

        String ability;
        String compare = registration.getAbility();

        if(compare.equals("I can't donate now"))
        {
            ability = "Can't Donate";
        }
        else
        {
            ability = "Can Donate";
        }

        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.genderTextViewId);
        TextView t3 = view.findViewById(R.id.ageTextViewId);
        TextView t4 = view.findViewById(R.id.cityTextViewId);
        TextView t5 = view.findViewById(R.id.bloodGroupTextViewId);
        TextView t6 = view.findViewById(R.id.abilityTextViewId);
        TextView t7 = view.findViewById(R.id.mobileTextViewId);


        t1.setText(registration.getName());
        t2.setText("Gender : "+registration.getGender());
        t3.setText("Age : "+registration.getAge());
        t4.setText("City : "+registration.getCity());
        t5.setText("Blood Group : "+registration.getBloodGroup());
        t6.setText("Donation status : "+ability);
        t7.setText("Mobile : "+registration.getMobile());


        return view;
    }


}
