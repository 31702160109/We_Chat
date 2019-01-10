package com.example.administrator.shixun.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.shixun.bean.UserBean;
import com.example.administrator.shixun.db_util.MyHelper;
import com.example.administrator.shixun.db_util.Util;

import java.util.ArrayList;
import java.util.List;

public class User_ListDao {
    private static MyHelper mHelper;
    private static SQLiteDatabase wdb=null;
    private static SQLiteDatabase rdb=null;
    private static Context context;
    public User_ListDao(Context context_){
        context=context_;
    }
    public long insert(String name,String user){
        if (wdb==null){
            wdb= Util.getWriteDb(context);
        }
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("user",user);
        long id=wdb.insert("contacts",null,values);
        return id;
    }
    public long delete(){
        if (wdb==null){
            wdb= Util.getWriteDb(context);
        }
        long id=wdb.delete("contacts",null,null);
        return id;
    }
    public List<UserBean> find(){
        if (rdb==null){
            rdb=Util.getReadDb(context);
        }
        Cursor cursor =rdb.query("contacts",null,null,null,null,null,null);
        List<UserBean> list=new ArrayList<UserBean>();
        while (cursor.moveToNext()){
            UserBean userBean=new UserBean();
            userBean.setName(cursor.getString(1));
            userBean.setUser(cursor.getString(2));
            list.add(userBean);
        }
        return list;
    }
}
