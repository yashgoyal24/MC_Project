package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {
    Button b;
    View v1,v2,v3,v4,v5,v6,v7,v8;
    Animation top_anim;
    Animation image_anim;
    Animation btn_anim;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top_anim= AnimationUtils.loadAnimation(this,R.anim.slide);
        image_anim=AnimationUtils.loadAnimation(this,R.anim.middle_anim);
        btn_anim=AnimationUtils.loadAnimation(this,R.anim.last);
        setContentView(R.layout.activity_main3);
        v1=(View)findViewById(R.id.red);
        v2=(View)findViewById(R.id.white);
        v3=(View)findViewById(R.id.red1);
        v4=(View)findViewById(R.id.white1);
        v5=(View)findViewById(R.id.yellow);
        v6=(View)findViewById(R.id.pink);
        v7=(View)findViewById(R.id.blue);
        v8=(View)findViewById(R.id.white2);
        b=(Button) findViewById(R.id.button_next);
        iv=(ImageView)findViewById(R.id.image_view_start);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });
        v1.setAnimation(top_anim);
        v2.setAnimation(top_anim);
        v3.setAnimation(top_anim);
        v4.setAnimation(top_anim);
        v5.setAnimation(top_anim);
        v6.setAnimation(top_anim);
        v7.setAnimation(top_anim);
        v8.setAnimation(top_anim);
        iv.setAnimation(image_anim);
        b.setAnimation(btn_anim);

    }
}