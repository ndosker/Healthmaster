package com.hfad.healthmaster;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
    }

    public void anxietyAttack(View view){

    }

    public void symptomCheck(View view){

    }

    public void newEntry(View view){

    }

    public void oldEntries(View view){

    }

    public void accessSettings(View view){
        Intent intent = new Intent(GreetingActivity.this, SettingsActivity.class);
        startActivity(intent);

    }

    public void logOut(View view){
        Intent intent = new Intent(GreetingActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
