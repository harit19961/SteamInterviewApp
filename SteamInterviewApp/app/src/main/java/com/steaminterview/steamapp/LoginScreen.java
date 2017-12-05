package com.steaminterview.steamapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        loginButton=(LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        String s = loginButton.getText().toString();
        if(s.equals("Log out")){
            Intent intent= new Intent(getApplicationContext(),CatalogScreen.class);
            finish();
            startActivity(intent);
        }

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(getApplicationContext(),CatalogScreen.class);
                    finish();
                    startActivity(intent);
            }


            @Override
            public void onCancel() {
                    Toast.makeText(getApplicationContext(),"Login Cancelled",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                    Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_LONG).show();
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
