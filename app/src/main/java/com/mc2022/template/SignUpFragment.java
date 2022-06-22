package com.mc2022.template;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class SignUpFragment extends Fragment {
    private View v;
    private EditText userfname;
    private EditText userlname;
    private EditText useremail;
    private EditText userphone;
    private EditText userpswd1;
    private EditText userpswd2;
    private Button btn1;
    List<user> dl=new ArrayList<>();
    List<user> dlphn=new ArrayList<>();
    userDatabase db2instance;
    user u1;
    StorageReference storageRef;
    DatabaseReference dbRef;

    public SignUpFragment() {
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
        float val=0;
        v= inflater.inflate(R.layout.fragment_sign_up, container, false);
        userfname=(EditText) v.findViewById(R.id.editTextTextPersonName3);
        userlname=(EditText) v.findViewById(R.id.editTextTextPersonName2);
        useremail=(EditText) v.findViewById(R.id.editTextTextPersonName4);
        userphone=(EditText) v.findViewById(R.id.editTextTextPersonName5);
        userpswd1=(EditText) v.findViewById(R.id.editTextTextPassword2);
        userpswd2=(EditText) v.findViewById(R.id.editTextTextPassword3);
        userfname.setTranslationX(800);
       userfname.setAlpha(val);
        userfname.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        userlname.setTranslationX(800);
        userlname.setAlpha(val);
        userlname.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        useremail.setTranslationX(800);
        useremail.setAlpha(val);
        useremail.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        userphone.setTranslationX(800);
        userphone.setAlpha(val);
        userphone.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        userpswd1.setTranslationX(800);
        userpswd1.setAlpha(val);
        userpswd1.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        userpswd2.setTranslationX(800);
        userpswd2.setAlpha(val);
        userpswd2.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1300).start();
        btn1=(Button) v.findViewById(R.id.button5);
       btn1.setTranslationX(800);
        btn1.setAlpha(val);
        btn1.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1200).start();

        storageRef= FirebaseStorage.getInstance().getReference();
        dbRef= FirebaseDatabase.getInstance().getReference("UserDetails");
        db2instance=userDatabase.getInstance(getActivity());
        u1=new user();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uf=String.valueOf(userfname.getText());
                String ul=String.valueOf(userlname.getText());
                String uem=String.valueOf(useremail.getText());
                String uphn=String.valueOf(userphone.getText());
                String upswd1=String.valueOf(userpswd1.getText());
                String upswd2=String.valueOf(userpswd2.getText());
                if(uf.equals(""))
                    userfname.setError("");

                else if(ul.equals(""))
                    userlname.setError("");

                else if(uem.equals(""))
                    useremail.setError("");

                else if(uphn.equals(""))
                    userphone.setError("");
                else if(upswd1.equals(""))
                    userpswd1.setError("");
                else if(upswd2.equals(""))
                    userpswd2.setError("");
                else{
                    dl=db2instance.userDao().getUserDetail(uem);
                    dlphn=db2instance.userDao().getUserPhone(uphn);
                    Log.i("before ","before pwd"+upswd1+" "+upswd2);
                    if(!dl.isEmpty()){
                        Toast.makeText(getContext(),"Email id already exists .Please Login",Toast.LENGTH_SHORT).show();
                    }
                    else if(!dlphn.isEmpty()){
                        Toast.makeText(getContext(),"Phone no already exists .Please Login",Toast.LENGTH_SHORT).show();
                    }

                    else if(!upswd1.equals(upswd2)){
                        Log.i("okay","ok"+upswd1+" "+upswd2);
                        Toast.makeText(getContext(),"Passwords does not match",Toast.LENGTH_SHORT).show();
                        userpswd2.setError("");
                    }

                    else{
                        u1.setFname(uf);
                        u1.setLname(ul);
                        u1.setPhoneno(uphn);
                        u1.setEmail(uem);
                        u1.setPswd(upswd1);
                        db2instance.userDao().insert(u1);
                        String i=dbRef.push().getKey();
                        dbRef.child(i).setValue(u1);
                        Toast.makeText(getContext(),"Successfully Registered" ,Toast.LENGTH_SHORT).show();

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        Fragment frag = fm.findFragmentById(R.id.fragment_container);

                       // Log.i(TAG,"fragment is  null");
                        frag = new loginFragment();
                        fm.beginTransaction().replace(R.id.fragment_container,frag,"frag2").commit();
                     //   Log.i(TAG,"hello2");


                    }
                }


            }
        });


        return v;
    }
}