package com.example.administrator.shixun.db_page;

public class Infor {
    public static final String DATABASE_NAME="mywx.db";
    public static final int VERSION=2;
    public static final String CREATE_TABLE_USER=
            "CREATE TABLE user(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username VARCHAR(20)," +
                    "password VARCHAR(20)" +
                    ")";
    public static final String CREATE_TABLE_CHAT=
            "CREATE TABLE chat(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(20)," +
                    "chat TEXT," +
                    "time VARCHAR(15)"+
                    ")";
    public static final String CREATE_TABLE_CONTACTS=
            "CREATE TABLE contacts(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(20)," +
                    "user VARCHAR(20)" +
                    ")";
}
