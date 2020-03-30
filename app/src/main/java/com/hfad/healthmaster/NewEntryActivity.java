package com.hfad.healthmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
    }

    public void Submit(View view){
        Intent intent = new Intent(NewEntryActivity.this, GreetingActivity.class);
        startActivity(intent);
    }
}
