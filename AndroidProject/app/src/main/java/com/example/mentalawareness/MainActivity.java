package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;

//import com.cometchat.pro.core.AppSettings;
//import com.cometchat.pro.core.CometChat;
//import com.cometchat.pro.exceptions.CometChatException;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dlayout = findViewById(R.id.drawer);

    }

    // Code for Navigation Begins Here
    public void ClickMenu (View v) {
        openDrawer(dlayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View v) {
        closeDrawer(dlayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickAbout(View v) {
        recreate();
    }
    // Redirect to new activity
    public void ClickAwareness(View v) {
        //Go to Awareness Activiy
        redirectActivity(this, AwarenessActivity.class);
    }

    public void ClickMaps(View v) {
        //Go to MapsActivity
        redirectActivity(this, MapsActivity.class);
    }

    public void ClickGame(View v) {
        //Go to Game Activity
        redirectActivity(this, GameActivity.class);
    }

    public void ClickSources(View v) {
        //Go to Sources Activity
        redirectActivity(this,SourcesActivity.class);
    }

    public void ClickCopingMethods(View v) {
        // Go to CopingMethods Activity
        redirectActivity(this, CopingMethodsActivity.class);
    }

    public void ClickChat(View v) {
        //Go to Chat Activity
        MainActivity.redirectActivity(this, ChatActivity.class);
    }

    /*Function takes in an Activity and class
    * Creates an Intent for the activity
    * Starts activity*/
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize Intent
        Intent ini = new Intent(activity, aClass);
        //Set Flags
        ini.setFlags(ini.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(ini);
    }

    protected void onPause() {
        super.onPause();
        closeDrawer(dlayout);
    }

    // Code for Navigation Ends Here

}