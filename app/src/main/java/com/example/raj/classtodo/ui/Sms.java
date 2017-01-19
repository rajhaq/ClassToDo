package com.example.raj.classtodo.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.model.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Raj on 1/20/2017.
 */

public class Sms extends AppCompatActivity {
    EditText smsBody;
    Button sendSms;
    Button cancelSms;
    public static StudentHelper studentDB;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
    String strDate = sdf.format(cal.getTime());

    String[] values = strDate.split("/",0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        int dbv=Integer.parseInt(values[0]);
        studentDB=new StudentHelper(this, dbv);
        smsBody=(EditText)findViewById(R.id.smsBody);
        sendSms = (Button) this.findViewById(R.id.buttonSend);
        cancelSms= (Button) this.findViewById(R.id.buttonNo);

        sendSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Get phone number & message sms
                // String phoneNo = txtPhoneNo.getText().toString();
                //                    sendMessage(phoneNo, message);
                if(smsLoop()=="No data")
                    Toast.makeText(getBaseContext(), "Please enter both phone number and message.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Sms Sent", Toast.LENGTH_LONG).show();
            }
        });

    }
    public String smsLoop(){
        Cursor res = studentDB.getAllData();
        String  k;
        int x;
        if(res.getCount()==0)
        {
            return "No data";
        }
        else
        {
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("Yes");
                x=Integer.parseInt(values[2]);
                x=x+3;
                k=res.getString(x);
                if(k==null)
                    sendMessage("+880"+res.getString(3),smsBody.getText().toString());

            }
            return buffer.toString();
        }

    }

    private void sendMessage(String phoneNo, String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void goStudentList(View view)
    {
        Intent i=new Intent (this, AddToday.class);

        startActivity(i);
    }
}

