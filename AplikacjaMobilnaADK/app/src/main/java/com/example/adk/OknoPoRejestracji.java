package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class OknoPoRejestracji extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
    TextView naglowek1;
    Button powrotDoLogowania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_po_rejestracji);

        //Hooks
        naglowek1 = findViewById(R.id.komunikat_po_rejestracji);
        powrotDoLogowania = findViewById(R.id.powrot_do_logowania_button);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        naglowek1.setAnimation(topAnim);
        powrotDoLogowania.setAnimation(bottomAnim);

        powrotDoLogowania.setOnClickListener(view -> {
            Intent intent = new Intent(OknoPoRejestracji.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}