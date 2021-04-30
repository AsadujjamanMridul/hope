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

public class CustomAdapter extends ArrayAdapter<Registration> {

    private Activity context;
    private List<Registration> donorList;


    public CustomAdapter(Activity context, List<Registration> donorList) {
        super(context, R.layout.sample_layout, donorList);
        this.context = context;
        this.donorList = donorList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,false);

        Registration registration = donorList.get(position);


        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.genderTextViewId);
        TextView t3 = view.findViewById(R.id.ageTextViewId);
        TextView t4 = view.findViewById(R.id.bloodGroupTextViewId);
        TextView t5 = view.findViewById(R.id.mobileTextViewId);

        Button btn = view.findViewById(R.id.callNowButtonId);


        t1.setText("Name : "+registration.getName());
        t2.setText("Gender : "+registration.getGender());
        t3.setText("Age : "+registration.getAge());
        t4.setText("Blood Group : "+registration.getBloodGroup());
        t5.setText("Mobile : "+registration.getMobile());

        final String mobile = registration.getMobile();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+mobile));
                context.startActivity(intent);
            }
        });

        return view;
    }


}
