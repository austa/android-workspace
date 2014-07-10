package com.austa.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.austa.barcodescanner.utils.UiViewHelper;

public class SplashActivity extends Activity {
    private View mViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUI();
        startTimer();
    }

    private void setUI() {

        mViewMain = (View) findViewById(R.id.vw_splash_main);

    }

    private void startTimer() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                finishActivity();
            }

        }, 2500);

    }

    /**
     * finish activity without memory expand
     */
    private void finishActivity() {

        SplashActivity.this.finish();
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        UiViewHelper.unbindDrawables(mViewMain);
        super.onDestroy();
    }
}