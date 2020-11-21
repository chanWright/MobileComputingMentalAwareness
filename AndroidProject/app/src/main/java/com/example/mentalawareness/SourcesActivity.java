package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SourcesActivity extends AppCompatActivity {

    DrawerLayout dlayout;
    private RecyclerView sourseRV = null;
    private SourcesModel myModel = null;
    private SourceAdapter myAdapter;
    private GestureDetectorCompat detector = null;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = sourseRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = sourseRV.getChildViewHolder(view);
                if (holder instanceof SourceAdapter.SourceViewHolder) {
                    int position = holder.getAdapterPosition();
                    // handle single tap
                    Log.d("click", "clicked on item "+ position);
                    Log.d("click", "name of item was" + myModel.myCite.get(position).link);

                    Intent implicict = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(myModel.myCite.get(position).link);
                    implicict.setData(uri);
                    startActivity(implicict);

                    return true; // Use up the tap gesture
                }
            }
            // we didn't handle the gesture so pass it on
            return false;
        }
    }

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

        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        sourseRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });
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