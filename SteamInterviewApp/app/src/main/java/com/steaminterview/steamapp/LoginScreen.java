package com.steaminterview.steamapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by haiharasudhan on 03-12-2017.
 */

public class LoginScreen extends Activity {
    public LoginButton loginButton;
    public CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        loginButton=(LoginButton) findViewById(R.id.fbuser_login_button);
//        debugLog(loginButton.getText().toString());
        if(loginButton.getText().toString().equals("Continue with Facebook")) {
            loginButton.setVisibility(View.VISIBLE);
        }
        else{
        Intent intent=new Intent(this,CatalogScreen.class);
            startActivity(intent);
        }
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }


            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
//    public static void debugLog(String s) {
//        Log.d("LoginScreen",s);
//    }

}
