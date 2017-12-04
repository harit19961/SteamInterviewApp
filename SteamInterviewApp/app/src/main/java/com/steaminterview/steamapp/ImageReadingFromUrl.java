package com.steaminterview.steamapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haiha on 04-12-2017.
 */

public class ImageReadingFromUrl extends AsyncTask<String,String,Object> {
    ImageCallBackInterface imageCallBackInterface;
    @Override
    protected Object doInBackground(String... imageUrls) {
       List<Bitmap> resultBitmap= new ArrayList<Bitmap>(imageUrls.length);
        try {
            for (int i = 0; i < imageUrls.length; i++) {
                Bitmap bitMap;
                URL aURL = new URL(imageUrls[i]);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitMap= BitmapFactory.decodeStream(bis);
                resultBitmap.add(i,bitMap);
                bis.close();
                is.close();
            }
        }catch (Exception e){}
       return resultBitmap;
    }
    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        imageCallBackInterface.callBack(result);
    }
    public void setCallabckListener(ImageCallBackInterface imageCallBackInterface) {
        this.imageCallBackInterface = imageCallBackInterface;
    }
}
