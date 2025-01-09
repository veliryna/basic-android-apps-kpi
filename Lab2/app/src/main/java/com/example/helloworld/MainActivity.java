package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentChoice.YearClicked{
    @Override
    public void sendData(String year){
        FragmentResult frag = (FragmentResult)
                getSupportFragmentManager().findFragmentById(R.id.fragment_result);
        assert frag != null;
        frag.showResult(year);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}