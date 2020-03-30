package com.hfad.healthmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GreetingActivityNoOld extends AppCompatActivity {

    public static Integer CURR_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_no_old);
    }

    public void anxietyAttack(View view){
        Intent intent = new Intent(GreetingActivityNoOld.this, AnxietyActivity.class);
        startActivity(intent);
    }

    public void symptomCheck(View view){
        //Intent intent = new Intent(GreetingActivityNoOld.this, SymptomsActivity.class);
        //startActivity(intent);
    }

    public void newEntry(View view){
        Intent intent = new Intent(GreetingActivityNoOld.this, NewEntryActivity.class);
        startActivity(intent);
    }

    public void accessSettings(View view){
        Intent intent = new Intent(GreetingActivityNoOld.this, SettingsActivity.class);
        startActivity(intent);

    }

    public void logOut(View view){
        Intent intent = new Intent(GreetingActivityNoOld.this, MainActivity.class);
        startActivity(intent);
    }
}
