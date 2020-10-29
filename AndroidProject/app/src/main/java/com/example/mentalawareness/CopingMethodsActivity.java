package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class CopingMethodsActivity extends AppCompatActivity {

    DrawerLayout dlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coping_methods);

        dlayout = findViewById(R.id.drawer);

    }

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
        recreate();
    }

    public void ClickChat(View v) {
        MainActivity.redirectActivity(this, ChatActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(dlayout);
    }
    // Code for Navigation Ends Here

}