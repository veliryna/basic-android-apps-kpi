package com.example.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StorageActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ArrayList<String> fileList = new ArrayList<>();
        File folder = this.getFilesDir();
        File[] filesInFolder = folder.listFiles();
        if(filesInFolder == null){
            Toast.makeText(this, "No files here yet",Toast.LENGTH_SHORT).show();
        }
        else{
            for (File file : filesInFolder) {
                if (!file.isDirectory()) {
                    fileList.add(file.getName());
                }
            }
            lv = findViewById(R.id.list_files);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    fileList);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener((parent, view, position, id) -> {
                String selectedFile = (String) parent.getItemAtPosition(position);
                FileInputStream fin = null;
                try {
                    fin = openFileInput(selectedFile);
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    String text = new String(bytes);
                    new AlertDialog.Builder(this)
                            .setTitle("File Content")
                            .setMessage(text)
                            .setPositiveButton(android.R.string.yes, null).show();

                } catch(IOException ex) {
                    Toast.makeText(this, ex.getMessage(),
                            Toast.LENGTH_SHORT).show();
                } finally {
                    try{
                        if(fin!=null) fin.close();
                    } catch(IOException ex) {
                        Toast.makeText(this, ex.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
