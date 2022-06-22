package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.mc2022.template.userDao;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "bla";
    List<user> dl=new ArrayList<>();
    userDatabase db1;
    user u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_container);

        Log.i(TAG,"fragment is  null");
        frag = new loginFragment();
        fm.beginTransaction().replace(R.id.fragment_container,frag,"frag1").commit();
        Log.i(TAG,"hello");


//        db1=userDatabase.getInstance(getApplicationContext());
//        dl=db1.userDao().getAll();
//
//        Log.i("before get all-","abc");
//        u=new user();
//        u.setId(1);
//        u.setFname("Seema");
//        u.setLname("Anjum");
//        u.setPhoneno("12345");
//        u.setEmail("abc.gamil.com");
//        u.setPswd("bla");
//
//        db1.userDao().insert(u);
//
//        Log.i("after get all-", String.valueOf(dl.get(0).getFname()));

    }
    public void fun(){


    }
}