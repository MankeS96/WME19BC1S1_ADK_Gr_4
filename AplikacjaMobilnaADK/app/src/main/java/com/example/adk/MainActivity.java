package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
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

        //Hooks
        image = findViewById(R.id.Image1);
        logo = findViewById(R.id.Text1);
        slogan = findViewById(R.id.Text2);
        buttonLogowanie = findViewById(R.id.Button1);
        buttonRejestracja = findViewById(R.id.Button2);

        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        buttonRejestracja.setAnimation(bottomAnim);
        buttonLogowanie.setAnimation(bottomAnim);



    }
}