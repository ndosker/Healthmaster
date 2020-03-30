package com.hfad.healthmaster;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkUser(View view){
        SQLiteOpenHelper healthmasterDatabaseHelper = new HealthmasterDatabaseHelper(this);
        EditText promptUsername = findViewById(R.id.username);
        EditText promptPassword = findViewById(R.id.password);
        try {
            SQLiteDatabase db = healthmasterDatabaseHelper.getWritableDatabase();
            String username = promptUsername.getText().toString();
            String password = promptPassword.getText().toString();
            checkUser(db, username, password);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void checkUser(SQLiteDatabase db, String username, String password){
        String qString = "Username = " + '"' + username + '"' + " AND Password = " + '"' + password + '"';
        Cursor cursor = db.query("USERS",
                new String[] {"Username", "Password"},
                qString, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.close();
                cursor = db.query("USERS",
                        new String[] {"UserID"},
                        "Username = '" + username + "'",
                        null, null, null, null);
                cursor.moveToFirst();
                Integer userID = cursor.getInt(0);
                GreetingActivityNoOld.CURR_USER = userID;
                GreetingActivity.CURR_USER = userID;
                System.out.println(userID);
                Integer totalEntries = 0;
                cursor.close();
                cursor = db.query("MENTALHEALTH",
                            new String[]{"EntryID"},
                            "UserID = " + userID,
                            null, null, null, null);
                totalEntries += cursor.getCount();
                cursor.close();
                cursor = db.query("PHYSICALHEALTH",
                            new String[]{"EntryID"},
                            "UserID = " + userID,
                            null, null, null, null);
                totalEntries += cursor.getCount();
                cursor.close();
                System.out.println(totalEntries);
                if (totalEntries > 0) {
                    Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, GreetingActivityNoOld.class);
                    startActivity(intent);
                }
            }
        cursor.close();
    }
}