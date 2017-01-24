package com.example.raj.classtodo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.Pdf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class teacherPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView mLabel = (TextView)findViewById(R.id.mLabel);
        TextView dLabel = (TextView)findViewById(R.id.dLabel);
        TextView eLabel = (TextView)findViewById(R.id.eLabel);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.ROOT); // Set your locale!
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);

        // Confirm
//        for (int i = 0; i < values.length; i++) {
//            Log.v("CHECK_DATE", values[i]);
//        }

        mLabel.setText(values[1]);
        dLabel.setText(values[2]);
        eLabel.setText(values[3]);

    }
    public void goStartToday(View view)
    {
        Intent i=new Intent (this,AddToday.class);

        startActivity(i);
    }
/*    public void goMonthView(View view)
    {
        Intent i=new Intent (this, studentPanel.class);

        startActivity(i);
    }*/
    public void goEdit(View view)
    {
        Intent i=new Intent (this, EditStudent.class);

        startActivity(i);
    }
/*    public void goNoticeEdit(View view)
    {
        Intent i=new Intent (this, noticePanel.class);

        startActivity(i);
    }*/
    public void goPDF(View view)
    {
        Intent i=new Intent (this, Pdf.class);

        startActivity(i);
    }
    public void goDB(View view)
    {
        Intent i=new Intent (this, Database.class);

        startActivity(i);
    }

}
