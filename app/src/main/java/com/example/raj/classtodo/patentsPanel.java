package com.example.raj.classtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class patentsPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patents_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
