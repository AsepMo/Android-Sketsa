package com.video.client.engine.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.Comparator;

public class Settings {
    private static final String 
    NAME = "SharedPref",
    PREF_START_FOLDER = "start_folder",
    PREF_START_FILE = "start_file",
    PREF_SORT_BY = "sort_by";
    
    public static void setIsShortCut(Context context, Boolean isLogin) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isShortCut", isLogin);
        editor.commit();
    }

    public static Boolean isShortCut(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean("isShortCut", false);
    }
    
}
