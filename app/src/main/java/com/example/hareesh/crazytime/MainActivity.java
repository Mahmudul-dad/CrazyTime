package com.example.hareesh.crazytime;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewTreeObserver;

import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GestureDetectorCompat mDetector;

    //running event repeatedly
    private int mInterval = 10000; // 10 seconds by default, can be changed later
    private Handler mHandler;

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
                Log.i("10", "interval");
//                RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);
//
//                Calendar c = Calendar.getInstance();
//                long now = c.getTimeInMillis();
//                c.set(Calendar.HOUR_OF_DAY, 0);
//                c.set(Calendar.MINUTE, 0);
//                c.set(Calendar.SECOND, 0);
//                c.set(Calendar.MILLISECOND, 0);
//                long passed = now - c.getTimeInMillis();
//                long secondsPassed = passed / 1000;
//
//                Log.i("image number", "" + secondsPassed);
//
//                Random random = new Random();
//                int r = random.nextInt(5);
//
//                String uri = "@drawable/sequence000" + r;  // where myresource (without the extension) is the file
//
//                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
////                imageview= (ImageView)findViewById(R.id.imageView);
////                Drawable resImage = getResources().getDrawable(imageResource);
////                imageView.setImageDrawable(res);
//
//                linearLayout.setBackgroundResource(imageResource);

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
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
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

            Log.i(DEBUG_TAG, "Scrolling wooohoooooo");

            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    try {
                        Log.i("10", "interval");
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

                        String uri = "@drawable/sequence000" + r;  // where myresource (without the extension) is the file

                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
                        Log.i("scrollPos", "" + scrollView.getScrollY());

//                imageview= (ImageView)findViewById(R.id.imageView);
//                Drawable resImage = getResources().getDrawable(imageResource);
//                imageView.setImageDrawable(res);

                        linearLayout.setBackgroundResource(imageResource);

                        //this function can change value of mInterval.
                    }
                    finally {
                        // 100% guarantee that this always happens, even if
                        // your update method throws an exception
                        mHandler.postDelayed(mStatusChecker, 0);
                    }
                    //wait = false;
                }
            }, 0);


//            float deltaY = e2.getY() - e1.getY();
//            float deltaX = e2.getX() - e1.getX();
//
//            if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                if (Math.abs(deltaX) > SWIPE_THRESHOLD) {
//                    listener.onHorizontalScroll(e2, deltaX);
//                    if (deltaX > 0) {
//                        Log.i("tag", "Slide right");
//                    } else {
//                        Log.i("tag", "Slide left");
//                    }
//                }
//            } else {
//                if (Math.abs(deltaY) > 100) {
//                    listener.onVerticalScroll(e2, deltaY);
//                    if (deltaY > 0) {
//                        Log.i(TAG, "Slide down");
//                    } else {
//                        Log.i(TAG, "Slide up");
//                    }
//                }
//            }
            return true;
        }

    }

//    @Override
//    public void onScrollChanged(){
//
//        Log.i("scrollPos", "");
//        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
//
//        int scrollX = scrollView.getScrollX(); //for horizontalScrollView
//        int scrollY = scrollView.getScrollY(); //for verticalScrollView
//
//        Log.i("scrollPos", "" + scrollY);
//        //DO SOMETHING WITH THE SCROLL COORDINATES
//
//    }

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


//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width,
//                               int height) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        // TODO Auto-generated method stub
//
//    }
}
