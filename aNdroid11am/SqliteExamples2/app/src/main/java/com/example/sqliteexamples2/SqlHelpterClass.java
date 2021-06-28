package com.example.sqliteexamples2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class SqlHelpterClass  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String MARKS = "MARKS";


    public SqlHelpterClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

//    public  boolean inserdata(String name,String sruname,String marks)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NAME,name);
//        contentValues.put(SURNAME,sruname);
//        contentValues.put(MARKS,marks);
//        long result =   db.insert(TABLE_NAME,null,contentValues);
//
//        if(result == -1)
//        {
//            return false;
//        }else
//        {
//            return true;
//        }
//
//
//
//    }

    public boolean insertdata(String name,String surname,String marks)
    {
        SQLiteDatabase sql = this.getWritableDatabase();

               ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(SURNAME,surname);
        contentValues.put(MARKS,marks);
        long result = sql.insert(TABLE_NAME,null,contentValues);
                if(result == -1) {
            return false;
        }else
        {
            return true;
        }
    }

//    public Cursor getAllData()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
//        return cursor;
//    }

    public Cursor getalldata()
    {
        SQLiteDatabase sql = this.getWritableDatabase();

        Cursor cursor = sql.rawQuery("select * from "+TABLE_NAME+"where ID == ?",null);
        return cursor;
    }

    public  boolean updatadata(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(SURNAME,surname);
        contentValues.put(MARKS,marks);
      int result =  db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
 if(result > 0 )
 {
     return true;
 }else{
     return false;
 }


    }
    public Integer deletedata(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }

}
