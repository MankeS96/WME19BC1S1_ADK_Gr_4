package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class RegisterActivity3 extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
    TextView rejestracja33;
    CountryCodePicker countryCodePicker;
    TextInputLayout podajNumer;
    Button zakonczRejestracje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        //Hooks
        rejestracja33 = findViewById(R.id.naglowek_rej3);
        countryCodePicker = findViewById(R.id.ccp);
        podajNumer = findViewById(R.id.wpisz_numer_tel);
        zakonczRejestracje = findViewById(R.id.zarejestruj_button);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        rejestracja33.setAnimation(topAnim);
        countryCodePicker.setAnimation(bottomAnim);
        podajNumer.setAnimation(bottomAnim);
        zakonczRejestracje.setAnimation(bottomAnim);

        zakonczRejestracje.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity3.this, OknoPoRejestracji.class);
            startActivity(intent);
        });
    }
}