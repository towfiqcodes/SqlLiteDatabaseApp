package com.backerror.rit.sqllitedatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION=1;
    private static  final String DATABASE_NAME="userinfo";
    private static  final String TABLE_NAME="user";

    private  static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static  final String KEY_PHONE_NUMBER="phone_number";

    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }
   //create a database table for my model class.this name is UserInfo ;
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATR_USER_INFO_TABLE="CREATE TABLE "+TABLE_NAME+"("
                + KEY_ID+ " INTEGER PRIMARY KEY, "
                +KEY_NAME+" TEXT, "
                +KEY_PHONE_NUMBER+" TEXT)";
        db.execSQL( CREATR_USER_INFO_TABLE );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Add user Information
    public void addUserInfo(UserInfo userInfo){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        //values.put( KEY_ID,userInfo.getUserId());
        values.put( KEY_NAME,userInfo.getUserName());
        values.put( KEY_PHONE_NUMBER,userInfo.getPhoneNumber());
        sqLiteDatabase.insert( TABLE_NAME,null,values );
        sqLiteDatabase.close();

    }
    public List<UserInfo> getAllData(){
        List<UserInfo> userDataList=new ArrayList();
        String sql="Select * from "+ TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery( sql,null );
        if(cursor.moveToFirst()){
            do{
                UserInfo userInfo=new UserInfo();
                userInfo.setUserId(Integer.parseInt( cursor.getString(0 ) ));
                userInfo.setUserName( cursor.getString( 1 ) );
                userInfo.setPhoneNumber( cursor.getString( 2) );
                userDataList.add( userInfo );
            }while (cursor.moveToNext());
        }
        return  userDataList;
    }
    //update user Information in my database
    public int updateUserInfo(UserInfo info){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues(  );
        values.put( KEY_NAME, info.getUserName());
        values.put( KEY_PHONE_NUMBER,info.getPhoneNumber());
        return sqLiteDatabase.update( TABLE_NAME,values,KEY_ID +" = ?", new String[]{String.valueOf( info.getUserId())});
    }
    //Delete user information from my SQLite Database
    public void deleteUser(UserInfo info){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete( TABLE_NAME,KEY_ID + " = ?", new String[]{String.valueOf( info.getUserId())});
        sqLiteDatabase.close();

    }
    //count how many user contacts avaiable in my SQLite Database
    public int getCountUser(){
        String countQuery="Select * From " +TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery( countQuery,null );
        return cursor.getCount();
    }

    //retrive data from my SQLite Database
    public List<String> getData() {
        List<String> fileName = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from user",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String id=cursor.getString( cursor.getColumnIndex( KEY_ID ) );
                String name = cursor.getString(cursor.getColumnIndex( KEY_NAME ));
                String phn = cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER));

                fileName.add( id );
                fileName.add(name);
                fileName.add( phn );
                cursor.moveToNext();
            }
            db.close();
        }
        return fileName;
    }
}
