package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //Fragment

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = new HomePageCards();
        fm.beginTransaction().replace(R.id.homePageLayout,frag,"fragHome").commit();

    }
}