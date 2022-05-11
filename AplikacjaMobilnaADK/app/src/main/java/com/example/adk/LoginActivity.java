package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
    TextView logowanie_naglowek;
    TextInputLayout nazwa_log, haslo_log;
    Button zalogujB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        nazwa_log = findViewById(R.id.wpisz_nazwa_log);
        haslo_log = findViewById(R.id.wpisz_haslo_log);
        zalogujB = findViewById(R.id.zaloguj_button);
        logowanie_naglowek = findViewById(R.id.Text1);

        logowanie_naglowek.setAnimation(topAnim);
        nazwa_log.setAnimation(bottomAnim);
        haslo_log.setAnimation(bottomAnim);
        zalogujB.setAnimation(bottomAnim);
    }
}