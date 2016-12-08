package com.example.raj.classtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class teacherPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void goStartToday(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }
    public void goMonthView(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }
    public void goEdit(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }

}
