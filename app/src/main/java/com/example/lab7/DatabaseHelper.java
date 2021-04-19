package com.example.lab7;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Company.db";
    public static final String TABLENAME = "Employees";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SALARY = "Salary";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //execSQL is used when there is no retrievable value expected,
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLENAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_SALARY + " INTEGER NOT NULL)"); }
    public void AddEmployee(String id, String name, int salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);

        values.put(COLUMN_SALARY, salary);
        db.insert(TABLENAME, null,values); }
    public Cursor ViewEmployees(){
        SQLiteDatabase db = this.getWritableDatabase();
        //rawQuery allows statements that have a return value like SELECT
        Cursor x = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        return x;
    }
    public Integer DeleteEmployees(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME, "ID = ?", new String[]
                {id});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}