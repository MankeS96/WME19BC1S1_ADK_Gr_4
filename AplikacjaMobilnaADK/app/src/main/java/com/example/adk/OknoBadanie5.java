package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class OknoBadanie5 extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    TextView komunikatBadanie1;
    Button rozpocznijBadanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_badanie5);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        komunikatBadanie1 = findViewById(R.id.komunikatBadanie1_5);
        rozpocznijBadanie = findViewById(R.id.rozpocznijBadanieButton_5);

        komunikatBadanie1.setAnimation(topAnim);
        rozpocznijBadanie.setAnimation(bottomAnim);

    }
    public void callOknoUzytkownika(View view){

        Intent intent = new Intent(OknoBadanie5.this, OknoUzytkownika.class);
        startActivity(intent);
    }
}