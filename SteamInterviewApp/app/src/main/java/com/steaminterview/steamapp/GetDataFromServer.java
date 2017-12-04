package com.steaminterview.steamapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by haiha on 03-12-2017.
 */
public class GetDataFromServer extends AsyncTask<String, String, Object> implements ImageCallBackInterface {

    private List<GameDetailsWC> listGameDetail;
    private IcallbackListener icallbackListener;
    private String headerImage[];
    private int n;
    List<Bitmap> list;
    private String[][] screenshots;

    Object result;
    @Override
    protected Object doInBackground(String... requestURL) {
        n = requestURL.length;
        headerImage = new String[n];
        screenshots = new String[n][5];
        listGameDetail = new ArrayList<GameDetailsWC>(n);
        try {
            for (int i = 0; i < n; i++) {
                URL url = new URL(requestURL[i]);
                boolean flag = false; int count = 0;
                JsonFactory jfactory = new JsonFactory();
                JsonParser jParser = jfactory.createParser(url.openStream());
                GameDetailsWC gameDetailsWC = new GameDetailsWC();
                jParser.nextToken();
                while (jParser.nextToken() != JsonToken.END_OBJECT&&flag==false) {
                    if(jParser.getCurrentName().equals("data")) {
                        flag=true;
                    }else {
                        System.out.println(jParser.getCurrentName());
                        jParser.nextToken();
                    }
                }
                while (jParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldname = jParser.getCurrentName();
                    if(fieldname.equals("values")) {
                        jParser.nextToken();int no_of_players=0;
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            no_of_players = jParser.getIntValue();
                            jParser.nextToken();
                        }
                        gameDetailsWC.setNo_of_players(no_of_players);
                        System.out.println("Values_Twitch");
                    }
                    if(fieldname.equals("name")) {
                        jParser.nextToken();
                        System.out.println("Name:"+jParser.getText());
                        gameDetailsWC.setName(jParser.getText());
                    }
                    else if ("header_image".equals(fieldname)) {
                        jParser.nextToken();
                        System.out.println("HeaderImage:"+jParser.getText());
                        headerImage[i]=jParser.getText();
                    }
                    else if ("price_overview".equals(fieldname)) {
                        jParser.nextToken();
                        while (jParser.nextToken() != JsonToken.END_OBJECT) {
                            if(jParser.getCurrentName().equals("final")) {
                                jParser.nextToken();
                                gameDetailsWC.setCost(jParser.getIntValue());
                            }else {jParser.skipChildren();}
                        }

                    }
                    else if ("short_description".equals(fieldname)) {
                        jParser.nextToken();
                        System.out.println("Description:"+jParser.getText());
                        gameDetailsWC.setDescription(jParser.getText());
                    }
                    else if ("is_free".equals(fieldname)) {
                        jParser.nextToken();
                        System.out.println("Is_free:"+jParser.getText());
                        gameDetailsWC.setIs_free(jParser.getBooleanValue());
                    }
                    else if(fieldname.equals("screenshots")) {
                        jParser.nextToken();
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            jParser.nextToken();
                            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                                if(jParser.getCurrentName().equals("path_thumbnail")&&count<5) {
                                    jParser.nextToken();
                                    System.out.println("Screenshots:"+jParser.getText());
                                    screenshots[i][count]=jParser.getText();count++;
                                }
                                else {
                                    jParser.skipChildren();
                                }
                            }
                        }

                    }
                    else {
                        jParser.skipChildren();
                    }
                }
                jParser.close();

                listGameDetail.add(i, gameDetailsWC);
            }
//            for (int i = n; i < requestURL.length; i++) {
//                URL url = new URL(requestURL[i]);
//                boolean flag=false;
//                JsonFactory jfactory = new JsonFactory();
//                JsonParser jParser = jfactory.createParser(url.openStream());
//                jParser.nextToken();
//                while (jParser.nextToken() != JsonToken.END_OBJECT&&(flag==false)) {
//                    if(jParser.getCurrentName().equals("data")) {
//                        flag=true;
//                    }else {
//                        jParser.nextToken();
//                    }
//                }
//                while (jParser.nextToken() != JsonToken.END_OBJECT) {
//                    String fieldname = jParser.getCurrentName();
//                    int no_of_players = 0;
//                    if (fieldname.equals("values")) {
//                        jParser.nextToken();
//
//                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
//                            no_of_players = jParser.getIntValue();
//                            jParser.nextToken();
//                        }
//                    }
//                     else
//                    {
//                            jParser.skipChildren();
//                    }
//                    listGameDetail.get(i-n).setNo_of_players(no_of_players);
//                }jParser.close();
//            }
        } catch (Exception e) {
            Log.d("Hari", e.toString());
        }
        return listGameDetail;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        this.result=result;
        ImageReadingFromUrl imageReadingFromUrl = new ImageReadingFromUrl();
            //.get function removed
            imageReadingFromUrl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,headerImage[0], headerImage[1], headerImage[2], headerImage[3], headerImage[4], headerImage[5], headerImage[6],screenshots[0][0], screenshots[0][1], screenshots[0][2], screenshots[0][3], screenshots[0][4], screenshots[1][0], screenshots[1][1], screenshots[1][2], screenshots[1][3], screenshots[1][4], screenshots[2][0], screenshots[2][1], screenshots[2][2], screenshots[2][3], screenshots[2][4], screenshots[3][0], screenshots[3][1], screenshots[3][2], screenshots[3][3], screenshots[3][4], screenshots[4][0], screenshots[4][1], screenshots[4][2], screenshots[4][3], screenshots[4][4], screenshots[5][0], screenshots[5][1], screenshots[5][2], screenshots[5][3], screenshots[5][4], screenshots[6][0], screenshots[6][1], screenshots[6][2], screenshots[6][3], screenshots[6][4]);
            imageReadingFromUrl.setCallabckListener(this);
    }

    public void setCallabckListener(IcallbackListener icallbackListener) {
        this.icallbackListener = icallbackListener;
    }

    @Override
    public void callBack(Object resultImage) {
        list= (List<Bitmap>) resultImage;
        int j=0;
        for (int i = 0; i < n; i++){
            listGameDetail.get(i).setHeaderImage(list.get(i));
            j++;
        }
        for (int i = 0; i < n; i++){
            listGameDetail.get(i).setScreenshot1(list.get(j++));
            listGameDetail.get(i).setScreenshot2(list.get(j++));
            listGameDetail.get(i).setScreenshot3(list.get(j++));
            listGameDetail.get(i).setScreenshot4(list.get(j++));
            listGameDetail.get(i).setScreenshot5(list.get(j++));
        }
        icallbackListener.onResult(result);
    }
}