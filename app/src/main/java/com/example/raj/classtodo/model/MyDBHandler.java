package com.example.raj.classtodo.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import com.example.raj.classtodo.adapter.Product;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="Classtodo.db";
    public static final String TABLE_PRODUCTS="notice";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_PRODUCTNAME="_productname";
    public String[] dbS;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+ TABLE_PRODUCTS +"( " +
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRODUCTNAME + " TEXT "+
                " )";
        db.execSQL("CREATE TABLE "+ TABLE_PRODUCTS +" ( " +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_PRODUCTNAME +" TEXT );");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
    public void clearDB()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //add new raw to db
    public  void addProduct(Product product)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
//    delete produt db
    public void deleteProduct(String productName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PRODUCTS+ " WHERE "+ COLUMN_ID + "=\"" +productName + "\";" );
    }
/*
    public String databaseToString()
    {
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_PRODUCTS+" WHERE 1;";
        Cursor c= db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("productname"))!=null)
            {
                dbString +=c.getString(c.getColumnIndex("productname"));
                dbString +="\n";

            }
        }
        db.close();
        return dbString;
    }
*/
public String databaseToString(){
    String dbString = "";
    SQLiteDatabase db = getWritableDatabase();

    String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";

    Cursor c = db.rawQuery(query, null);

    c.moveToFirst();

    while (!c.isAfterLast()){
        if (c.getString(c.getColumnIndex("_productname")) != null){
            dbString += c.getString(c.getColumnIndex("_productname"));
            dbString += "\n";
        }
        c.moveToNext();
    }
    db.close();
    return dbString;
}
    public String databaseToId(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_id")) != null){
                dbString += c.getString(c.getColumnIndex("_id"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
    public String databaseS(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();
        int i=0;
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_productname")) != null){
                dbString = c.getString(c.getColumnIndex("_productname"));
                dbS[i]=dbString;
                i++;
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }



}
