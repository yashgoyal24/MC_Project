package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
    ViewPager vp;
    TabLayout tb;
    float val=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vp=(ViewPager) findViewById(R.id.view_pager_login);
        tb=(TabLayout) findViewById(R.id.tabl);
        tb.addTab(tb.newTab().setText("Login"));
        tb.addTab(tb.newTab().setText("Sign Up"));
        tb.setTabGravity(tb.GRAVITY_FILL);
        final MainActivity2_Ad adap=new MainActivity2_Ad(getSupportFragmentManager(), tb.getTabCount(), this);
        vp.setAdapter(adap);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb));

        tb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {



//                tb.setTranslationY(300);
//                tb.setAlpha(val);
//                tb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });






//                ( new TabLayout.TabLayoutOnPageChangeListener(tb));
        tb.setTranslationY(300);
        tb.setAlpha(val);
        tb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();

    }
}