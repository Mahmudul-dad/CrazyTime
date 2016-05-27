package com.example.hareesh.crazytime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hareesh.crazytime.util.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends Activity {

    private Handler mHandler;
    private GestureDetectorCompat mDetector;
    private float velocity;
    private String placeName;
    private VelocityTracker mVelocityTracker = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        placeName = intent.getStringExtra("placeName");

        showTextView(R.id.textTime, getTimeZoneTime(getTimeZoneCalendar(placeName)), Constants.TIME_FONT_SIZE);
        showTextView(R.id.textdate, getTimeZoneDate(getTimeZoneCalendar(placeName)), Constants.DATE_FONT_SIZE);
        showTextView(R.id.textplace, placeName, Constants.PLACE_FONT_SIZE);

        //listener for scroll action
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        //handler for runnable
        mHandler = new Handler();
        startRepeatingTask();
    }

    private Calendar getTimeZoneCalendar(String placeName) {
        if (placeName == Constants.DUBAI) {
            return Calendar.getInstance(TimeZone.getTimeZone(Constants.DUBAI_TIME_ZONE));
        } else if (placeName == Constants.LONDON) {
            return Calendar.getInstance(TimeZone.getTimeZone(Constants.LONDON_TIME_ZONE));
        } else if (placeName == Constants.PHOENIX) {
            return Calendar.getInstance(TimeZone.getTimeZone(Constants.PHOENIX_TIME_ZONE));
        } else {
            return Calendar.getInstance();
        }
    }

    private String getTimeZoneTime(Calendar timeZoneCalendar) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(timeZoneCalendar.getTime());
    }

    private String getTimeZoneDate(Calendar timeZoneCalendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d EEE");
        return dateFormat.format(timeZoneCalendar.getTime());
    }

    private void showTextView(int textViewID, String text, int textSize) {
        TextView textView = (TextView) findViewById(textViewID);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(textSize);
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {

                showTextView(R.id.textTime, getTimeZoneTime(getTimeZoneCalendar(placeName)), Constants.TIME_FONT_SIZE);
                showTextView(R.id.textdate, getTimeZoneDate(getTimeZoneCalendar(placeName)), Constants.DATE_FONT_SIZE);
                showTextView(R.id.textplace, placeName, Constants.PLACE_FONT_SIZE);

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);

                Calendar cal = Calendar.getInstance();

                int imageResource = getResources().getIdentifier(getImageUri(getImageNumber(cal)), null, getPackageName());

                relativeLayout.setBackgroundResource(imageResource);

                //this function can change value of TIME_INTERVAL.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, Constants.TIME_INTERVAL);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void delayRepeatingTask() {
        mHandler.postDelayed(mStatusChecker, 3000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.mDetector.onTouchEvent(event);

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
//                Log.d("", "X velocity: " +
//                        VelocityTrackerCompat.getXVelocity(mVelocityTracker,
//                                pointerId));

                velocity = VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId);

//                Log.d("", "Y velocity: " +
//                        VelocityTrackerCompat.getYVelocity(mVelocityTracker,
//                                pointerId));

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

        private Handler scrollHandler = new Handler();

        Runnable mScrollChecker = new Runnable() {
            @Override
            public void run() {
                try {
                    RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);

                    Calendar cal = Calendar.getInstance();
                    cal.getTime();

                    int imageNumber = getImageNumber(addTime(cal, velocity));

                    if(imageNumber < 1440) {
                        int imageResource = getResources().getIdentifier(getImageUri(imageNumber), null, getPackageName());
                        linearLayout.setBackgroundResource(imageResource);
                    }

                } finally {
                    // 100% guarantee that this always happens, even if
                    // your update method throws an exception
                    mHandler.postDelayed(mStatusChecker, 3000);
                    scrollHandler.postDelayed(mScrollChecker, 0);
                }
            }
        };

        void startScrollRepeatingTask() {
            mScrollChecker.run();
        }

        void stopScrollRepeatingTask() {
            scrollHandler.removeCallbacks(mScrollChecker);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            stopRepeatingTask();
            startScrollRepeatingTask();
            stopScrollRepeatingTask();
            delayRepeatingTask();
            startRepeatingTask();
            return true;
        }

    }

    public String getFormattedTime(Calendar cal) {
        DateFormat dateFormat = new SimpleDateFormat("MMM d EEE HH:mm");
        return dateFormat.format(cal.getTime());
    }

    public Calendar addTime(Calendar cal, float velocity) {
        int numberOfMinutes = (int) velocity;

        if (velocity > 0) {
            cal.add(Calendar.MINUTE, -numberOfMinutes);
            Log.i("time down", "" + getFormattedTime(cal));
            return cal;

        } else if (velocity < 0) {
            cal.add(Calendar.MINUTE, -numberOfMinutes);
            Log.i("time up", "" + getFormattedTime(cal));
            return cal;
        }
        return cal;
    }

    public int getImageNumber(Calendar cal) {
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int am = cal.get(Calendar.AM_PM);

        int imageNumber = 0;

        if( am == 0 ) {
            imageNumber = (hour * 60 ) + minute;
            Log.i("for am image scroll", "" + imageNumber);
        } else if( am == 1) {
            imageNumber = (hour + 12 ) * 60 + minute;
            Log.i("for pm image scroll", "" + imageNumber);

        }

        return imageNumber;
    }

    public String getImageUri(int imageNumber) {
        int length = (int) Math.log10(imageNumber) + 1;
        String uri = null;

        if(length == 1) {
            uri = "@drawable/a_000" + imageNumber;
        } else if(length == 2){
            uri = "@drawable/a_00" + imageNumber;
        } else if(length == 3){
            uri = "@drawable/a_0" + imageNumber;
        } else  if(length == 4){
            uri = "@drawable/a_" + imageNumber;
        }

        return uri;
    }

}
