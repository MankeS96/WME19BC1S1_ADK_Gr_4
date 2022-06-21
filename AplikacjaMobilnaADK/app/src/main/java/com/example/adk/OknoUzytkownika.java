package com.example.adk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class OknoUzytkownika extends AppCompatActivity {
    //Variable
    Animation topAnim, bottomAnim;
    TextView naglowekOknouz1;
    ImageView naglowekOknouz2;
    GridLayout grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_uzytkownika);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        naglowekOknouz2 = findViewById(R.id.naglowek_oknouzytkownika);
        naglowekOknouz1 = findViewById(R.id.naglowek_oknouzytkownika2);
        grid = findViewById(R.id.grid);

        naglowekOknouz1.setAnimation(topAnim);
        naglowekOknouz2.setAnimation(topAnim);
        grid.setAnimation(bottomAnim);
    }
    public void callOknoBadanie1(View view){

        Intent intent = new Intent(OknoUzytkownika.this, OknoBadanie1.class);
        startActivity(intent);
    }
    public void callOknoWyniki1(View view){

        Intent intent = new Intent(OknoUzytkownika.this, OknoWyniki1.class);
        startActivity(intent);
    }
}