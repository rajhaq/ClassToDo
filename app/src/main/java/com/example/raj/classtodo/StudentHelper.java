package com.example.raj.classtodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Raj on 1/10/2017.
 */

public class StudentHelper extends SQLiteOpenHelper{
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.US); // Set your locale!
    String strDate = sdf.format(cal.getTime());
    private String[] values = strDate.split("/",0);


    public StudentHelper(Context context,int version) {
        super(context, "StudentInfo.db", null, version);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+values[1]+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, sid INTEGER, mobile INTEGER, " +
                "date1 TEXT, date2 TEXT, date3 TEXT, date4 TEXT, date5 TEXT, date6 TEXT, date7 TEXT, date8 TEXT, date9 TEXT, date10 TEXT, " +
                "date11 TEXT, date12 TEXT, date13 TEXT, date14 TEXT, date15 TEXT, date16 TEXT, date17 TEXT, date18 TEXT, date19 TEXT, date20 TEXT, " +
                "date21 TEXT, date22 TEXT, date23 TEXT, date24 TEXT, date25 TEXT, date26 TEXT, date27 TEXT, date28 TEXT, date29 TEXT, date30 TEXT, date31 TEXT,bonus INTEGER " +
                " )";
        db.execSQL(query);

    }
    public void dropTable() {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DROP TABLE IF EXISTS  "+values[1];
        db.execSQL(query);
        onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }
    public boolean insertData(String name,String sid, String mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name ",name);
        contentValues.put("sid ",sid);
        contentValues.put("mobile ",mobile);
        long result=db.insert(values[1],null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+values[1], null);
        return res;
    }

}
