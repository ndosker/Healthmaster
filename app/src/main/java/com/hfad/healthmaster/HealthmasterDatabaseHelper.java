package com.hfad.healthmaster;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import java.sql.Date;

public class HealthmasterDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "healthmaster";
    private static final int DB_VERSION = 1;

    HealthmasterDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Create users table
        db.execSQL("CREATE TABLE USERS ("
                + "UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FirstName VARCHAR(45), "
                + "LastName VARCHAR(45));");
        // Create physical health table
        db.execSQL("CREATE TABLE PHYSICALHEALTH ("
                + "EntryID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DayID Date, "
                + "UserID INTEGER NOT NULL, "
                + "ExcerID INTEGER DEFAULT NULL, "
                + "FoodID INTEGER DEFAULT NULL, "
                + "DrinkID INTEGER DEFAULT NULL, "
                + "FoodQty DOUBLE DEFAULT(0), "
                + "DrinkQty DOUBLE DEFAULT(0),"
                + " FOREIGN KEY (UserID) REFERENCES USERS (UserID));");
        // Create mental health table
        db.execSQL("CREATE TABLE MENTALHEALTH ("
                + "EntryID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DayID Date, "
                + "UserID INTEGER NOT NULL, "
                + "AnxID INTEGER, "
                + "MoodID INTEGER, "
                + "Lo5 VARCHAR(1000), "
                + "T4 VARCHAR(1000), "
                + "Li3 VARCHAR(1000), "
                + "S2 VARCHAR(1000), "
                + "DesSur VARCHAR(1000), "
                + "MoodLevel INTEGER, "
                + "MoodDesc VARCHAR(1000), "
                + "MoodTrigger VARCHAR(1000) DEFAULT '', "
                + " FOREIGN KEY (UserID) REFERENCES USERS (UserID));");
        // Create food table
        db.execSQL("CREATE TABLE FOOD ("
                + "FoodID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "FoodName VARCHAR(45),"
                + "FoodCal DOUBLE, "
                + "FoodFat DOUBLE, "
                + "FoodSugar DOUBLE, "
                + "FoodChol DOUBLE, "
                + "FoodPotas DOUBLE, "
                + "Microwaved TINYINT);");
        // Create drink table
        db.execSQL("CREATE TABLE DRINK ("
                + "DrinkID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DrinkName VARCHAR(45),"
                + "DrinkCal DOUBLE, "
                + "DrinkSugar DOUBLE, "
                + "DrinkSod DOUBLE);");
        // Create food entry joining table
        db.execSQL("CREATE TABLE FOODENTRY ("
                + "FoodID INTEGER NOT NULL, "
                + "DayID Date,"
                + "UserID INTEGER NOT NULL, "
                + "CONSTRAINT pk_eatfood PRIMARY KEY(FoodID, DayID, UserID));");
        // Create drink entry joining table
        db.execSQL("CREATE TABLE DRINKENTRY ("
                + "DrinkID INTEGER NOT NULL, "
                + "DayID Date,"
                + "UserID INTEGER NOT NULL, "
                + "CONSTRAINT pk_eatdrink PRIMARY KEY(DrinkID, DayID, UserID));");
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

    private static void insertMHealth(SQLiteDatabase db,
                                      String day,
                                      Integer user,
                                      Integer anx,
                                      Integer mood,
                                      String lo5,
                                      String t4,
                                      String li3,
                                      String s2,
                                      String desSur,
                                      Integer mLevel,
                                      String mDesc,
                                      String mTrigger){
        ContentValues mHealthValues = new ContentValues();
        mHealthValues.put("DayID", day);
        mHealthValues.put("UserID", user);
        mHealthValues.put("AnxID", anx);
        mHealthValues.put("MoodID", mood);
        mHealthValues.put("Lo5", lo5);
        mHealthValues.put("T4", t4);
        mHealthValues.put("Li3", li3);
        mHealthValues.put("S2", s2);
        mHealthValues.put("DesSur", desSur);
        mHealthValues.put("MoodLevel", mLevel);
        mHealthValues.put("MoodDesc", mDesc);
        mHealthValues.put("MoodTrigger", mTrigger);
        db.insert("MENTALHEALTH", null, mHealthValues);
    }

    private static void insertFood(SQLiteDatabase db,
                                   Integer FoodID,
                                   String FoodName,
                                   Double FoodCal,
                                   Double FoodFat,
                                   Double FoodSugar,
                                   Double FoodChol,
                                   Double FoodPotas,
                                   Boolean Microwaved){
        ContentValues foodValues = new ContentValues();
        foodValues.put("FoodID", FoodID);
        foodValues.put("FoodName", FoodName);
        foodValues.put("FoodCal", FoodCal);
        foodValues.put("FoodFat", FoodFat);
        foodValues.put("FoodSugar", FoodSugar);
        foodValues.put("FoodChol", FoodChol);
        foodValues.put("FoodPotas", FoodPotas);
        foodValues.put("Microwaved", Microwaved);
        db.insert("FOOD", null, foodValues);
    }

    private static void insertDrink(SQLiteDatabase db,
                                   Integer DrinkID,
                                   String DrinkName,
                                   Double DrinkCal,
                                   Double DrinkSugar,
                                   Double DrinkSod){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("DrinkID", DrinkID);
        drinkValues.put("DrinkName", DrinkName);
        drinkValues.put("DrinkCal", DrinkCal);
        drinkValues.put("DrinkSugar", DrinkSugar);
        drinkValues.put("DrinkSod", DrinkSod);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertFoodEntry(SQLiteDatabase db,
                                        Integer FoodID,
                                        String DayID,
                                        Integer UserID){
        ContentValues fEntryValues = new ContentValues();
        fEntryValues.put("FoodID", FoodID);
        fEntryValues.put("DayID", DayID);
        fEntryValues.put("UserID", UserID);
        db.insert("FOODENTRY", null, fEntryValues);
    }

    private static void insertDrinkEntry(SQLiteDatabase db,
                                        Integer DrinkID,
                                        String DayID,
                                        Integer UserID){
        ContentValues dEntryValues = new ContentValues();
        dEntryValues.put("FoodID", DrinkID);
        dEntryValues.put("DayID", DayID);
        dEntryValues.put("UserID", UserID);
        db.insert("DRINKENTRY", null, dEntryValues);
    }
}
