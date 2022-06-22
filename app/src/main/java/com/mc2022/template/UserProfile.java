package com.mc2022.template;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends Fragment {

    FirebaseDatabase dataBase=FirebaseDatabase.getInstance();
    DatabaseReference root=dataBase.getReference().child("UserDetails");
String fname,lname,email,phone;
TextView tvWelcome,tvEmail,tvName,tvPhone;
    SharedPreferences sharedPreferences;
    private static final String PREF= "sharedPref";
    public UserProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_profile, container, false);
        sharedPreferences = getContext().getSharedPreferences(PREF, (getContext()).MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvWelcome=view.findViewById(R.id.tvWelcome);
        tvName=view.findViewById(R.id.tvName);
        tvEmail=view.findViewById(R.id.tvEmail);
        tvPhone=view.findViewById(R.id.tvPhone);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    //insert current user email id here
                    String seller_email= sharedPreferences.getString("Email","");
                    if(dataSnapshot.child("email").getValue().equals(seller_email)){
                        fname=dataSnapshot.child("fname").getValue(String.class);
                        lname=dataSnapshot.child("lname").getValue(String.class);
                        phone=dataSnapshot.child("phoneno").getValue(String.class);
                        email=dataSnapshot.child("email").getValue(String.class);
                    }
                  //  ModelCar model=dataSnapshot.getValue(ModelCar.class);

                }

                    tvWelcome.setText("Welcome, "+fname);
                tvName.setText(fname+lname);
                tvPhone.setText(phone);
                tvEmail.setText(email);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}