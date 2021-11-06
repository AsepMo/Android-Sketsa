package com.video.client.application;

import android.Manifest;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import com.video.client.R;
import com.video.client.AppController;
import com.video.client.engine.app.folders.FileMe;
import com.video.client.engine.app.folders.FolderMe;
import com.video.client.engine.app.models.VideoData;
import com.video.client.engine.app.tasks.VideoTask;
import com.video.client.engine.app.permissions.PermissionsManager;
import com.video.client.engine.app.permissions.PermissionsResultAction;
import com.video.client.engine.widget.soundPool.SoundPoolManager;

public class Application {
    private static final String TAG = Application.class.getSimpleName();
    private static volatile Application Instance = null;
    private Context context;

    public static Application getInstance() {
        Application localInstance = Instance;
        if (localInstance == null) {
            synchronized (Application.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new Application(AppController.getContext());
                }
            }
        }
        return localInstance;
    }

    private Application(Context context) {
        this.context = context;
        FolderMe.initVideoBox(context);
        FileMe.getInstance().FileMe(context, context.getString(R.string.app_name), "video.dat", "Welcome To ViSplash");
    }

    public static Application with(Context context) {
        return new Application(context);
    }
    
    public void setPermission(Activity act, String[] permissions, final OnActionPermissionListener mOnActionPermissionListener) {      
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(act, permissions, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    mOnActionPermissionListener.onGranted();
                }


                @Override
                public void onDenied(String permission) {
                    mOnActionPermissionListener.onDenied(permission);
                }
            });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
        Log.i(TAG, "Activity-onRequestPermissionsResult() PermissionsManager.notifyPermissionsChange()");
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
    
    public void setApplicationTaskListener(final Activity act, final OnApplicationTaskListener listener) {

        VideoTask task = new VideoTask(act);
        task.setOnVideoTaskListener(new VideoTask.OnVideoTaskListener(){
                @Override
                public void onPreExecute() {
                    listener.onPreExecute();
                }
                @Override
                public void onSuccess(ArrayList<VideoData> result) {
                    listener.onSuccess(result); 
                }

                @Override
                public void onFailed() {
                    listener.onFailed(); 
                } 

                @Override
                public void isEmpty() {
                    listener.isEmpty();
                } 
            });
        task.execute();    
    }
    
    public void soundPlay(int sound) {
        SoundPoolManager.getInstance().playSound(sound);
    }


    public void soundRelese() {
        SoundPoolManager.getInstance().release();
    }
    
    
    public String[] requestPermissionStorage = new String[]
    {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public void exitApplication(Context c) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }

    public interface OnActionPermissionListener {
        void onGranted();
        void onDenied(String permission);
    }

    public interface OnApplicationTaskListener {
        void onPreExecute();
        void onSuccess(ArrayList<VideoData> result);
        void onFailed();
        void isEmpty();
    }
}
