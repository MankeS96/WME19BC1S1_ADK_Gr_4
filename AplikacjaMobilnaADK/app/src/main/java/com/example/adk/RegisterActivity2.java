package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegisterActivity2 extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    TextView rejestracja_naglowek;
    TextView naglowek1, naglowek2;
    RadioGroup radioGroup;
    RadioButton selectedPlec;
    DatePicker datePicker;
    Button przejdzDalej2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //Hooks
        rejestracja_naglowek =findViewById(R.id.Text1rej);
        naglowek1 = findViewById(R.id.wybierz_plec_text);
        naglowek2 = findViewById(R.id.podaj_date_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.date_picker);
        //selectedPlec = findViewById(R.id.)
        przejdzDalej2 = findViewById(R.id.zarejestruj_button);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        rejestracja_naglowek.setAnimation(topAnim);
        naglowek1.setAnimation(bottomAnim);
        naglowek2.setAnimation(bottomAnim);
        radioGroup.setAnimation(bottomAnim);
        datePicker.setAnimation(bottomAnim);
        przejdzDalej2.setAnimation(bottomAnim);

        przejdzDalej2.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity2.this, RegisterActivity3.class);
            startActivity(intent);
        });
    }
}
