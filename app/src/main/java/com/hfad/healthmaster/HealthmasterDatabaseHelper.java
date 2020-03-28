package com.hfad.healthmaster;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class HealthmasterDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "healthmaster";
    private static final int DB_VERSION = 1;

    HealthmasterDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE USERS ("
                + "UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FirstName VARCHAR(45), "
                + "LastName VARCHAR(45));");
        db.execSQL("CREATE TABLE PHYSICALHEALTH ("
                + "EntryID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DayID Date, "
                + "UserID INTEGER NOT NULL, "
                + "ExcerID INTEGER DEFAULT NULL, "
                + "FoodID INTEGER DEFAULT NULL, "
                + "DrinkID INTEGER DEFAULT NULL, "
                + "FoodQty DOUBLE DEFAULT(0), "
                + "DrinkQty DOUBLE DEFAULT(0));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    private static void insertUser(SQLiteDatabase db,
                                   String fname,
                                   String lname){
        ContentValues userValues = new ContentValues();
        userValues.put("FirstName", fname);
        userValues.put("FirstName", lname);
        db.insert("USERS", null, userValues);
    }

    private static void insertPHealth(SQLiteDatabase db,
                                   String day,
                                   Integer user,
                                   Integer excer,
                                   Integer food,
                                   Integer drink,
                                   Double foodQ,
                                   Double drinkQ){
        ContentValues pHealthValues = new ContentValues();
        pHealthValues.put("DayID", day);
        pHealthValues.put("UserID", user);
        pHealthValues.put("ExcerID", excer);
        pHealthValues.put("FoodID", food);
        pHealthValues.put("DrinkID", drink);
        pHealthValues.put("FoodQty", foodQ);
        pHealthValues.put("DrinkQty", drinkQ);
        db.insert("PHYSICALHEALTH", null, pHealthValues);
    }
}
