package ch.blinkenlights.android.vanilla;

import android.app.Application;
import android.content.Context;
import android.view.View;

import java.io.File;

import com.singhajit.sherlock.core.Sherlock;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;
import com.singhajit.sherlock.core.SherlockNotInitializedException;
import com.singhajit.sherlock.util.AppInfoUtil;

public class AppController extends Application{
    private static Context mContext;
    private static AppController mInstance;

    
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        mContext = this;
        mInstance = this;
        
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
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
    
    public static synchronized Context getContext(){
        return mContext;
    }

}
