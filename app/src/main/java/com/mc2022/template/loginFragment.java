package com.mc2022.template;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class loginFragment extends Fragment {

    private static final String PREF= "sharedPref";
    private EditText UserEmail;
    private EditText UserPassword;
    private View v;
    SharedPreferences sharedpreferences;


    ArrayList<user> dataList;
    private Button ButtonLogin;
    private Button ButtonSignup;
    userDatabase dbinstance;
    public final String TAG="LOGIN FRAGMENT";
    List<user> dl=new ArrayList<>();
    FirebaseDatabase dataBase=FirebaseDatabase.getInstance();
    DatabaseReference root=dataBase.getReference().child("UserDetails");
    user u;

    public loginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        float val=0;
        v=inflater.inflate(R.layout.fragment_login, container, false);

        UserEmail=(EditText) v.findViewById(R.id.name);

        UserPassword=(EditText) v.findViewById(R.id.pswd_l);
        ButtonLogin=(Button)v.findViewById(R.id.button) ;
        ButtonSignup=(Button)v.findViewById(R.id.button_1);
        UserEmail.setTranslationX(800);
        UserEmail.setAlpha(val);
        UserEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1300).start();
        UserPassword.setTranslationX(800);
        UserPassword.setAlpha(val);
        UserPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1300).start();
        ButtonLogin.setTranslationX(800);
        ButtonLogin.setAlpha(val);
        ButtonLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1300).start();
        ButtonSignup.setTranslationX(800);
        ButtonSignup.setAlpha(val);
        ButtonSignup.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1300).start();
        dataList=new ArrayList<>();
        sharedpreferences = getActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String userEmail= String.valueOf(UserEmail.getText());
                String userPassword=String.valueOf(UserPassword.getText());

                if(userEmail.equals("") )
                {
                    UserEmail.setError("Please enter user email!!");
                }
                else if(userPassword.equals(""))
                {
                    UserPassword.setError("Please enter Password!!");
                }
                else
                {
                    root.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.i("here","ok");
                            String uemail="";
                            String upass="";
                            int flag=0;
                            if(dataSnapshot.getChildren()!=null) {
                                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                    Log.i("i am ", "here");
                                    user u1 = snap.getValue(user.class);
                                    Log.i("data", u1.getEmail());
                                    if(u1.getEmail().equals(userEmail)){
                                        uemail=u1.getEmail();
                                        Log.i("found","found");
                                        upass=u1.getPswd();
                                        flag=1;
                                        break;
                                    }
                                }
                                if(flag==1){
                                    if(upass.equals(userPassword)){
                                        Toast.makeText(getContext(),"Succesfully logged in",Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor editEmail = sharedpreferences.edit();
                                        editEmail.putString("Email",userEmail);
                                        editEmail.commit();

                                        Intent intent=new Intent(getContext(),NavBarActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getContext(),"Incorrect password",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getContext(),"Please SignUp first1",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getContext(),"Please SignUp first2",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });
        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment frag = fm.findFragmentById(R.id.fragment_container);

                Log.i(TAG,"fragment is  null");
                frag = new SignUpFragment();
                fm.beginTransaction().replace(R.id.fragment_container,frag,"frag2").commit();
                Log.i(TAG,"hello2");
            }
        });


        return v;
    }
}