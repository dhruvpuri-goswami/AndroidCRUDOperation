package com.example.androidcrudoperation.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.androidcrudoperation.modal.Courses;
import com.example.androidcrudoperation.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context){
        super(context, Params.DB_NAME,null,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME +"("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " +
                Params.KEY_NAME + " TEXT, " +
                Params.KEY_DURATION + " TEXT, " +
                Params.KEY_DESC + " TEXT " +")";
        Log.d("db","Query being run is : " +create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addcourse(Courses courses){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME, courses.getName());
        values.put(Params.KEY_DURATION, courses.getDuration());
        values.put(Params.KEY_DESC, courses.getDescription());
        db.insert(Params.TABLE_NAME,null,values);
        Log.d("dbInsert","Values are Inserted...");
        db.close();
    }

    public int updatecourse(Courses courses){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME, courses.getName());
        values.put(Params.KEY_DURATION, courses.getDuration());
        values.put(Params.KEY_DESC, courses.getDescription());

        return db.update(Params.TABLE_NAME,values,Params.KEY_NAME + "=?",
                new String[]{String.valueOf(courses.getName())});
    }

    public void deletecourse(String item){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_NAME + "=?",
                new String[]{String.valueOf(item)});
        db.close();
    }

    public List<Courses> getAllUsers(){
        List<Courses> itemsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do {
                Courses items = new Courses();
                items.setId(Integer.parseInt(cursor.getString(0)));
                items.setName(cursor.getString(1));
                items.setDuration(cursor.getString(2));
                items.setDescription(cursor.getString(2));
                itemsList.add(items);
            }while (cursor.moveToNext());
        }
        return itemsList;
    }

}
