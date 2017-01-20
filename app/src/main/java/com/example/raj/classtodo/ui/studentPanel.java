package com.example.raj.classtodo.ui;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.raj.classtodo.R;

public class studentPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
