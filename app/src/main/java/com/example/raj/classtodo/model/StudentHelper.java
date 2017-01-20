package com.example.raj.classtodo.model;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.example.raj.classtodo.model.ListItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        String query="CREATE TABLE "+values[1]+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, sid INTEGER NOT NULL UNIQUE, mobile INTEGER NOT NULL UNIQUE, " +
                "date1 TEXT, date2 TEXT, date3 TEXT, date4 TEXT, date5 TEXT, date6 TEXT, date7 TEXT, date8 TEXT, date9 TEXT, date10 TEXT, " +
                "date11 TEXT, date12 TEXT, date13 TEXT, date14 TEXT, date15 TEXT, date16 TEXT, date17 TEXT, date18 TEXT, date19 TEXT NULL, date20 TEXT, " +
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
    public void dropDB() {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DETACH DATABASE StudentInfo.db";
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
    public void deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+ values[1] + " WHERE id='"+id+"';";
        db.execSQL(query);

    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from "+values[1], null);

        return res;
    }
    public Cursor getAllMonth(String month)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from "+month, null);

        return res;
    }
    public boolean tableExist(String month)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + month + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public String updateData(String id, String date,String x)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+ values[1] +"  SET date"+date+" ='"+x+"' WHERE id="+id;
        db.execSQL(query);
        return query;
    }
    public String updateData2(String id, String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+ values[1] +"  SET date"+date+" =NULL WHERE id="+id;
        db.execSQL(query);
        return query;
    }
    public String updateStudent(String name, String id,String mobile, String idd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+ values[1] +"  SET name='"+name+"', sid='"+id+"', mobile='"+mobile+"' WHERE id='"+idd+"';";
        db.execSQL(query);
        return query;
    }
    public String viewAll()
    {
        String month;
        switch (values[1]){
            case "January":
                month ="December";
                break;
            case "February":
                month ="January";
                break;
            case "March":
                month ="February";
                break;
            case "April":
                month ="March";
                break;
            case "May":
                month ="April";
                break;
            case "June":
                month ="May";
                break;
            case "July":
                month ="June";
                break;
            case "August":
                month ="July";
                break;
            case "September":
                month ="August";
                break;
            case "October":
                month ="September";
                break;
            case "November":
                month ="December";
                break;
            case "December":
                month ="December";
                break;
            default:
                month =null;
                break;
        }
        Cursor res = getAllMonth(month);
        String k = null;
        int x;
        if(res.getCount()==0)
        {
            return "No data";


        }
        else
        {
            while(res.moveToNext())
            {
                int count=0;
                for(int i=4;i<=34;i++) {
                    k=res.getString(i);
                    if(k==null)
                    {

                    }
                    else
                    {
                        count=count+1;
                    }
                    if(count>3)
                    {
                     k=k+res.getString(2)+", ";
                    }

                }

            }
        }
        return k;

    }



}
