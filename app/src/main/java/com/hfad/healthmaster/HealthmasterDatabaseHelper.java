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
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion == 1){

        }
        if (oldVersion < 3){

        }
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion == 3){

        }
    }

private void updateMyDatabase(SQLiteDatabase db,
                              int oldVersion,
                              int newVersion){
        if (oldVersion < 1) {
            // Create users table
            db.execSQL("CREATE TABLE USERS ("
                    + "UserID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "FirstName VARCHAR(45), "
                    + "LastName VARCHAR(45), "
                    + "Username VARCHAR(10), "
                    + "Password VARCHAR(25));");
            // Create physical health table
            db.execSQL("CREATE TABLE PHYSICALHEALTH ("
                    + "EntryID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DayID Date, "
                    + "UserID INTEGER NOT NULL, "
                    + "ExercID INTEGER DEFAULT NULL, "
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

            // Insert initial data
            insertUser(db, "Annie", "Azul", "aazul", "testAzul");
            insertUser(db, "Betty", "Blue", "beblue", "bluetest");
            insertUser(db, "Casey", "Cyan", "cacyan", "Cyantest");
            insertUser(db, "Danny", "Denim", "dadenim", "dENimTest");

            insertMHealth(db, 0, 1, null);
            insertMHealth(db, 20191201, 1, 1, 5,
                    "Very happy, excited, enthusiastic and motivated", "I got a promotion!");
            insertMHealth(db, 1, 1, "Lamp, tv, fridge, shelf, desk", "Carpet, cat fur, leather, book cover",
                    "Cars, talking from the other room, tapping on a keyboard", "Popcorn, beer",
                    "I'm in my dorm room; it's a bit small, but cozy. I can relax here.");
            insertMHealth(db, 2, 1, 4, "Confused, excited, nervous",
                    "I didn't get the part in the play, but I got a callback");

            insertFood(db, "Lucky Charms", 110.0, 1.0,
                    10.0, 0.0, 55.0, false);
            insertDrink(db, "Coke Zero", 0.0, 0.0, 40.0);

            insertPHealth(db, 1, 20190403);

            insertFoodEntry(db, 1, 20190403, 1);
            insertDrinkEntry(db, 1, 20190403, 1);
        }
        if (oldVersion < 2){
            // section for adding future columns if needed
        }
    }

    private static void insertUser(SQLiteDatabase db,
                                   String fname,
                                   String lname,
                                   String uname,
                                   String pword){
        ContentValues userValues = new ContentValues();
        userValues.put("FirstName", fname);
        userValues.put("LastName", lname);
        userValues.put("Username", uname);
        userValues.put("Password", pword);
        db.insert("USERS", null, userValues);
    }

    private static void insertPHealth(SQLiteDatabase db,
                                      Integer day,
                                      Integer user){
        ContentValues pHealthValues = new ContentValues();
        pHealthValues.put("DayID", day);
        pHealthValues.put("UserID", user);
        db.insert("PHYSICALHEALTH", null, pHealthValues);
    }

    private static void insertPHealth(SQLiteDatabase db,
                                   Integer day,
                                   Integer user,
                                   Integer exerc,
                                   Integer food,
                                   Integer drink,
                                   Double foodQ,
                                   Double drinkQ){
        ContentValues pHealthValues = new ContentValues();
        pHealthValues.put("DayID", day);
        pHealthValues.put("UserID", user);
        pHealthValues.put("ExercID", exerc);
        pHealthValues.put("FoodID", food);
        pHealthValues.put("DrinkID", drink);
        pHealthValues.put("FoodQty", foodQ);
        pHealthValues.put("DrinkQty", drinkQ);
        db.insert("PHYSICALHEALTH", null, pHealthValues);
    }

    private static void insertMHealth(SQLiteDatabase db,
                                       Integer day,
                                       Integer user,
                                       Integer mLevel){
        ContentValues mHealthValues = new ContentValues();
        mHealthValues.put("DayID", day);
        mHealthValues.put("UserID", user);
        mHealthValues.put("MoodLevel", mLevel);
        db.insert("MENTALHEALTH", null, mHealthValues);
    }

    private static void insertMHealth(SQLiteDatabase db,
                                      Integer day,
                                      Integer user,
                                      Integer mood,
                                      Integer mLevel,
                                      String mDesc,
                                      String mTrigger){
        ContentValues mHealthValues = new ContentValues();
        mHealthValues.put("DayID", day);
        mHealthValues.put("UserID", user);
        mHealthValues.put("MoodID", mood);
        mHealthValues.put("MoodLevel", mLevel);
        mHealthValues.put("MoodDesc", mDesc);
        mHealthValues.put("MoodTrigger", mTrigger);
        db.insert("MENTALHEALTH", null, mHealthValues);
    }
    private static void insertMHealth(SQLiteDatabase db,
                                      Integer user,
                                      Integer mood,
                                      Integer mLevel,
                                      String mDesc,
                                      String mTrigger){
        ContentValues mHealthValues = new ContentValues();
        mHealthValues.put("UserID", user);
        mHealthValues.put("MoodID", mood);
        mHealthValues.put("MoodLevel", mLevel);
        mHealthValues.put("MoodDesc", mDesc);
        mHealthValues.put("MoodTrigger", mTrigger);
        db.insert("MENTALHEALTH", null, mHealthValues);
    }

    private static void insertMHealth(SQLiteDatabase db,
                                      Integer user,
                                      Integer anx,
                                      String Lo5,
                                      String T4,
                                      String Li3,
                                      String S2,
                                      String DesSur){
        ContentValues mHealthValues = new ContentValues();
        mHealthValues.put("UserID", user);
        mHealthValues.put("AnxID", anx);
        mHealthValues.put("Lo5", Lo5);
        mHealthValues.put("T4", T4);
        mHealthValues.put("Li3", Li3);
        mHealthValues.put("S2", S2);
        mHealthValues.put("DesSur", DesSur);
        db.insert("MENTALHEALTH", null, mHealthValues);
    }

    private static void insertMHealth(SQLiteDatabase db,
                                      Integer day,
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
                                   String FoodName,
                                   Double FoodCal,
                                   Double FoodFat,
                                   Double FoodSugar,
                                   Double FoodChol,
                                   Double FoodPotas,
                                   Boolean Microwaved){
        ContentValues foodValues = new ContentValues();
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
                                   String DrinkName,
                                   Double DrinkCal,
                                   Double DrinkSugar,
                                   Double DrinkSod){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("DrinkName", DrinkName);
        drinkValues.put("DrinkCal", DrinkCal);
        drinkValues.put("DrinkSugar", DrinkSugar);
        drinkValues.put("DrinkSod", DrinkSod);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertFoodEntry(SQLiteDatabase db,
                                        Integer FoodID,
                                        Integer DayID,
                                        Integer UserID){
        ContentValues fEntryValues = new ContentValues();
        fEntryValues.put("FoodID", FoodID);
        fEntryValues.put("DayID", DayID);
        fEntryValues.put("UserID", UserID);
        db.insert("FOODENTRY", null, fEntryValues);
    }

    private static void insertDrinkEntry(SQLiteDatabase db,
                                        Integer DrinkID,
                                        Integer DayID,
                                        Integer UserID){
        ContentValues dEntryValues = new ContentValues();
        dEntryValues.put("FoodID", DrinkID);
        dEntryValues.put("DayID", DayID);
        dEntryValues.put("UserID", UserID);
        db.insert("DRINKENTRY", null, dEntryValues);
    }
}
