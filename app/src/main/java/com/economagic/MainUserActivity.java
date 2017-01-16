package com.economagic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        Intent thisIntent = getIntent();
        String userName = thisIntent.getStringExtra(MainActivity.USERNAME);
        TextView tv = (TextView) findViewById(R.id.hiTextView);
        tv.setText("Hello, " + userName);
    }
}
