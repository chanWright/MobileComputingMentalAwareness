package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class GameActivity extends AppCompatActivity implements Runnable{
    DrawerLayout dlayout;

    ImageView[] gameObjects = new ImageView[4];
    TextView scoreTV;
    TextView timerTV;
    double timer = 0.0;
    double maxTime = 60.0;
    double startTime = 0.0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dlayout = findViewById(R.id.drawer);
        gameObjects[0] = findViewById(R.id.gameObject);
        gameObjects[1] = findViewById(R.id.gameObject1);
        gameObjects[2] = findViewById(R.id.gameObject2);
        gameObjects[3] = findViewById(R.id.gameObject3);
        scoreTV = findViewById(R.id.scoreTextView);
        timerTV = findViewById(R.id.timerTextView);
        ObjectAnimator animator = ObjectAnimator.ofFloat(gameObjects[0],"translationX",100f);
        animator.setDuration(2000);
        animator.start();
        startTime = System.nanoTime() / 1000000;
        run();
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
        recreate();
    }

    public void ClickSources(View v) {
        MainActivity.redirectActivity(this, SourcesActivity.class);
    }

    public void ClickCopingMethods(View v) {
        MainActivity.redirectActivity(this, CopingMethodsActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(dlayout);
    }

    @Override
    public void run() {
        while (timer <= maxTime){
            timer = ((System.nanoTime() / 1000000) - startTime);
            timerTV.setText("Timer: "+(maxTime-timer));
        }
        if (timer >= maxTime){
            for (ImageView object:gameObjects)
            {
                while(object.getAlpha() > 0) {
                    object.setAlpha(object.getAlpha() - 0.1f);
                }
            }
        }
    }
    // Code for Navigation Ends Here

}