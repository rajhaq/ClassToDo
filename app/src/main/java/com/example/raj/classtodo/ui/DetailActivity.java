package com.example.raj.classtodo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raj.classtodo.R;
import com.example.raj.classtodo.model.StudentHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    public static StudentHelper studentDB;
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_1 = "EXTRA_1";
    private static final String EXTRA_2 = "EXTRA_2";
    Button updateButton,deleteButton;
    EditText name,id,mobile;
    String idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
        String strDate = sdf.format(cal.getTime());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String[] values = strDate.split("/",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int dbv=Integer.parseInt(values[0]);
        studentDB=new StudentHelper(this, dbv);

        updateButton=(Button)findViewById(R.id.buttonUpdate);
        deleteButton=(Button)findViewById(R.id.buttonDelete);
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        name=(EditText)findViewById(R.id.editText2);
        id=(EditText)findViewById(R.id.editText3);
        mobile=(EditText)findViewById(R.id.editText4);
        idd=extras.getString(EXTRA_1);




        name.setText(extras.getString(EXTRA_ATTR));
        id.setText(extras.getString(EXTRA_QUOTE));
        mobile.setText(extras.getString(EXTRA_2));
        updateStudent();
        deleteData();


    }
    public void updateStudent()
    {
        updateButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        studentDB.updateStudent(name.getText().toString(),id.getText().toString(),mobile.getText().toString(),idd);
                            Toast.makeText(DetailActivity.this,"Data Updated",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
    public void goNoticeEdit(View view)
    {

    }
    public void deleteData()
    {
        deleteButton.setOnClickListener(


                new View.OnClickListener()
                {

                    public void onClick(View v)
                    {

                        studentDB.deleteData(idd);

                        Toast.makeText(DetailActivity.this,"Student Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
}
