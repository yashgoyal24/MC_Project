package com.mc2022.template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    ArrayList<ModelCar> dataList;
    Context context;
    ItemClickedListener clickedListener;
    public RVAdapter(ArrayList<ModelCar> dataList,Context context,ItemClickedListener clickedListener){
        this.context=context;
        this.dataList=dataList;
        this.clickedListener=clickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String brand1,year1,fuel1,transmission1,distance1,mileage1,seller_email1;
        brand1=dataList.get(position).getBrand();
        year1=dataList.get(position).getManYear();
        fuel1=dataList.get(position).getFuelType();
        transmission1=dataList.get(position).getTransmission();
        distance1=dataList.get(position).getKmTravelled();
        mileage1=dataList.get(position).getMileage();
        seller_email1=dataList.get(position).getSeller_email();

        ModelCar model=dataList.get(position);
        holder.brand.setText(model.getBrand());
        holder.year.setText(model.getManYear());
        holder.fuel.setText(model.getFuelType());
        holder.transmission.setText(model.getTransmission());
        holder.distance.setText(model.getKmTravelled());
        holder.mileage.setText(model.getMileage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedListener.onItemClickedFunction(brand1,year1,fuel1,transmission1,distance1,mileage1,seller_email1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
            TextView brand,year,fuel,transmission,distance,mileage;
            ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brand=itemView.findViewById(R.id.brand);
            year=itemView.findViewById(R.id.year);
            fuel=itemView.findViewById(R.id.fuel);
            transmission=itemView.findViewById(R.id.transmission);
            distance=itemView.findViewById(R.id.distance);
            mileage=itemView.findViewById(R.id.mileage);
            imageView=itemView.findViewById(R.id.imageView);

        }

    }
    public interface ItemClickedListener{
        public void onItemClickedFunction(String brand,String year,String fuel,String transmission,String distance,String mileage,String seller_email);
    }
}
