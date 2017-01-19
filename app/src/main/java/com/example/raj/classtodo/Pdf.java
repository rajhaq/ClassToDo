package com.example.raj.classtodo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
       String path= Environment.getExternalStorageDirectory()+"/student.pdf";
        File file =new File(path);
        Intent target=new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(target,"Open File");
        try {
            startActivity(intent);
        }catch(ActivityNotFoundException e)
        {

        }


    }
    public void createPDF(View view)
    {
        EditText text=(EditText)findViewById(R.id.editPdf);
        Document doc=new Document(PageSize.A3.rotate());
        String outPath= Environment.getExternalStorageDirectory()+"/student2.pdf";
        try {
            PdfWriter.getInstance(doc,new FileOutputStream(outPath));
            doc.open();
            String x="Name      01  |02  |03  |04  |05  |06  |07  |08  |09  |10  |11  |12  |13  |14  |15  |16  |17  |18  |19  |20  |21  |22  |23  |24  |25  |26  |27  |28  |29  |30\n" +
                    "Name      01  |02  |03  |04  |05  |06  |07  |08  |09  |10  |11  |12  |13  |14  |15  |16  |17  |18  |19  |20  |21  |22  |23  |24  |25  |26  |27  |28  |29  |30\n";
            doc.add(new Paragraph(viewAll()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String viewAll()
    {
                        Cursor res = studentDB.getAllData();
                        String j="1";
        String k;
                        int x;
                        if(res.getCount()==0)
                        {
                            return "No data";


                        }
                        else
                        {
                            StringBuffer buffer=new StringBuffer();
                            buffer.append("________________________________________________________________________________"+values[1]+"__________________________________________________________________________\n");
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
