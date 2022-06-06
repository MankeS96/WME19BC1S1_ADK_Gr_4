package com.example.adk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class OknoBadanie2 extends AppCompatActivity{

    // constant for storing audio permission
    private static final int REQUEST_AUDIO_PERMISSION_CODE=1;

    // Initializing all variables..
    ImageView  recordB, grafika;
    TextView komunikatBadanie2;
    Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_okno_badanie2);

        //Hooks
        recordB = findViewById(R.id.recordButton);
        grafika = findViewById(R.id.naglowek_oknoBadanie2);
        komunikatBadanie2 = findViewById(R.id.komunikatBadanie2);
        chronometer = findViewById(R.id.record_Timer);

        recordB.setOnClickListener(view -> {
            // start recording method will
            // start the recording of audio.
            startRecording();
            recordB.setImageDrawable(ContextCompat.getDrawable(OknoBadanie2.this,R.drawable.record2));

        });
    }

    private void startRecording() {
        // check permission method is used to check
        // that the user has granted permission
        // to record nd store the audio.
        if (checkRecordingPermission()) {

            // we are here initializing our filename variable
            // with the path of the recorded audio file.
            // string variable is created for storing a file name
            String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName = mFileName + "/AudioRecording.3gp";

            // below method is used to initialize
            // the media recorder class
            // creating a variable for media recorder object class.
            MediaRecorder mRecorder = new MediaRecorder();

            // below method is used to set the audio
            // source which we are using a mic.
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            // below method is used to set
            // the output format of the audio.
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            // below method is used to set the
            // audio encoder for our recorded audio.
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // below method is used to set the
            // output file location for our recorded audio
            mRecorder.setOutputFile(mFileName);

            chronometer.setBase(SystemClock.elapsedRealtime());
            try {
                // below method will prepare
                // our audio recorder class
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            // start method will start
            // the audio recording.
            mRecorder.start();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA); //zmienic na polska
            Date now = new Date();
            chronometer.start();

        } else {
            // if audio recording permissions are
            // not granted by user below method will
            // ask for runtime permission for mic and storage.
            requestRecordingPermission();
        }
    }

    private void requestRecordingPermission(){
        ActivityCompat.requestPermissions(OknoBadanie2.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    public boolean checkRecordingPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_DENIED){
            requestRecordingPermission();
            return false;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_AUDIO_PERMISSION_CODE){
            if(grantResults.length>0){
                boolean permissionToRecord = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if (!permissionToRecord){
                    Toast.makeText(getApplicationContext(),"Odmowa dostępu do mikrofonu", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}