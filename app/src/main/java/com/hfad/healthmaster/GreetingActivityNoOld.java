package com.hfad.healthmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GreetingActivityNoOld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_no_old);
    }

    public void anxietyAttack(View view){

    }

    public void symptomCheck(View view){

    }

    public void newEntry(View view){

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
