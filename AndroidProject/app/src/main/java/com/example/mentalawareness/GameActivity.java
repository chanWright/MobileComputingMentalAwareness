package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class GameActivity extends AppCompatActivity{
    DrawerLayout dlayout;

    ImageView[] gameObjects = new ImageView[4];
    TextView scoreTV;
    TextView timerTV;
    double spawnTime = 5.0;
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
        for (final ImageView object:gameObjects)
        {
            object.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    onGameObjectClick(object);
                    score += 1;
                    scoreTV.setText("Score: "+score);
                }
            });
        }
        //ObjectAnimator animator = ObjectAnimator.ofFloat(gameObjects[0],"translationX",100f);
        //animator.setDuration(2000);
        //animator.start();
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

    public void fadeAway(ImageView object){
        while(object.getAlpha() > 0){
            object.setAlpha(object.getAlpha() - 0.01f);
        }
        fadeIn(object);
    }
    public void fadeIn(ImageView object){
        double startTime = System.currentTimeMillis()/1000;
        double timer = 0;
        while(timer < spawnTime) {
            timer = (System.currentTimeMillis() / 1000 - startTime);
        }
        if(timer >= spawnTime) {
            while (object.getAlpha() < 1) {
                object.setAlpha(object.getAlpha() + 0.01f);
            }
        }
    }
    public void onGameObjectClick(final ImageView gameObj){
        new Thread(new Runnable(){
            public void run(){
                fadeAway(gameObj);
            }
        }).start();
    }
    // Code for Navigation Ends Here

}