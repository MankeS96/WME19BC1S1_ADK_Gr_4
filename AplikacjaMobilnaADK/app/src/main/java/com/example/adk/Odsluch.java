package com.example.adk;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

public class Odsluch extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Animation topAnim, bottomAnim;
    TextView komunikatOdsluch, statusTV2, status;
    Button powrot_do_menuB;
    ImageView playB, pauseB;
    Spinner spinner;

    private static final LinkedList<String> ListFileNames = new LinkedList<>();
    private MediaPlayer mPlayer;
    private static String mFileName = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_odsluch);


        komunikatOdsluch = findViewById(R.id.komunikatOdsluch);
        powrot_do_menuB = findViewById(R.id.powrot_do_menuB);
        spinner = findViewById(R.id.spinner);
        statusTV2 = findViewById(R.id.statusTV2);
        playB = findViewById(R.id.playB);
        status = findViewById(R.id.status);
        //pauseB = findViewById(R.id.pauseB);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        komunikatOdsluch.setAnimation(topAnim);
        spinner.setAnimation(topAnim);
        statusTV2.setAnimation(topAnim);
        playB.setAnimation(bottomAnim);
        //pauseB.setAnimation(bottomAnim);
        powrot_do_menuB.setAnimation(bottomAnim);

        ListFileNames.clear();
        ListFileNames.add("<<wybierz>>");

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ListFileNames);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(ad);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        assert files != null;
        Log.d("Files", "Size: "+ files.length);
        for (File file : files) {
            if (file.isFile()) {
                //ListFileNames.add(path + '/' + files[i].getName());
                ListFileNames.add(file.getAbsolutePath());
            }

            Log.d("Files", "FileName:" + file.getName());
        }

        playB.setOnClickListener(v -> playAudio());
        //pauseB.setOnClickListener(v -> pausePlaying());
    }

    public void playAudio() {
        mPlayer = new MediaPlayer();
        playB.setEnabled(false);
        playB.setColorFilter(getResources().getColor(R.color.gray));
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            Toast.makeText(Odsluch.this, "Rozpoczęto odtwarzanie", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
        PrimeRun p = new PrimeRun(143);
        new Thread(p).start();

    }

    public void pausePlaying() {
        mPlayer.release();
        mPlayer = null;
        Toast.makeText(Odsluch.this, "Zatrzymano odtwarzanie", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String name = ListFileNames.get(position);
        if(position != 0) {
            statusTV2.setText(name);
            mFileName = name;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    class PrimeRun implements Runnable {
        long minPrime;
        PrimeRun(long minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0L;

            while (elapsedTime < 10*1000) {
                //perform db poll/check
                elapsedTime = (new Date()).getTime() - startTime;
                double x = (double)elapsedTime/ 1000;

                //statusTV3.setText(Double.toString(x) + " sek.");
                status.setText( String.format("%,.2f sek.", x));
            }

            status.setText("");
            playB.setEnabled(true);
            playB.setColorFilter(getResources().getColor(R.color.kolor2));
        }
    }

    public void callOknoUzytkownika(View view){

        Intent intent = new Intent(Odsluch.this, OknoUzytkownika.class);
        startActivity(intent);
    }

}