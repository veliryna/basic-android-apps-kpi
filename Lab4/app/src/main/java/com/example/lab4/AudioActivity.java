package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.TextView;

public class AudioActivity extends AppCompatActivity {
    MediaPlayer player;
    Uri currentUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            currentUri = Uri.parse(value);
            TextView song = findViewById(R.id.song_name);
            song.setText(getFileName(currentUri));
        }
    }

    public void play(View view){
        if(player == null){
            player = MediaPlayer.create(this, currentUri);
            player.setOnCompletionListener(mp -> stopPlayer());
        }
        player.start();
    }

    public void pause(View view){
        if(player != null){
            player.pause();
        }
    }

    public void stop(View view){
        stopPlayer();
    }

    private void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }
    }

    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}