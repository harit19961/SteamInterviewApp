package com.steaminterview.steamapp;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haiha on 05-12-2017.
 */
public class GetDataFromSecoundApi extends AsyncTask<String,String,Object>{
    CallBackForPlayers callBackForPlayers;
    @Override
    protected Object doInBackground(String... requestUrls) {
        int n=requestUrls.length;
        List<Integer> list;
        list = new ArrayList<Integer>();
        try {
            for (int i = 0; i < n; i++) {
                URL url = new URL(requestUrls[i]);
                boolean flag = false;
                JsonFactory jfactory = new JsonFactory();
                JsonParser jParser = jfactory.createParser(url.openStream());
                jParser.nextToken();
                while (jParser.nextToken() != JsonToken.END_OBJECT && (flag == false)) {
                    if (jParser.getCurrentName().equals("data")) {
                        flag = true;
                    } else {
                        jParser.nextToken();
                    }
                }
                while (jParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldname = jParser.getCurrentName();
                    int no_of_players = 0;
                    if (fieldname.equals("values")) {
                        jParser.nextToken();
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            no_of_players = jParser.getIntValue();
                            jParser.nextToken();
                        }
                    } else {
                        jParser.skipChildren();
                    }
                    if(no_of_players!=0){
                    list.add(i,no_of_players);}
                }
                jParser.close();
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        callBackForPlayers.callBackForPlayer(o);
    }

    void setCallBackForPlayers(CallBackForPlayers callBackForPlayers){
        this.callBackForPlayers=callBackForPlayers;
    }
}
