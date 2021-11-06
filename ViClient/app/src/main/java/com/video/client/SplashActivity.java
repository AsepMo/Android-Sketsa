package com.video.client;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import com.video.client.application.Application;
import com.video.client.application.ApplicationActivity;
import com.video.client.engine.app.folders.FolderMe;
import com.video.client.engine.app.permissions.PermissionsResultAction;
import com.video.client.engine.app.models.VideoData;
import com.video.client.engine.app.settings.Settings;
import com.video.client.engine.widget.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    public static String TAG = SplashActivity.class.getSimpleName();
    public static String ACTION_RESTART_APPLICATION = "RESTART";
    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setAction(ACTION_RESTART_APPLICATION);
        context.startActivity(intent);
	}
    private Application mApplication;
    private SplashScreen mSplashScreen;
    private ImageView mImageSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

        mImageSplash = (ImageView)findViewById(R.id.splash_image);
        mSplashScreen = (SplashScreen)findViewById(R.id.splash_screen);

        mApplication = Application.getInstance();

        String restart = getIntent().getAction();
        if (restart.equals(ACTION_RESTART_APPLICATION)) {          
            mApplication.setPermission(SplashActivity.this, mApplication.requestPermissionStorage, new Application.OnActionPermissionListener(){
                    @Override
                    public void onGranted() {
                        setSplashScreen();
                        FolderMe.initVideoBox(SplashActivity.this);    
                    }

                    @Override
                    public void onDenied(String permission) {

                        Log.i(TAG, "onDenied: Write Storage: " + permission);
                        String message = String.format(Locale.getDefault(), getString(R.string.message_denied), permission);
                        Toast.makeText(SplashActivity.this, message, Toast.LENGTH_SHORT).show();
                    }    
                });

        } else {
            /**** START APP ****/
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean isFirstStart = SP.getBoolean("firstStart", true);
            if (isFirstStart) {
                SharedPreferences.Editor e = SP.edit();
                e.putBoolean("firstStart", false);
                e.apply();
                mApplication.setPermission(SplashActivity.this, mApplication.requestPermissionStorage, new Application.OnActionPermissionListener(){
                        @Override
                        public void onGranted() {
                            setSplashScreen();
                            FolderMe.initVideoBox(SplashActivity.this);    
                        }

                        @Override
                        public void onDenied(String permission) {

                            Log.i(TAG, "onDenied: Write Storage: " + permission);
                            String message = String.format(Locale.getDefault(), getString(R.string.message_denied), permission);
                            Toast.makeText(SplashActivity.this, message, Toast.LENGTH_SHORT).show();
                        }    
                    });

            } else {
                setTransition();
            }
        }
    }

    public void setTransition() {
        int SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {

                    Application.getInstance().setApplicationTaskListener(SplashActivity.this, new Application.OnApplicationTaskListener(){
                            @Override
                            public void onPreExecute() {
                                Application.getInstance().soundPlay(R.raw.add);
                            }

                            @Override
                            public void onSuccess(ArrayList<VideoData> result) {
                                Intent mIntent = new Intent(SplashActivity.this, ApplicationActivity.class);
                                startActivity(mIntent);    
                                SplashActivity.this.finish();                                  
                                Application.getInstance().soundPlay(R.raw.done);
                            }

                            @Override
                            public void onFailed() {
                                Application.getInstance().soundPlay(R.raw.error);
                            }

                            @Override
                            public void isEmpty() {

                            } 
                        });                                                                
                }
            }, SPLASH_TIME_OUT); 
    }

    public void setSplashScreen() {
        int SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start SplashScreen
                    mImageSplash.setVisibility(View.GONE);
                    // hide actionbar
                    getSupportActionBar().hide();
                    // hide navigation bar
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
                    mSplashScreen.setVisibility(View.VISIBLE);
                    mSplashScreen.start();
                    mSplashScreen.setOnSplashScreenListener(new SplashScreen.OnSplashScreenListener(){
                            @Override
                            public void OnStartActivity() {
                                mApplication.setApplicationTaskListener(SplashActivity.this, new Application.OnApplicationTaskListener(){
                                        @Override
                                        public void onPreExecute() {
                                            Application.getInstance().soundPlay(R.raw.add);
                                        }

                                        @Override
                                        public void onSuccess(ArrayList<VideoData> result) {
                                            setTransition();
                                            if (!Settings.isShortCut(SplashActivity.this)) {
                                                mSplashScreen.createShortCut(SplashActivity.this);   
                                            }
                                            mApplication.soundPlay(R.raw.done);
                                            SplashActivity.this.finish();
                                        }

                                        @Override
                                        public void onFailed() {
                                            mApplication.soundPlay(R.raw.error);
                                        }

                                        @Override
                                        public void isEmpty() {

                                        } 
                                    });                                                                
                            }
                        });

                }
            }, SPLASH_TIME_OUT); 
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mApplication.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
