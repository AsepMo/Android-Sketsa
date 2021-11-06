package com.singhajit.sherlock.engine.app.crashlytics;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.singhajit.sherlock.engine.app.crashlytics.core.Sherlock;
import com.singhajit.sherlock.engine.app.crashlytics.core.SherlockNotInitializedException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            Sherlock.getInstance().getAllCrashes();
            Sherlock.getInstance().getAppInfoProvider();
        } catch (SherlockNotInitializedException e) {
            e.printStackTrace();
        }

        findViewById(R.id.testCrash).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String testStr = null;
        int length = testStr.length();
    }
}
