package com.example.raj.classtodo.ui;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.model.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Database extends AppCompatActivity {
    public static StudentHelper studentDB;
    EditText editName,editID,editMobile,deleteBox;
    Button DB,addButton,dropButton,viewAllButton,deleteButton;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int dbv=Integer.parseInt(values[0]);
        studentDB=new StudentHelper(this, dbv);

        deleteBox =(EditText)findViewById(R.id.deleteID);
 //       editName =(EditText)findViewById(R.id.editStudentName);
   //     editID =(EditText)findViewById(R.id.editStudentId);
     //   editMobile =(EditText)findViewById(R.id.editStudentMobile);
       // addButton=(Button)findViewById(R.id.buttonAddStudent);
        //       dropButton=(Button)findViewById(R.id.buttonDrop);
        viewAllButton=(Button)findViewById(R.id.buttonViewAll);
        DB=(Button)findViewById(R.id.buttonDB);
        deleteButton=(Button)findViewById(R.id.buttonDelete);
        //      date=(TextView) findViewById(R.id.todayDate);
   //     AdddData();
        //    DropData();
        viewAll();
        deleteData();
        DropData();
        //    date.setText(strDate);


    }

    public void deleteData()
    {
        deleteButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        studentDB.deleteData(deleteBox.getText().toString());

                        Toast.makeText(Database.this,"Data deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
        public void DropData()
        {
            DB.setOnClickListener(
                    new View.OnClickListener()
                    {
                        public void onClick(View v)
                        {
                            studentDB.dropTable();
                            Toast.makeText(Database.this,"table Dropped",Toast.LENGTH_LONG).show();

                        }
                    }
            );
        }
    public void viewAll()
    {
        viewAllButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Cursor res = studentDB.getAllData();
                        if(res.getCount()==0)
                        {
                            showMsg("ERROR","Nothing Found");

                            return;

                        }
                        else
                        {
                            StringBuffer buffer=new StringBuffer();
                            while(res.moveToNext())
                            {

                                buffer.append("Default id :"+res.getString(0)+"\n");
                                buffer.append("Name :"+res.getString(1)+"\n");
                                buffer.append("ID :"+res.getString(2)+"\n");
                                buffer.append("Mobile :"+res.getString(3)+"\n");
                                buffer.append("---------------------\n");
                            }
                            showMsg("Student List", buffer.toString());
                        }

                    }
                }
        );
    }

    public void showMsg(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}