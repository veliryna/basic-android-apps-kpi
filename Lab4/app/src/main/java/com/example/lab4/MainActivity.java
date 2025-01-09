package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Intent intentAudio;
    private Intent intentVideo;
    ActivityResultLauncher<Intent> audioGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    Uri uri = data.getData();
                    if(intentAudio == null)
                        intentAudio = new Intent(this, AudioActivity.class);
                    intentAudio.putExtra("key", uri.toString());
                    startActivity(intentAudio);
                }
            });
    ActivityResultLauncher<Intent> videoGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    Uri uri = data.getData();
                    if(intentVideo == null)
                        intentVideo = new Intent(this, VideoActivity.class);
                    intentVideo.putExtra("key", uri.toString());
                    startActivity(intentVideo);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAudio = findViewById(R.id.find_audio_btn);
        Button btnVideo = findViewById(R.id.find_video_btn);
        btnAudio.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            audioGetContent.launch(intent);
        });
        btnVideo.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*");
            videoGetContent.launch(intent);
        });
    }
}