package com.example.raj.classtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class studentPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
