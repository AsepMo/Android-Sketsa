package com.yarolegovich.slidingrootnav.sample;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.sample.menu.DrawerAdapter;
import com.yarolegovich.slidingrootnav.sample.menu.DrawerItem;
import com.yarolegovich.slidingrootnav.sample.menu.SimpleItem;
import com.yarolegovich.slidingrootnav.sample.menu.SpaceItem;
import com.yarolegovich.slidingrootnav.sample.fragment.CenteredTextFragment;

import java.util.Arrays;
import android.widget.Toast;

/**
 * Created by yarolegovich on 25.03.2017.
 */

public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener, View.OnClickListener{

    private static final int POS_VIDEO_PLAYER = 0;
    private static final int POS_VIDEO_LIBRARY = 1;
    private static final int POS_VIDEO_FOLDER = 2;
    private static final int POS_VIDEO_CLOUD_DOWNLOAD = 3;
    private static final int POS_APP_MANAGER = 5;
    private static final int POS_MORE_APPS = 6;
    
    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
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
                                                      createItemFor(POS_VIDEO_PLAYER).setChecked(true),
                                                      createItemFor(POS_VIDEO_LIBRARY),
                                                      createItemFor(POS_VIDEO_FOLDER),
                                                      createItemFor(POS_VIDEO_CLOUD_DOWNLOAD),
                new SpaceItem(48),
                createItemFor(POS_APP_MANAGER),
                createItemFor(POS_MORE_APPS)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        findViewById(R.id.about_layout).setOnClickListener(this);
        findViewById(R.id.action_profile).setOnClickListener(this);       
        findViewById(R.id.settings_layout).setOnClickListener(this);
        findViewById(R.id.exit_layout).setOnClickListener(this);
        
        adapter.setSelected(POS_VIDEO_PLAYER);
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_VIDEO_PLAYER) {
            
        }
        if (position == POS_VIDEO_LIBRARY) {

        }
        if (position == POS_VIDEO_FOLDER) {

        }
        if (position == POS_VIDEO_CLOUD_DOWNLOAD) {

        }
        if (position == POS_APP_MANAGER) {

        }
        if (position == POS_MORE_APPS) {

        }
        slidingRootNav.closeMenu();
        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);
    }

    @Override
    public void onClick(View v) {
        String title = "";
        switch(v.getId()){
            case R.id.about_layout:
                title = "About";
                break;
            case R.id.settings_layout:
                title = "Settings";
                break;
            case R.id.exit_layout:
                title = "Exit";
                finish();
                break;
            case R.id.action_profile:
                title = "Profile";
                break;     
        }
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
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
}
