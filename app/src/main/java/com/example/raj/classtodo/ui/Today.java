package com.example.raj.classtodo.ui;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.model.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Today extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button saveLog = (Button) findViewById(R.id.savetoday);
        saveLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox c = (CheckBox) findViewById(R.id.checkBox);

                if (c.isChecked()) {

                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "+8801722876856", null));
                    intent.putExtra("sms_body", "Hello, Your Student Zubaer Haque (1412020036) is absent today. -Leading University");
                    startActivity(intent);

                }
            }
        });
    }

}