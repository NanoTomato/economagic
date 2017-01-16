package com.economagic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "com.economagic.USERNAME";
    public static final String GLOBAL_SETTINGS_FILENAME = "global_settings.xml";

    private static HashSet<String> users = new HashSet<>();

    public static Set<String> getUsers() {
        return users;
    }

    public void addUser(final String userName) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.profiles_layout);
        users.add(userName);
        Button profileButton = new Button(this);
        profileButton.setText(userName);
        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent profilePage = new Intent(MainActivity.this, MainUserActivity.class);
                profilePage.putExtra(USERNAME, userName);
                startActivity(profilePage);
            }
        });
        ll.addView(profileButton, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addUser = (Button) findViewById(R.id.button);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createProfilePage = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(createProfilePage);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(getFilesDir() + CreateUserActivity.USERS_FILE)
            );
            String user = br.readLine();
            while(user != null) {
                String userName = user.substring(0, user.indexOf(';'));
                if (!users.contains(userName)) addUser(userName);
                user = br.readLine();
            }
        } catch (IOException e) {

        }
    }
}
