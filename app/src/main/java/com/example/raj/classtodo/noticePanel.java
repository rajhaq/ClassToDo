package com.example.raj.classtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class noticePanel extends AppCompatActivity {

    EditText noticeInput;
    TextView noticeLog;
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_panel);
        noticeInput= (EditText) findViewById(R.id.noticeInput);
        noticeLog=(TextView) findViewById(R.id.noticeLogText);
        dbHandler= new MyDBHandler(this,null,null,1);
        printDatabase();


    }

    public void addNoticeClicked(View view)
    {
        Product products= new Product(noticeInput.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();

    }
    public void deleteNoticeClicked(View view)
    {
        String inputText=noticeInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();

    }
    public void printDatabase()
    {
        String dbString=dbHandler.databaseToString();
        noticeLog.setText(dbString);
        noticeInput.setText("");


    }

}
