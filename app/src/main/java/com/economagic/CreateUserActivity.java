package com.economagic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.FileWriter;

public class CreateUserActivity extends AppCompatActivity {

    public static final String USERS_FILE = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Button createButton = (Button) findViewById(R.id.create_user_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });
    }

    public void createNewUser() {
        EditText nameInput = (EditText) findViewById(R.id.new_user_name);
        EditText passInput = (EditText) findViewById(R.id.new_user_pass);
        if (MainActivity.getUsers().contains(nameInput.getText().toString())) {
            nameInput.setError("User with given name exists.");
            nameInput.requestFocus();
            return;
        }
        try {
            FileWriter fw = new FileWriter(getFilesDir() + USERS_FILE, true);
            fw.append(nameInput.getText().toString() + ';' + passInput.getText().toString() + '\n');
            fw.close();
            onBackPressed();
        } catch (IOException e) {}
    }
}
