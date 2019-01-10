package com.example.administrator.shixun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.administrator.shixun.bean.MainBean;
import com.example.administrator.shixun.db_util.MyHelper;
import com.example.administrator.shixun.db_util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainDao {
    private static MyHelper mHelper;
    private static SQLiteDatabase wdb=null;
    private static SQLiteDatabase rdb=null;
    private static Context context;
    public MainDao(Context context_){
        context=context_;
    }
    public void insert(List<MainBean> list){
        if (wdb==null){
            wdb= Util.getWriteDb(context);
        }
        for (MainBean mainBean:list){
            ContentValues values=new ContentValues();
            values.put("name",mainBean.getName());
            values.put("chat",mainBean.getChat());
            values.put("time",mainBean.getTime());
            wdb.insert("chat",null,values);
        }
    }
    public void delete(){
        if (wdb==null){
            wdb= Util.getWriteDb(context);
        }
        wdb.delete("chat",null,null);
    }
    public List<MainBean> find(){
        if (rdb==null){
            rdb=Util.getReadDb(context);
        }
        Cursor cursor =rdb.query("chat",null,null,null,null,null,null);
        List<MainBean> list=new ArrayList<MainBean>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                MainBean mainBean=new MainBean();
                mainBean.setName(cursor.getString(1));
                mainBean.setChat(cursor.getString(2));
                mainBean.setTime(cursor.getString(3));
                list.add(mainBean);
            }
            return list;
        }else {
            return null;
        }
    }
}
