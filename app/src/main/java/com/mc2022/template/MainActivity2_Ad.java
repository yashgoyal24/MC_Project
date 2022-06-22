package com.mc2022.template;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivity2_Ad extends FragmentPagerAdapter {

    Context context;
    int tabs;
    public MainActivity2_Ad(FragmentManager fm,  int tabs,Context context){
        super(fm);
        this.context=context;
        this.tabs=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            loginFragment lf=new loginFragment();
            return lf;
        }
        else if(position==1){
            SignUpFragment sf=new SignUpFragment();
            return sf;
        }
        else{
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
