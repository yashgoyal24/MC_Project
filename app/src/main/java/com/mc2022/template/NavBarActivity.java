package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
//import meow.bottomnavigation.MeowBottomNavigation;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class NavBarActivity extends AppCompatActivity {
    MeowBottomNavigation navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        navbar=findViewById(R.id.id_navbar);
        navbar.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        navbar.add(new MeowBottomNavigation.Model(2,R.drawable.ic_explore));
        navbar.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_directions_car_24));
        navbar.add(new MeowBottomNavigation.Model(4,R.drawable.ic_myprofile));

        //On click

navbar.setOnShowListener(new MeowBottomNavigation.ShowListener() {
    @Override
    public void onShowItem(MeowBottomNavigation.Model item) {
        Fragment frag=null;
        switch (item.getId()){
            case 1:

                frag=new HomePageCards();
                Toast.makeText(NavBarActivity.this, "Clicked Home", Toast.LENGTH_SHORT).show();
                break;



            case 2:
                frag=new ExploreFragment();
                Toast.makeText(NavBarActivity.this, "Clicked Explore", Toast.LENGTH_SHORT).show();
                break;

            case 3:


                Toast.makeText(NavBarActivity.this, "Clicked MyAds", Toast.LENGTH_SHORT).show();
                frag=new MyAds();
                break;

            case 4:
                Toast.makeText(NavBarActivity.this, "Clicked MyProfile", Toast.LENGTH_SHORT).show();
                frag=new UserProfile();
                break;

        }

        func(frag);
    }
});

navbar.show(1,true);



navbar.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
    @Override
    public void onClickItem(MeowBottomNavigation.Model item) {
        Toast.makeText(getApplicationContext(),"You Clicked - "+item.getId(),Toast.LENGTH_SHORT).show();
    }
});
    }

    void func(Fragment frag){

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame,frag).commit();

    }
}