package com.jarves.expense_manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jarves.expense_manager.class_components.Task;
import com.jarves.expense_manager.class_components.Date;
import com.jarves.expense_manager.class_components.Time;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "ExpenseDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Tasks";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String CATEGORY_COL = "category";
    private static final String AMOUNT_COL = "amount";
    private static final String DATE_DAY_COL = "day";
    private static final String DATE_MONTH_COL = "month";
    private static final String DATE_YEAR_COL = "year";
    private static final String TIME_HOUR_COL = "hour";
    private static final String TIME_MINUTE_COL = "minute";
    private static final String COMPLETED_COL = "completed";

    public Database(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + CATEGORY_COL + " TEXT,"
                + AMOUNT_COL + " TEXT,"
                + DATE_DAY_COL + " TEXT,"
                + DATE_MONTH_COL + " TEXT,"
                + DATE_YEAR_COL + " TEXT,"
                + TIME_HOUR_COL + " TEXT,"
                + TIME_MINUTE_COL + " TEXT,"
                + COMPLETED_COL + " TEXT);";
        database.execSQL(query);
    }
    public void addNewTask(Task task){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //adding values
        values.put(NAME_COL,task.getName());
        values.put(CATEGORY_COL,task.getCategory());
        values.put(AMOUNT_COL,task.getAmount());
        values.put(DATE_DAY_COL,task.getDate().getDay());
        values.put(DATE_MONTH_COL,task.getDate().getMonth());
        values.put(DATE_YEAR_COL,task.getDate().getYear());
        values.put(TIME_HOUR_COL,task.getTime().getHour());
        values.put(TIME_MINUTE_COL,task.getTime().getMinute());
        values.put(COMPLETED_COL,Boolean.toString(task.isComplete()));

        database.insert(TABLE_NAME,null,values);
        database.close();
    }
    public ArrayList<Task> getTasks(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Date date = new Date(Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)));


                Time time = new Time(Integer.parseInt(cursor.getString(7)),
                        Integer.parseInt(cursor.getString(8)));

                Task task = new Task(cursor.getString(1),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(2),
                        date,
                        time);
                task.setComplete(Boolean.parseBoolean(cursor.getString(9)));
                tasks.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    public void deleteTask(String name){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_NAME,"name=?", new String[]{name});
        database.close();
    }
    public ArrayList<Task> getTasksCompleted(){
        ArrayList<Task> tasks = getTasks();
        ArrayList<Task> completedTasks = new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(task.isComplete()){
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }
    public ArrayList<Task> getTasksPending(){
        ArrayList<Task> tasks = getTasks();
        ArrayList<Task> pendingTasks = new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(!task.isComplete()){
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
