package com.hfad.healthmaster;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OldEntriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_entries);
        SQLiteOpenHelper healthmasterDatabaseHelper = new HealthmasterDatabaseHelper(this);
        TextView entries = findViewById(R.id.old_entries);
        System.out.println(entries.getText().toString());
        try {
            SQLiteDatabase db = healthmasterDatabaseHelper.getWritableDatabase();
            Integer userID = GreetingActivity.CURR_USER;
            Integer currColumn = 0;
            String currString = "";
                    Cursor cursor = db.query("MENTALHEALTH",
                    new String[] {"DayID",
                            "AnxID",
                            "MoodID",
                            "Lo5",
                            "T4",
                            "Li3",
                            "S2",
                            "DesSur",
                            "MoodLevel",
                            "MoodDesc",
                            "MoodTrigger"}, "UserID = " + userID,
                    null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isLast()) {
               while (currColumn < cursor.getColumnCount()) {
                   try {
                       currString = cursor.getString(currColumn);
                       if (!currString.isEmpty()) {
                           System.out.println(currString);
                           currString = cursor.getColumnName(currColumn) + ": " + currString;
                           if (!currString.equals("DayID: 0")) {
                               entries.append(currString + "\n");
                           }
                       }
                   }
                   catch (NullPointerException e) {
                       System.out.println(cursor.getColumnName(currColumn) + " has no value.");
                   }
                   currColumn += 1;
               }
               currColumn = 0;
               entries.append("\n");
               cursor.moveToNext();
            }
            cursor.close();
            /*cursor = db.query("PHYSICALHEALTH",
                    new String[] {"DayID",
                            "ExercID",
                            "FoodID",
                            "DrinkID",
                            "FoodQty",
                            "DrinkQty"}, "UserID = " + userID,
                    null, null, null, null);
            System.out.println(cursor.getCount());
            currColumn = 1;
            while (currColumn < cursor.getColumnCount()) {
                fetchFromSubTables(cursor, currColumn);
                currColumn += 1;
            }
            cursor.close();*/
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void goBack(View view){
        super.onBackPressed();
    }

    private void fetchFromSubTables(Cursor c, Integer col){
    }
}
