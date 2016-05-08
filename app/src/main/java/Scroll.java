//import android.content.Context;
//import android.os.Handler;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.widget.RelativeLayout;
//import android.widget.ScrollView;
//
//import com.example.hareesh.crazytime.R;
//
//import java.util.Calendar;
//import java.util.Random;
//
///**
// * Created by hareesh on 5/5/16.
// */
//public class Scroll extends ScrollView {
//
//    private ScrollView scrollView;
//    private static Handler mHandler;
//
//
//    public Scroll(Context context, ScrollView scrollView) {
//        super(context);
//
//        this.scrollView = scrollView;
//
//    }
//
//    Runnable mStatusChecker = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                Log.i("10", "interval");
////                RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);
////
////                Calendar c = Calendar.getInstance();
////                long now = c.getTimeInMillis();
////                c.set(Calendar.HOUR_OF_DAY, 0);
////                c.set(Calendar.MINUTE, 0);
////                c.set(Calendar.SECOND, 0);
////                c.set(Calendar.MILLISECOND, 0);
////                long passed = now - c.getTimeInMillis();
////                long secondsPassed = passed / 1000;
////
////                Log.i("image number", "" + secondsPassed);
////
////                Random random = new Random();
////                int r = random.nextInt(5);
////
////                String uri = "@drawable/sequence000" + r;  // where myresource (without the extension) is the file
////
////                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
////
//////                imageview= (ImageView)findViewById(R.id.imageView);
//////                Drawable resImage = getResources().getDrawable(imageResource);
//////                imageView.setImageDrawable(res);
////
////                linearLayout.setBackgroundResource(imageResource);
//
//                //this function can change value of mInterval.
//            } finally {
//                // 100% guarantee that this always happens, even if
//                // your update method throws an exception
//                mHandler.postDelayed(mStatusChecker, 0);
//            }
//        }
//    };
//
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//
//        //handler
//        mHandler = new Handler();
//        startRepeatingTask();
//
//        Log.i("checking scroll", "Scrolling wooohoooooo");
//
//
//        mHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Log.i("10", "interval");
//                    RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relativeLayoutid);
//
//                    Calendar c = Calendar.getInstance();
//                    long now = c.getTimeInMillis();
//                    c.set(Calendar.HOUR_OF_DAY, 0);
//                    c.set(Calendar.MINUTE, 0);
//                    c.set(Calendar.SECOND, 0);
//                    c.set(Calendar.MILLISECOND, 0);
//                    long passed = now - c.getTimeInMillis();
//                    long secondsPassed = passed / 1000;
//
//                    Log.i("image number", "" + secondsPassed);
//
//                    Random random = new Random();
//                    int r = random.nextInt(5);
//
//                    String uri = "@drawable/a000" + r;  // where myresource (without the extension) is the file
//
//                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//                    ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
//                    Log.i("scrollPos", "" + scrollView.getScrollY());
//
////                imageview= (ImageView)findViewById(R.id.imageView);
////                Drawable resImage = getResources().getDrawable(imageResource);
////                imageView.setImageDrawable(res);
//
//                    linearLayout.setBackgroundResource(imageResource);
//
//                    //this function can change value of mInterval.
//                }
//                finally {
//                    // 100% guarantee that this always happens, even if
//                    // your update method throws an exception
//                    mHandler.postDelayed(mStatusChecker, 0);
//                }
//                //wait = false;
//            }
//        }, 0);
//
//
////            float deltaY = e2.getY() - e1.getY();
////            float deltaX = e2.getX() - e1.getX();
////
////            if (Math.abs(deltaX) > Math.abs(deltaY)) {
////                if (Math.abs(deltaX) > SWIPE_THRESHOLD) {
////                    listener.onHorizontalScroll(e2, deltaX);
////                    if (deltaX > 0) {
////                        Log.i("tag", "Slide right");
////                    } else {
////                        Log.i("tag", "Slide left");
////                    }
////                }
////            } else {
////                if (Math.abs(deltaY) > 100) {
////                    listener.onVerticalScroll(e2, deltaY);
////                    if (deltaY > 0) {
////                        Log.i(TAG, "Slide down");
////                    } else {
////                        Log.i(TAG, "Slide up");
////                    }
////                }
////            }
//        return true;
//    }
//
//
//    void startRepeatingTask() {
//        mStatusChecker.run();
//    }
//
//    void stopRepeatingTask() {
//        mHandler.removeCallbacks(mStatusChecker);
//    }
//
//}
//
