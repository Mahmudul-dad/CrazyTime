package com.example.hareesh.crazytime;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity   {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GestureDetectorCompat mDetector;

    //running event repeatedly
    private int mInterval = 10000; // 10 seconds by default, can be changed later
    private Handler mHandler;

    private VelocityTracker mVelocityTracker = null;

    private Integer images[] = {R.drawable.sequence0000, R.drawable.sequence0001, R.drawable.sequence0002};


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get current time
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        // Now we display formattedDate value in TextView
        TextView texttime = (TextView) findViewById(R.id.textTime);
        texttime.setText("Current Date and Time : " + formattedDate);
        texttime.setGravity(Gravity.CENTER);
        texttime.setTextSize(20);
        //setContentView(txtView);

        //listener for scroll action
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        //handler
        mHandler = new Handler();
        startRepeatingTask();

        //gallery of images to scroll
        addImagesToThegallery();
    }

    private void addImagesToThegallery() {
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.linLayout2);
        for (Integer image : images) {
            imageGallery.addView(getImageView(image));
        }
    }

    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 50, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);

                Calendar c = Calendar.getInstance();
                long now = c.getTimeInMillis();
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                long passed = now - c.getTimeInMillis();
                long secondsPassed = passed / 1000;

                Log.i("image number", "" + secondsPassed);

                Random random = new Random();
                int r = random.nextInt(5);

                String uri = "@drawable/sequence000" + r;  // where myresource (without the extension) is the file

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

//                imageview= (ImageView)findViewById(R.id.imageView);
//                Drawable resImage = getResources().getDrawable(imageResource);
//                imageView.setImageDrawable(res);

                relativeLayout.setBackgroundResource(imageResource);

                 //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);

        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                Log.d("", "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(mVelocityTracker,
                                pointerId));
                float Xvel = VelocityTrackerCompat.getXVelocity(mVelocityTracker,pointerId);
                float YVel = VelocityTrackerCompat.getYVelocity(mVelocityTracker,pointerId);


                Log.d("", "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                                pointerId));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                mVelocityTracker = null;
                break;
        }
        return true;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Scroller mScroller = new Scroller(getApplicationContext());

            Log.i(DEBUG_TAG, "Scrolling wooohoooooo");

            if (mScroller.computeScrollOffset()) {
                // Get current x and y positions
                int currX = mScroller.getCurrX();
                int currY = mScroller.getCurrY();

                Log.i("x pos", "" + currX);
                Log.i("y pos", "" + currY);
            } else {
                Log.i("no pos", "no pos");
            }

            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    try {
                        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);

                        Calendar c = Calendar.getInstance();
                        long now = c.getTimeInMillis();
                        c.set(Calendar.HOUR_OF_DAY, 0);
                        c.set(Calendar.MINUTE, 0);
                        c.set(Calendar.SECOND, 0);
                        c.set(Calendar.MILLISECOND, 0);
                        long passed = now - c.getTimeInMillis();
                        long secondsPassed = passed / 1000;

                        Log.i("image number", "" + secondsPassed);

                        Random random = new Random();
                        int r = random.nextInt(5);

                        String uri = "@drawable/a000" + r;  // where myresource (without the extension) is the file

                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                        linearLayout.setBackgroundResource(imageResource);
                    }
                    finally {
                        // 100% guarantee that this always happens, even if
                        // your update method throws an exception
                        mHandler.postDelayed(mStatusChecker, 0);
                    }
                }
            }, 0);
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
