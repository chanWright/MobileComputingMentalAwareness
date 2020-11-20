package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class SourcesActivity extends AppCompatActivity {

    DrawerLayout dlayout;
    private RecyclerView sourseRV = null;
    private SourcesModel myModel = null;
    private SourceAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        dlayout = findViewById(R.id.drawer);

        myModel = SourcesModel.getModel();
        myAdapter = new SourceAdapter();

        sourseRV = findViewById(R.id.sourceRV);
        sourseRV.setAdapter(myAdapter);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        sourseRV.setLayoutManager(lin);
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
        recreate();
    }

    public void ClickCopingMethods(View v) {
        MainActivity.redirectActivity(this, CopingMethodsActivity.class);
    }

    public void ClickChat(View v) {
        MainActivity.redirectActivity(this, ChatActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(dlayout);
    }
    // Code for Navigation Begins Here

}