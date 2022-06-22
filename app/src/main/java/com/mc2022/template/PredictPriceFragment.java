package com.mc2022.template;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PredictPriceFragment extends Fragment {

    private Button btnPredict;

    EditText model1,year1,transmission1,mileage1,fuelType1,mpg1,engineSize1,type1;

    TextView tvPrice;
    String model,year,transmission,mileage,fuelType,mpg,engineSize,type;

    public PredictPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_predict_price, container, false);
        btnPredict=view.findViewById(R.id.btnPredict);
        model1=(EditText) view.findViewById(R.id.model);
        year1=(EditText)view.findViewById(R.id.year);
        transmission1=(EditText)view.findViewById(R.id.transmission);
        mileage1=(EditText)view.findViewById(R.id.mileage);
        fuelType1=(EditText)view.findViewById(R.id.fuelType);
        mpg1=(EditText)view.findViewById(R.id.mpg);
        engineSize1=(EditText)view.findViewById(R.id.engineSize);
        type1=(EditText)view.findViewById(R.id.type);
        tvPrice=(TextView)view.findViewById(R.id.tvPrice);
        return view;
    }
     @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetch from cloud

         String url="https://mc-carprice-predictor.herokuapp.com/predict";

         btnPredict.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 //Get values from the edit text

                 model=model1.getText().toString();
                 year=year1.getText().toString();
                 transmission=transmission1.getText().toString();
                 mileage=mileage1.getText().toString();
                 fuelType=fuelType1.getText().toString();
                 mpg=mpg1.getText().toString();
                 engineSize=engineSize1.getText().toString();
                 type=type1.getText().toString();

                 //Run Volley Code

                 StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         try {
                             JSONObject jsonObject=new JSONObject(response);
                             Log.e("test","After creating json object");
                             String predicted_price=jsonObject.getString("price");

                             //Set the Text View for showing final predicted price

                             tvPrice.setText("Rs. "+predicted_price);

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }

                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.e("test","Inside ErrorResponse");
                     }
                 }){

                     @Override

                     protected Map<String,String> getParams() {


                         Map<String, String> parameters = new HashMap<String, String>();
                         parameters.put("model",model);
                         parameters.put("year",year);
                         parameters.put("transmission",transmission);
                         parameters.put("mileage",mileage);
                         parameters.put("fuelType",fuelType);
                         parameters.put("mpg",mpg);
                         parameters.put("engineSize",engineSize);
                         parameters.put("type",type);
                         Log.e("test","Inside ");

                         return parameters;

                     }



                 };

                 RequestQueue queue= Volley.newRequestQueue(getContext());
                 queue.add(stringRequest);



             }
         });



    }

}