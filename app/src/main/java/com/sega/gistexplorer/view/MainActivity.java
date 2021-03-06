package com.sega.gistexplorer.view;

import android.os.Bundle;

import com.sega.gistexplorer.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, GistFragment.newInstance()).commit();
        }
    }

}