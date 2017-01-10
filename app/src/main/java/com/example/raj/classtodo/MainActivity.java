package com.example.raj.classtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;

import com.example.raj.classtodo.ui.patentsPanel;
import com.example.raj.classtodo.ui.studentPanel;
import com.example.raj.classtodo.ui.teacherPanel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


    }
    public void goTeacher(View view)
    {
        Intent i=new Intent (this, teacherPanel.class);

        startActivity(i);
    }
    public void goParents(View view)
    {
        Intent i=new Intent (this, patentsPanel.class);

        startActivity(i);
    }
    public void goStudents(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }


}
