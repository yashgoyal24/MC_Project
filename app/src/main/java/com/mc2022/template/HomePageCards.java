package com.mc2022.template;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomePageCards extends Fragment {
    CardView cardBuy,cardSell,cardPredict;


    public HomePageCards() {
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

        View view=inflater.inflate(R.layout.fragment_home_page_cards, container, false);
        cardBuy=view.findViewById(R.id.cardbuy);
        cardSell=view.findViewById(R.id.cardSell);
        cardPredict=view.findViewById(R.id.cardPredict);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag=new PredictPriceFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //Fragment frag = fm.findFragmentById(R.id.predictPriceFrag);
                fm.beginTransaction().replace(R.id.layout_frame,frag,"fragPredict").addToBackStack(null).commit();

            }
        });

        cardSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AddCarActivity.class);
                startActivity(intent);
            }
        });

        cardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag=new ExploreFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.layout_frame,frag,"fragExplore").addToBackStack(null).commit();
               // Intent intent=new Intent(getContext(),ExploreActivity.class);
              //  startActivity(intent);
            }
        });



    }
}