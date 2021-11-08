package com.jarves.expense_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "registered";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";
    private static final String PWD = "pwd";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + MOBILE + " TEXT,"
                + PWD + " TEXT,"
                + EMAIL + " TEXT)";
        db.execSQL(query);
    }

    public void adduser(String name, String mobile, String email, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(MOBILE, mobile);
        values.put(EMAIL, email);
        values.put(PWD, pwd);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkuser(String name, String pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name","pwd"};
        String whereClause = "name='"+name+"' and pwd='"+pwd+"'";
        Cursor c = db.query(TABLE_NAME,columns, whereClause, null, null, null,null,null);
        if ((c != null) && (c.getCount() > 0)){
            c.close();
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
