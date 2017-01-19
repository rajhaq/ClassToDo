package com.example.raj.classtodo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.example.raj.classtodo.R;
import com.example.raj.classtodo.Pdf;


public class teacherPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void goStartToday(View view)
    {
        Intent i=new Intent (this,AddToday.class);

        startActivity(i);
    }
    public void goMonthView(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }
    public void goEdit(View view)
    {
        Intent i=new Intent (this, EditStudent.class);

        startActivity(i);
    }
    public void goNoticeEdit(View view)
    {
        Intent i=new Intent (this, noticePanel.class);

        startActivity(i);
    }
    public void goPDF(View view)
    {
        Intent i=new Intent (this, Pdf.class);

        startActivity(i);
    }

}
