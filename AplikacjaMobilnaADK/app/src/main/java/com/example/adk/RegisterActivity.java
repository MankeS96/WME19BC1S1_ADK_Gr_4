package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    //Variable
    Animation topAnim, bottomAnim;
    TextView rejestracja_naglowek;
    TextInputLayout imie_rej, nazwisko_rej, pesel_rej, email_rej, haslo_rej;
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
        haslo_rej =findViewById(R.id.wpisz_haslo_reg);
        zarejestrujB = findViewById(R.id.zarejestruj_button);


        rejestracja_naglowek.setAnimation(topAnim);
        imie_rej.setAnimation(bottomAnim);
        nazwisko_rej.setAnimation(bottomAnim);
        email_rej.setAnimation(bottomAnim);
        pesel_rej.setAnimation(bottomAnim);
        haslo_rej.setAnimation(bottomAnim);
        zarejestrujB.setAnimation(bottomAnim);

//        zarejestrujB.setOnClickListener(v -> {
//            Toast toast1 = Toast.makeText(RegisterActivity.this, "Rejestracja przebiegła pomyślnie!", Toast.LENGTH_SHORT);
//            toast1.setGravity(Gravity.TOP|Gravity.CENTER, 5, 50);
//            toast1.show();
//            Toast toast2 = Toast.makeText(RegisterActivity.this, "Wróć do okna logowania,\naby zalogować się do aplikacji", Toast.LENGTH_LONG);
//            toast2.setGravity(Gravity.TOP|Gravity.CENTER, 5,50);
//            toast2.show();
//        });

        zarejestrujB.setOnClickListener(view -> {
            if(!validateImie() | !validateAdres() | !validateNazwisko() | !validatePESEL() | !validateHaslo()){
                return;
            }
            Intent intent = new Intent(RegisterActivity.this, RegisterActivity2.class);
            startActivity(intent);
        });
    }
    public void callRegisterActivity2(View view){
        validateImie();
        validateAdres();
        validateNazwisko();
        validatePESEL();
        validateHaslo();
    }
    public boolean validateImie(){
        String val = Objects.requireNonNull(imie_rej.getEditText()).getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if(val.isEmpty()){
            imie_rej.setError("Pole nie może być puste");
            return false;
        }else if(val.length()>20){
            imie_rej.setError("Nazwisko jest zbyt długie!");
            return false;
        }else if(!val.matches(checkspaces)){
            imie_rej.setError("Nie zostawiaj pustych przestrzeni w tym polu");
            return false;
        }else{
            imie_rej.setError(null);
            imie_rej.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateNazwisko(){
        String val = Objects.requireNonNull(nazwisko_rej.getEditText()).getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if(val.isEmpty()){
            nazwisko_rej.setError("Pole nie może być puste");
            return false;
        }else if(val.length()>20){
            nazwisko_rej.setError("Nazwisko jest zbyt długie!");
            return false;
        }else if(!val.matches(checkspaces)){
            nazwisko_rej.setError("Nie zostawiaj pustych przestrzeni w tym polu");
            return false;
        }else{
            nazwisko_rej.setError(null);
            nazwisko_rej.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validatePESEL(){
        String val = Objects.requireNonNull(pesel_rej.getEditText()).getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if(val.isEmpty()){
            pesel_rej.setError("Pole nie może być puste");
            return false;
        }else if(val.length()!=9){
            pesel_rej.setError("Podano nieprawidłową wartość numeru PESEL!");
            return false;
        }else if(!val.matches(checkspaces)){
            pesel_rej.setError("Nie zostawiaj pustych przestrzeni w tym polu");
            return false;
        }else{
            pesel_rej.setError(null);
            pesel_rej.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateAdres(){
        String val = Objects.requireNonNull(email_rej.getEditText()).getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            email_rej.setError("Pole nie może być puste");
            return false;
        }else if(!val.matches(checkEmail)){
            email_rej.setError("Niepoprawny adres email!");
            return false;
        }else{
            email_rej.setError(null);
            email_rej.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateHaslo(){
        String val = Objects.requireNonNull(haslo_rej.getEditText()).getText().toString().trim();
        String checkHaslo = "^"+
                "(?=.*[a-zA-Z])"+ // litery
                "(?=.*\\S+$)"+ // bez pustych przestrzeni
                ".{4,}"+ // przynajmniej 4 znaki
                "$";
        if(val.isEmpty()){
            haslo_rej.setError("Pole nie może być puste");
            return false;
        }else if(!val.matches(checkHaslo)){
            haslo_rej.setError("Hasło powinno składać się przynajmniej z 4 znaków!");
            return false;
        }else{
            haslo_rej.setError(null);
            haslo_rej.setErrorEnabled(false);
            return true;
        }
    }
}
