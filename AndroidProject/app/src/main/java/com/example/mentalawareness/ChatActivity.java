package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

public class ChatActivity extends AppCompatActivity {
    DrawerLayout dlayout;
    long userID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dlayout = findViewById(R.id.drawer);
        //CometChat
        initCometChat();
        initViews();
    }



    // Chat implementation begins
    private void initCometChat() {
        String appID = "249025b30c2a205"; // Replace with your App ID
        String region = "us"; // Replace with your App Region ("eu" or "us")

        AppSettings appSettings=new AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(region).build();

        CometChat.init(this, appID,appSettings, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
                Log.d("CometChat", "Initialization completed successfully");
            }
            @Override
            public void onError(CometChatException e) {
                Log.d("CometChat", "Initialization failed with exception: " + e.getMessage());
            }
        });
    }

    private void initViews() {
        userID += 12;
        String id = "" + userID;
        String authKey = "91672a90de9bc03025323ad1a744b0ddb9c90afa";
        EditText userNameET = findViewById(R.id.userNameET);
        Button submitBTN = findViewById(R.id.submitBTN);
        User user = new User();
        user.setUid(id);
        user.setName(userNameET.getText().toString());


        submitBTN.setOnClickListener(view -> {
            if (CometChat.getLoggedInUser() == null) {
                CometChat.login(id, authKey, new CometChat.CallbackListener<User>() {

                    @Override
                    public void onSuccess(User user1) {
                        Log.d("Login: ", "Login Successful : " + user1.toString());
                    }

                    @Override
                    public void onError(CometChatException e) {
                        Log.d("Login: ", "Login failed with exception: " + e.getMessage());
                    }
                });
            } else {
                // User already logged in
            }
        });
    }


    // Chat implementation ends

    // Code for Navigation Begins Here
    public void ClickMenu(View v) {
        MainActivity.openDrawer(dlayout);

    }

    public void ClickLogo(View v) {
        MainActivity.closeDrawer(dlayout);
    }

    public void ClickAbout(View v) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAwareness(View v) {
        MainActivity.redirectActivity(this, AwarenessActivity.class);
    }

    public void ClickMaps(View v) {
        MainActivity.redirectActivity(this, MapsActivity.class);
    }

    public void ClickGame(View v) {
        MainActivity.redirectActivity(this, GameActivity.class);
    }

    public void ClickSources(View v) {
        MainActivity.redirectActivity(this, SourcesActivity.class);
    }

    public void ClickCopingMethods(View v) {
        MainActivity.redirectActivity(this, CopingMethodsActivity.class);
    }

    public void ClickChat(View v) {
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(dlayout);
    }
    // Code for Navigation Begins Here

}