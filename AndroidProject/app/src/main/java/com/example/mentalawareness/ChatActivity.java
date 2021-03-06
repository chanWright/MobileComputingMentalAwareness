package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.example.mentalawareness.Chat_Activities.GroupListActivity;
import com.example.mentalawareness.Chat_Activities.Login_Fragment;
import com.example.mentalawareness.Chat_Activities.SignUp_Fragment;

public class ChatActivity extends AppCompatActivity implements Login_Fragment.Login_Responder, SignUp_Fragment.SignUp_Responder {
    DrawerLayout dlayout;
    String authKey = "237640375aca36f6683cffdefdc4db833c241bce";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dlayout = findViewById(R.id.drawer);
        //CometChat
        initCometChat();

        //logout any user logined before
        logoutPrevUser();


        //Set up Fragments
        Login_Fragment loginFRG = Login_Fragment.newInstance();
        SignUp_Fragment signUpFRG = SignUp_Fragment.newInstance();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.fragment_container, loginFRG, "LOGIN_FRG");
        trans.add(R.id.fragment_container, signUpFRG, "SIGNUP_FRG");
        trans.hide(signUpFRG);

        trans.commit();

        //Set up Logout on click listener
        Button logoutBTN = findViewById(R.id.logoutBTN);
        logoutBTN.setOnClickListener(view -> {
            logoutPrevUser();
        });



    }

    private void logoutPrevUser() {
        //String user = CometChat.getLoggedInUser().getName();
        CometChat.logout(new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
                //Toast.makeText(getApplicationContext(), user + " Logged Out Sucessfully!", Toast.LENGTH_SHORT).show();
                Log.d("Logout: ", "Logout completed successfully");
            }
            @Override
            public void onError(CometChatException e) {
                Log.d("Logout: ", "Logout failed with exception: " + e.getMessage());
            }
        });
    }


    // Chat implementation begins
    private void initCometChat() {
        String appID = "26786408a2a5388"; // Replace with your App ID
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



    public void CreateUser(String userID, String userName) {
        //Create User
        user = new User();
        user.setUid(userID);
        user.setName(userName);
        CometChat.createUser(user, authKey, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                Log.d("createUser", user.toString());
            }

            @Override
            public void onError(CometChatException e) {
                Log.e("createUser", e.getMessage());
                Log.e("Error!!! : ", e.getDetails());
            }
        });
    }

    public void swapToLogin() {
        FragmentManager fm = getSupportFragmentManager();
        SignUp_Fragment sf = (SignUp_Fragment) fm.findFragmentByTag("SIGNUP_FRG");
        Login_Fragment lf = (Login_Fragment) fm.findFragmentByTag("LOGIN_FRG");

        FragmentTransaction trans = fm.beginTransaction();
        trans.hide(sf);
        trans.show(lf);
        trans.commit();
    }


    public void loginUser(String UID) {
        //EditText userIDET = findViewById(R.id.userIDET);
        //String id = userIDET.getText().toString();
        if (CometChat.getLoggedInUser() == null) {
            CometChat.login(UID, authKey, new CometChat.CallbackListener<User>() {
                @Override
                public void onSuccess(User user1) {
                    Log.d("Login: ", "Login Successful : " + user1.toString());
                    redirectToChatPage();
                }

                @Override
                public void onError(CometChatException e) {
                    Log.d("Login: ", "Login failed with exception: " + e.getMessage());
                    Toast.makeText(getApplicationContext(),"Please enter a Valid User ID",Toast.LENGTH_LONG).show();
                }
            });
        } else {
            // User already logged in
            redirectToChatPage();
        }
    }

    public void swapToSignUp() {
        FragmentManager fm = getSupportFragmentManager();
        SignUp_Fragment sf = (SignUp_Fragment) fm.findFragmentByTag("SIGNUP_FRG");
        Login_Fragment lf = (Login_Fragment) fm.findFragmentByTag("LOGIN_FRG");

        FragmentTransaction trans = fm.beginTransaction();
        trans.hide(lf);
        trans.show(sf);
        trans.commit();
    }

    private void redirectToChatPage() {
        GroupListActivity.start(this);
    }


    // Chat implementation ends

    // Code for Navigation Begins Here
    public void ClickMenu(View v) {
        MainActivity.openDrawer(dlayout);

    }

    public void ClickLogo(View v) {
        MainActivity.closeDrawer(dlayout);
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