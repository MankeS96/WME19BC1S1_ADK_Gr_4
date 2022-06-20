package com.example.adk;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OknoBadanie2 extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView recordB, grafika;
    TextView komunikatBadanie2, statusTV3, statusTV;
    private MediaRecorder mRecorder;
    private long timeLeftInMilliseconds = 30000; //30 sek
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_badanie2);

        recordB = findViewById(R.id.recordButton);
        grafika = findViewById(R.id.naglowek_oknoBadanie2);
        komunikatBadanie2 = findViewById(R.id.komunikatBadanie2);
        statusTV3 = findViewById(R.id.idTVstatus3);
        statusTV = findViewById(R.id.idTVstatus);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        komunikatBadanie2.setAnimation(topAnim);
        //grafika.setAnimation(topAnim);
        statusTV.setAnimation(bottomAnim);
        statusTV3.setAnimation(bottomAnim);
        recordB.setAnimation(bottomAnim);

        recordB.setOnClickListener(view -> startRecording());


        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
    }

    private String getNameByDateTime() {

        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyMMdd_hhmmss");
        //String strDate = "Audio_"; // + dateFormat.format(date);
        return "A_" + dateFormat.format(date);
    }

    @SuppressLint("SetTextI18n")
    private void startRecording() {
        if (CheckPermissions()) {
            String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/" + getNameByDateTime() + ".3gp";

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mFileName);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            mRecorder.start();
            startTimer();
            recordB.setImageDrawable(getResources().getDrawable(R.drawable.record2));
            statusTV.setText("Rozpoczęto pomiar nr 1");

        } else {
            RequestPermissions();
        }
    }

    @SuppressLint("SetTextI18n")
    public void pauseRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        recordB.setImageDrawable(getResources().getDrawable(R.drawable.record));
        statusTV.setText("Zakończono pomiar nr 1");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (permissionToRecord && permissionToStore) {
                    Toast.makeText(getApplicationContext(), "Przyznano uprawnienia", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Odrzucono uprawnienia", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void startTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                pauseRecording();
            }
        }.start();

    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds /60000;
        int seconds = (int) timeLeftInMilliseconds %60000/1000;
        String timeLeftText;
        timeLeftText = "" +minutes;
        timeLeftText += ":";
        if (seconds<10) timeLeftText += "0";
        timeLeftText += seconds;
        statusTV3.setText(timeLeftText);
    }

    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestPermissions() {
        ActivityCompat.requestPermissions(OknoBadanie2.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

}