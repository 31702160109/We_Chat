package com.example.administrator.shixun.db_util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Util {
    private static MyHelper mHelper=null;
    public static SQLiteDatabase getWriteDb(Context context){
        if (mHelper==null){
            mHelper=new MyHelper(context);
        }
        SQLiteDatabase db=mHelper.getWritableDatabase();
        return db;
    }
    public static SQLiteDatabase getReadDb(Context context){
        if (mHelper==null){
            mHelper=new MyHelper(context);
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        return db;
    }
}
