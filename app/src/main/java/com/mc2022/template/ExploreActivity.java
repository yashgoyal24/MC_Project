package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity implements RVAdapter.ItemClickedListener {
    ArrayList<ModelCar> dataList;
        RecyclerView recyclerView;
        FirebaseDatabase dataBase=FirebaseDatabase.getInstance();
        DatabaseReference root=dataBase.getReference().child("CarDetails");
        RVAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList=new ArrayList<>();
        rvAdapter=new RVAdapter(dataList,getApplicationContext(),this::onItemClickedFunction);
        recyclerView.setAdapter(rvAdapter);



        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelCar model=dataSnapshot.getValue(ModelCar.class);
                    dataList.add(model);
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public void onItemClickedFunction(String brand,String year,String fuel,String transmission,String distance,String mileage,String seller_email
    ) {

        Intent intent=new Intent(getApplicationContext(),ViewCarActivity.class);

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