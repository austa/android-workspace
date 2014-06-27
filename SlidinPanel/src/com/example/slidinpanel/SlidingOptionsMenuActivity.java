package com.example.slidinpanel;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuView.LayoutParams;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class SlidingOptionsMenuActivity extends Activity {

    
    boolean alreadyShowing = false;
    private int windowWidth;
    private int windowHeight;
    private PopupWindow fadePopup;
    private Animation ta;
    private RelativeLayout baseView;
    LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        windowWidth = display.getWidth();
        windowHeight = display.getHeight();
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        findViewById(R.id.btnOptions).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!alreadyShowing){
                    alreadyShowing = true;
                    openSlidingMenu();
                }
            }
        });
    }


    
    private void showFadePopup() {
        final View layout = inflater.inflate(R.layout.fadepopup, (ViewGroup) findViewById(R.id.fadePopup));
        fadePopup = new PopupWindow(layout, windowWidth, windowHeight, false);
        fadePopup.showAtLocation(layout, Gravity.NO_GRAVITY, 0, 0);
    }

    
    private void openSlidingMenu() {
        showFadePopup();
        // The amount of view which needs to be moved out. equivalent to the
        // width of the menu
        int width = (int) (windowWidth * 0.6f);
        translateView((float) (width));
        int height = LayoutParams.FILL_PARENT;
        // creating a popup
        final View layout = inflater.inflate(R.layout.option_popup_layout,(ViewGroup) findViewById(R.id.popup_element));

        final PopupWindow optionsPopup = new PopupWindow(layout, width, height, true);
        optionsPopup.setBackgroundDrawable(new PaintDrawable());

        optionsPopup.showAtLocation(layout, Gravity.NO_GRAVITY, 0, 0);

        optionsPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            public void onDismiss() {
                //Removing the fade effect
                fadePopup.dismiss();    
                //to clear the previous animation transition in
                cleanUp();
                //move the view out
                translateView(0);
                //to clear the latest animation transition out
                cleanUp();
                //resetting the variable
                alreadyShowing = false;
            }
        });
    }

  
    private void translateView(float right) {

        ta = new TranslateAnimation(0f, right, 0f, 0f);
        ta.setDuration(300);
        ta.setFillEnabled(true);
        ta.setFillAfter(true);

        baseView = (RelativeLayout) findViewById(R.id.baseView);
        baseView.startAnimation(ta);
        baseView.setVisibility(View.VISIBLE);
    }

  
    private void cleanUp(){
        if (null != baseView) {
            baseView.clearAnimation();
            baseView = null;
        }
        if (null != ta) {
            ta.cancel();
            ta = null;
        }
        fadePopup = null;
    }
}