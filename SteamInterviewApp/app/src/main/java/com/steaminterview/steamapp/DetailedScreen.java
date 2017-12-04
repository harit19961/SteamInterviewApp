package com.steaminterview.steamapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import static com.steaminterview.steamapp.CatalogScreen.listGameDetails;
//import static com.steaminterview.steamapp.LoginScreen.debugLog;


/**
 * Created by haiha on 04-12-2017.
 */

public class DetailedScreen extends Activity {
    private GameDetailsWC gameDetailsWC;
    private ViewFlipper mViewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen);
        Intent intent = getIntent();
        int position = intent.getIntExtra("GameObjects",1);
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        try{
        gameDetailsWC = listGameDetails.get(position) ;
            ImageView imageView1 =(ImageView) findViewById(R.id.screenshot1);
            ImageView imageView2 =(ImageView) findViewById(R.id.screenshot2);
            ImageView imageView3 =(ImageView) findViewById(R.id.screenshot3);
            ImageView imageView4 =(ImageView) findViewById(R.id.screenshot4);
            ImageView imageView5 =(ImageView) findViewById(R.id.screenshot5);
            imageView1.setImageBitmap(gameDetailsWC.getScreenshot1());
            imageView2.setImageBitmap(gameDetailsWC.getScreenshot2());
            imageView3.setImageBitmap(gameDetailsWC.getScreenshot3());
            imageView4.setImageBitmap(gameDetailsWC.getScreenshot4());
            imageView5.setImageBitmap(gameDetailsWC.getScreenshot5());
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
        }catch(Exception e){
//            debugLog("DetialedScreen:Exception:"+e);
        }
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        mViewFlipper.setInAnimation(in);
        mViewFlipper.setOutAnimation(out);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.startFlipping();
    }
}
