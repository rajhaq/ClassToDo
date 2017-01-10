package com.example.raj.classtodo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.raj.classtodo.R;

public class patentsPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patents_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
