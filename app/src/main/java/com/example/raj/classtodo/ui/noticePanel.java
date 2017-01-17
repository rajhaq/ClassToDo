package com.example.raj.classtodo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raj.classtodo.model.MyDBHandler;
import com.example.raj.classtodo.adapter.Product;
import com.example.raj.classtodo.R;


//editttttttttttttttttttttttttttttttttttttttttttt
public class noticePanel extends AppCompatActivity {

    EditText noticeInput;
    TextView noticeLog;
    TextView noticeNumberLog;
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_panel);
        noticeInput= (EditText) findViewById(R.id.noticeInput);
        noticeLog=(TextView) findViewById(R.id.noticeLogText);
        noticeNumberLog=(TextView) findViewById(R.id.noticeNumberText);
        dbHandler= new MyDBHandler(this,null,null,1);
        printDatabase();
        printId();
        int x=1;
        if(x==1)
        {

        }


/*
        String str=dbHandler.databaseS();
        String[] t=dbHandler.dbS;
        StringTokenizer st=new StringTokenizer(str, "\n");

        int i=0;
        for(i=0;i<str.length();i++)
        {

        }



        String [] foods ={"bacon","potato","tuna","meatball","bacon","potato","tuna","meatball","bacon","potato","tuna","meatball"};
        ListAdapter foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, t);
        ListView foodListView= (ListView)findViewById(R.id.noticeList);
        foodListView.setAdapter(foodAdapter);



*/


    }

    public void addNoticeClicked(View view)
    {
        Product products= new Product(noticeInput.getText().toString());
        String st=noticeInput.getText().toString();

            dbHandler.addProduct(products);
            String x = "\"" + st + "\" Added";
            Toast.makeText(noticePanel.this, x, Toast.LENGTH_LONG).show();
        printDatabase();
        printId();

    }
    public void deleteNoticeClicked(View view)
    {
        String inputText=noticeInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
        printId();
    }
    public void printDatabase()
    {
        String dbString=dbHandler.databaseToString();

        noticeLog.setText(dbString);
        noticeInput.setText("");


    }
    public void printId()
    {
        String dbString=dbHandler.databaseToId();
        noticeNumberLog.setText(dbString);
        noticeInput.setText("");


    }


}
