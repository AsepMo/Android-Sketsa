package com.video.client.application;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.ref.WeakReference;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import com.video.client.R;
import com.video.client.engine.app.adapters.DrawerAdapter;
import com.video.client.engine.app.models.DrawerItem;
import com.video.client.engine.app.models.SimpleItem;
import com.video.client.engine.app.models.SpaceItem;
import com.video.client.engine.app.fragments.ServerFragment;
import com.video.client.engine.app.fragments.ClientFragment;

public class ApplicationActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    public static String TAG = ApplicationActivity.class.getSimpleName();
    private static final int POS_CLIENT_APP_CAMERA = 0;
    private static final int POS_CLIENT_APP_CONTROLLER = 1;
    private static final int POS_CLIENT_APP_TERMINAL = 2;
    private static final int POS_CLIENT_APP_MEDIA = 3;
    private static final int POS_APP_EXIT = 5;

    private static final boolean DEBUG = false;

    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;

    private static Boolean isQuit = false;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_left_drawer)
            .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                                                      createItemFor(POS_CLIENT_APP_CAMERA).setChecked(true),
                                                      createItemFor(POS_CLIENT_APP_CONTROLLER),
                                                      createItemFor(POS_CLIENT_APP_TERMINAL),
                                                      createItemFor(POS_CLIENT_APP_MEDIA),
                                                      new SpaceItem(48),                                       
                                                      createItemFor(POS_APP_EXIT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_CLIENT_APP_CAMERA);
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit();
    } 


    @Override
    public void onItemSelected(int position) {

        if (position == POS_CLIENT_APP_CAMERA) {
            switchFragment(ServerFragment.createFor("Server"));
        }
        if (position == POS_CLIENT_APP_CONTROLLER) {
            switchFragment(ClientFragment.createFor("Server"));
        }
        if (position == POS_CLIENT_APP_TERMINAL) {

        }
        if (position == POS_CLIENT_APP_MEDIA) {

        }
        if (position == POS_APP_EXIT) {
            Application.getInstance().exitApplication(this);
        }
        slidingRootNav.closeMenu();

    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
            .withIconTint(color(R.color.textColorSecondary))
            .withTextTint(color(R.color.textColorPrimary))
            .withSelectedIconTint(color(R.color.selected_color_tint))
            .withSelectedTextTint(color(R.color.selected_color_tint));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    private static final class MyBroadcastReceiver extends BroadcastReceiver {
        private final WeakReference<ApplicationActivity> mWeakParent;
        public MyBroadcastReceiver(final ApplicationActivity parent) {
            mWeakParent = new WeakReference<ApplicationActivity>(parent);
        }

        @Override
        public void onReceive(final Context context, final Intent intent) {
            if (DEBUG) Log.v(TAG, "onReceive:" + intent);
            final String action = intent.getAction();
            /*if (ScreenRecordingService.ACTION_QUERY_STATUS_RESULT.equals(action)) {
             final boolean isRecording = intent.getBooleanExtra(ScreenRecordingService.EXTRA_QUERY_RESULT_RECORDING, false);
             final boolean isPausing = intent.getBooleanExtra(ScreenRecordingService.EXTRA_QUERY_RESULT_PAUSING, false);
             final ApplicationActivity parent = mWeakParent.get();
             if (parent != null) {
             parent.updateRecording(isRecording, isPausing);
             }
             }*/
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Microphone")
            .setIcon(R.drawable.ic_microphone)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    return true;
                }
            }).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Camera")
            .setIcon(R.drawable.ic_camera)
            .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    requestTakePhoto();
                    return true;
                }
            }).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
        return super.onCreateOptionsMenu(menu);
    }*/


    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");      
        super.onDestroy();
    }

    @Override
    public void finish() {
        Log.v(TAG, "finish");
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isQuit) {
                isQuit = true;
                Toast.makeText(getBaseContext(), R.string.app_press_back_againt_to_exit, Toast.LENGTH_LONG).show();
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}

