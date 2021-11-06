package com.video.client;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.singhajit.sherlock.core.Sherlock;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.SherlockNotInitializedException;
import com.singhajit.sherlock.util.AppInfoUtil;

import com.video.client.engine.widget.soundPool.ISoundPoolLoaded;
import com.video.client.engine.widget.soundPool.SoundPoolManager;

public class AppController extends Application {
    private static AppController isInstance;
    private static Context mContext;
    
    @Override
    public void onCreate() {
        super.onCreate();

        isInstance = this;
        mContext = this;

        try {

            Sherlock.init(this); //Initializing Sherlock
            Sherlock.setAppInfoProvider(new AppInfoProvider() {
                    @Override
                    public AppInfo getAppInfo() {
                        return new AppInfo.Builder()
                            .with("Version", AppInfoUtil.getAppVersion(getApplicationContext())) //You can get the actual version using "AppInfoUtil.getAppVersion(context)"
                            .with("BuildNumber", "1")
                            .build();
                    }
                });

        } catch (SherlockNotInitializedException e) {
            e.printStackTrace();
        } 
        
        SoundPool();
    }

    public static synchronized AppController getInstance() {
        return isInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    public void SoundPool() {
        SoundPoolManager.CreateInstance();
        List<Integer> sounds = new ArrayList<Integer>();
        sounds.add(R.raw.sound1);
        sounds.add(R.raw.sound2);
        sounds.add(R.raw.add);
        sounds.add(R.raw.done);
        sounds.add(R.raw.error);
        SoundPoolManager.getInstance().setSounds(sounds);
        try {
            SoundPoolManager.getInstance().InitializeSoundPool(getApplicationContext(), new ISoundPoolLoaded() {
                    @Override
                    public void onSuccess() {

                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }

        SoundPoolManager.getInstance().setPlaySound(true);
    }
}
