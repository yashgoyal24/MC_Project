package com.mc2022.template;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class MyAds extends Fragment implements RVAdapter.ItemClickedListener {

    SharedPreferences sharedPreferences;
    StorageReference storageRef;
    private static final String PREF= "sharedPref";
    ArrayList<ModelCar> dataList;
    RecyclerView recyclerView;
    FirebaseDatabase dataBase=FirebaseDatabase.getInstance();
    DatabaseReference root=dataBase.getReference().child("CarDetails");
    RVAdapter rvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferences = getContext().getSharedPreferences(PREF, (getContext()).MODE_PRIVATE);
        View view= inflater.inflate(R.layout.fragment_explore, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String seller_email= sharedPreferences.getString("Email","");
        recyclerView=view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataList=new ArrayList<>();
        rvAdapter=new RVAdapter(dataList,getContext(),this::onItemClickedFunction);
        recyclerView.setAdapter(rvAdapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    if(dataSnapshot.child("seller_email").getValue().equals(seller_email))
                    {
                        ModelCar model=dataSnapshot.getValue(ModelCar.class);
                        dataList.add(model);
                    }
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClickedFunction(String brand, String year, String fuel, String transmission, String distance, String mileage,String seller_email) {

        Intent intent=new Intent(getContext(),ViewCarActivity.class);

        ArrayList<String> data=new ArrayList<String>();

        data.add(brand);
        data.add(year);
        data.add(fuel);
        data.add(transmission);
        data.add(distance);
        data.add(mileage);
        data.add(seller_email);
        intent.putStringArrayListExtra("data",data);
        startActivity(intent);


    }
}