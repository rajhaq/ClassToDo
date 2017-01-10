package com.example.raj.classtodo.ui;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditStudent extends AppCompatActivity {
    StudentHelper studentDB;
    EditText editName,editID,editMobile;
    Button addButton,dropButton,viewAllButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        int dbv=Integer.parseInt(values[0]);
        studentDB=new StudentHelper(this, dbv);

        editName =(EditText)findViewById(R.id.editStudentName);
        editID =(EditText)findViewById(R.id.editStudentId);
        editMobile =(EditText)findViewById(R.id.editStudentMobile);
        addButton=(Button)findViewById(R.id.buttonAddStudent);
        dropButton=(Button)findViewById(R.id.buttonDrop);
        viewAllButton=(Button)findViewById(R.id.buttonViewAll);
        AdddData();
        DropData();
        viewAll();


    }
    public void AdddData()
    {
        addButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean isInster=studentDB.insertData(editName.getText().toString(),editID.getText().toString(),editMobile.getText().toString());
                        if(isInster==true)
                            Toast.makeText(EditStudent.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditStudent.this,"Data not inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void DropData()
    {
        dropButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        studentDB.dropTable();
                            Toast.makeText(EditStudent.this,"May be dropped",Toast.LENGTH_LONG).show();

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
                                buffer.append("Name :"+res.getString(1)+"\n");
                                buffer.append("ID :"+res.getString(2)+"\n");
                                buffer.append("Mobile :"+res.getString(3)+"\n");
                                buffer.append("07 :"+res.getString(10)+"\n\n");
                                buffer.append("08 :"+res.getString(11)+"\n\n");
                                buffer.append("09 :"+res.getString(12)+"\n\n");
                                buffer.append("10 :"+res.getString(13)+"\n\n");
                                buffer.append("11 :"+res.getString(14)+"\n\n");
                                buffer.append("12 :"+res.getString(15)+"\n\n");
                                buffer.append("13 :"+res.getString(16)+"\n\n");
                                buffer.append("14 :"+res.getString(17)+"\n\n");
                                buffer.append("15 :"+res.getString(18)+"\n\n");
                                buffer.append("16 :"+res.getString(19)+"\n\n");
                                buffer.append("17 :"+res.getString(20)+"\n\n");
                            }
                            showMsg("Data", buffer.toString());
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