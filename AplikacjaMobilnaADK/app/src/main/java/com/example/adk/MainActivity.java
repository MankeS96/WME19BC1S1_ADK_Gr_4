package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adk.R;

public class MainActivity extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim, scaleUp, scaleDown;
    ImageView image;
    TextView logo, slogan;
    Button buttonLogowanie, buttonRejestracja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        //Hooks
        image = findViewById(R.id.Image1);
        logo = findViewById(R.id.Text1);
        slogan = findViewById(R.id.Text2);
        buttonLogowanie = findViewById(R.id.logowanie);
        buttonRejestracja = findViewById(R.id.rejestracja);

        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        buttonRejestracja.setAnimation(bottomAnim);
        buttonLogowanie.setAnimation(bottomAnim);
    }
    //        buttonLogowanie.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
//                    buttonLogowanie.startAnimation(scaleUp);
//                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    buttonLogowanie.startAnimation(scaleDown);
//                }
//                return true;
//            }
//        });
//    }

    @SuppressLint("NonConstantResourceId")
    public void click(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.logowanie:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rejestracja:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}