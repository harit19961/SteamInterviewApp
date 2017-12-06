package com.steaminterview.steamapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;

import static com.steaminterview.steamapp.CatalogScreen.listGameDetails;
//import static com.steaminterview.steamapp.LoginScreen.debugLog;


/**
 * Created by haiha on 04-12-2017.
 */

public class DetailedScreen extends Activity {
    private GameDetailsWC gameDetailsWC;
    private ViewFlipper mViewFlipper;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen);
        Intent intent = getIntent();
        int position = intent.getIntExtra("GameObjects",1);
        gameDetailsWC = listGameDetails.get(position) ;
            viewPager = (ViewPager) findViewById(R.id.viewPager);

            sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,position);

            viewPager.setAdapter(viewPagerAdapter);

            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];

            for(int i = 0; i < dotscount; i++){

                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                sliderDotspanel.addView(dots[i], params);

            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                }


                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);



        TextView textView = (TextView) findViewById(R.id.gameName);
        textView.setText(gameDetailsWC.getName());
        TextView description = (TextView) findViewById(R.id.gameDescription);
        String desc = gameDetailsWC.getDescription();
        String new_desc = desc.replaceAll("<strong>","");
        new_desc = new_desc.replaceAll("</strong>","");
        description.setText(new_desc);
        TextView cost = (TextView) findViewById(R.id.cost);
        if(gameDetailsWC.is_free)
            cost.setText("Free");
        else
            cost.setText("\u20B9"+gameDetailsWC.getCost()/100);
        }

        public class MyTimerTask extends TimerTask {

            @Override
            public void run() {

                DetailedScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);
                        }else if (viewPager.getCurrentItem() == 2) {
                                viewPager.setCurrentItem(3);
                        }else if (viewPager.getCurrentItem() == 3) {
                            viewPager.setCurrentItem(4);
                        } else {
                            viewPager.setCurrentItem(0);
                        }

                    }
                });

            }
        }
    }