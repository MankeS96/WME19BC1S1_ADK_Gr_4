package com.example.adk;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OknoUzytkownika extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView  naglowek1;
    TextView naglowek2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_uzytkownika);

        //Hooks
        naglowek1 = findViewById(R.id.naglowek_oknouz1);
        naglowek2 = findViewById(R.id.naglowek_oknouz2);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        naglowek1.setAnimation(topAnim);
        naglowek2.setAnimation(topAnim);

    }
}