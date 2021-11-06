package com.singhajit.sherlock.engine.app.crashlytics;

import android.app.Application;
import com.singhajit.sherlock.engine.app.crashlytics.core.Sherlock;
import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.AppInfo;
import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.engine.app.crashlytics.core.SherlockNotInitializedException;
import com.singhajit.sherlock.engine.app.crashlytics.util.AppInfoUtil;

public class MyApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            
            Sherlock.init(this); //Initializing Sherlock
            Sherlock.setAppInfoProvider(new AppInfoProvider() {
                    @Override
                    public AppInfo getAppInfo() {
                        return new AppInfo.Builder()
                            .with("Version", AppInfoUtil.getAppVersion(getApplicationContext())) //You can get the actual version using "AppInfoUtil.getAppVersion(context)"
                            .with("BuildNumber", "221B")
                            .build();
                    }
                });
           
        } catch (SherlockNotInitializedException e) {
            e.printStackTrace();
        }
    }
}

