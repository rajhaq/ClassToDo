package com.example.raj.classtodo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.raj.classtodo.model.StudentHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Pdf extends AppCompatActivity {
    Spinner spinner;
    int postionx;
    String  monthSelected;
    ArrayAdapter<CharSequence> adapter;
    public static StudentHelper studentDB;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
    String strDate = sdf.format(cal.getTime());

    String[] values = strDate.split("/",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        int dbv=Integer.parseInt(values[0]);
        studentDB=new StudentHelper(this, dbv);
        spinner=(Spinner) findViewById(R.id.spinnerMonth);
        adapter=ArrayAdapter.createFromResource(this,R.array.month_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthSelected= (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void createPDF(View view)
    {
        if(studentDB.tableExist(monthSelected)) {
            Document doc = new Document(PageSize.A3.rotate());
            String outPath = Environment.getExternalStorageDirectory() + "/" + monthSelected + ".pdf";
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(outPath));
                doc.open();
                doc.add(new Paragraph(viewAll()));
                doc.close();
                Toast.makeText(getBaseContext(), monthSelected+".pdf created on Storage", Toast.LENGTH_LONG).show();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "No Data Found", Toast.LENGTH_LONG).show();
        }
    }
    public void ViewPDF(View view)
    {
        String path= Environment.getExternalStorageDirectory()+"/"+monthSelected+".pdf";
        File file =new File(path);
        if(file.exists())
        {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            Intent intent = Intent.createChooser(target, "Open File");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {

            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "No Data Found", Toast.LENGTH_LONG).show();
        }
    }
    public String viewAll()
    {
                        Cursor res = studentDB.getAllMonth(monthSelected);
        String k;
                        int x;
                        if(res.getCount()==0)
                        {
                            return "No data";


                        }
                        else
                        {
                            StringBuffer buffer=new StringBuffer();
                            buffer.append("________________________________________________________________________________"+monthSelected+"__________________________________________________________________________\n");
                            while(res.moveToNext())
                            {
                                x= res.getString(2).length();
                                buffer.append(res.getString(1)+"\n("+res.getString(2)+")__");
/*                                for(int i=x;i<=25;i++)
                                    buffer.append(" ");*/

                                for(int i=4;i<=34;i++) {
                                    k=res.getString(i);
                                    if(k==null)
                                        buffer.append(i-4+"(A)-");
                                    else
                                        buffer.append(i-4+"(P)-");

                                }

                                buffer.append("Bonus:"+res.getString(35));
                                buffer.append("\n");
                            }
                            return buffer.toString();
                        }

    }
}
