package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
    TextView rejestracja_naglowek;
    TextInputLayout imie_rej, nazwisko_rej, pesel_rej, email_rej, numer_rej, haslo_rej;
    Button zarejestrujB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        rejestracja_naglowek = findViewById(R.id.Text1rej);
        imie_rej = findViewById(R.id.wpisz_imie);
        nazwisko_rej = findViewById(R.id.wpisz_nazwisko);
        email_rej = findViewById(R.id.wpisz_adres_email);
        pesel_rej = findViewById(R.id.wpisz_numer_pesel);
        numer_rej = findViewById(R.id.wpisz_numer_telefonu);
        haslo_rej =findViewById(R.id.wpisz_haslo_reg);
        zarejestrujB = findViewById(R.id.zarejestruj_button);


        rejestracja_naglowek.setAnimation(topAnim);
        imie_rej.setAnimation(bottomAnim);
        nazwisko_rej.setAnimation(bottomAnim);
        email_rej.setAnimation(bottomAnim);
        pesel_rej.setAnimation(bottomAnim);
        numer_rej.setAnimation(bottomAnim);
        haslo_rej.setAnimation(bottomAnim);
        zarejestrujB.setAnimation(bottomAnim);

        zarejestrujB.setOnClickListener(v -> {
            Toast toast1 = Toast.makeText(RegisterActivity.this, "Rejestracja przebiegła pomyślnie!", Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.TOP|Gravity.CENTER, 5, 50);
            toast1.show();
            Toast toast2 = Toast.makeText(RegisterActivity.this, "Wróć do okna logowania, aby zalogować się do aplikacji", Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.TOP|Gravity.CENTER, 5,50);
            toast2.show();
        });
    }
}
