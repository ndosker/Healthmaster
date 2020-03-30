package com.hfad.healthmaster;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AnxietyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety);
    }

    public void Submit(View view){
        SQLiteOpenHelper healthmasterDatabaseHelper = new HealthmasterDatabaseHelper(this);
        EditText promptLook = findViewById(R.id.look_five);
        EditText promptTouch = findViewById(R.id.touch_four);
        EditText promptListen = findViewById(R.id.listen_3);
        EditText promptSmell = findViewById(R.id.smell_2);
        EditText promptDescribe = findViewById(R.id.desc_sur);

        String look = promptLook.getText().toString();
        String touch = promptTouch.getText().toString();
        String listen = promptListen.getText().toString();
        String smell = promptSmell.getText().toString();
        String descrip = promptDescribe.getText().toString();

        try {
            SQLiteDatabase db = healthmasterDatabaseHelper.getWritableDatabase();
            if (look != "" && touch != "" && listen != "" && smell != "" && descrip != "") {
                ((HealthmasterDatabaseHelper) healthmasterDatabaseHelper).insertMHealth(db,
                        GreetingActivity.CURR_USER,
                        look, touch, listen, smell, descrip);
                Intent intent = new Intent(AnxietyActivity.this, GreetingActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(this,
                        "All fields must have text!",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
