package com.example.adk;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity2 extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    TextView rejestracja22;
    TextView naglowek1, naglowek2;
    RadioGroup radioGroup;
    //RadioButton selectedPlec;
    DatePicker datePicker;
    Button przejdzDalej2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register2);

        //Hooks
        rejestracja22 =findViewById(R.id.naglowek_rej2);
        naglowek1 = findViewById(R.id.wybierz_plec_text);
        naglowek2 = findViewById(R.id.podaj_date_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.date_picker);
        //selectedPlec = findViewById(R.id.)
        przejdzDalej2 = findViewById(R.id.zarejestruj_button);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        rejestracja22.setAnimation(topAnim);
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
