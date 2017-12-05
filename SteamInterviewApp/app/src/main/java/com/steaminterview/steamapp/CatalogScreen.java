package com.steaminterview.steamapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by haiha on 03-12-2017.
 */
public class CatalogScreen extends Activity implements IcallbackListener, CallBackForPlayers{

    /**
     * Busy dialog message shown to user
     */
    private static final String BUSY_DIALOG_MESSAGE = "Please wait . . .";

    private GetDataFromServer getDataFromServer;
    private GetDataFromSecoundApi getDataFromSecoundApi;
    /**
     * Used to block UI operations when Async task is called
     */
    private ProgressDialog loadingDialog;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    public static List<GameDetailsWC> listGameDetails;
    public static String apiUrl1="http://store.steampowered.com/api/appdetails/?appids=";
    public static String apiUrl2="https://steamdb.info/api/GetGraph/?type=concurrent_week&appid=";
    private String url1,url2,url3,url4,url5,url6,url7,url8,url9,url10,url11,url12,url13,url14;
    private CardViewAdapter cardViewAdapter;
    private List<Integer> integerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recycler);
        url1=this.getString(R.string.id_1);url8=apiUrl2+url1;
        url2=this.getString(R.string.id_2);url9=apiUrl2+url2;
        url3=this.getString(R.string.id_3);url10=apiUrl2+url3;
        url4=this.getString(R.string.id_4);url11=apiUrl2+url4;
        url5=this.getString(R.string.id_5);url12=apiUrl2+url5;
        url6=this.getString(R.string.id_6);url13=apiUrl2+url6;
        url7=this.getString(R.string.id_7);url14=apiUrl2+url7;
        url1=apiUrl1+url1;
        url2=apiUrl1+url2;
        url3=apiUrl1+url3;
        url4=apiUrl1+url4;
        url5=apiUrl1+url5;
        url6=apiUrl1+url6;
        url7=apiUrl1+url7;

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        getDataFromServer = new GetDataFromServer();
        button2CLicked();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void button2CLicked() {
        getDataFromServer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url1,url2,url3,url4,url5,url6,url7);
        getDataFromServer.setCallabckListener((IcallbackListener) this);
        /**Once background task is requested, start showing Busy dialog and block User Actions**/
        showBusyDialog();
    }

    private void showBusyDialog() {

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage(BUSY_DIALOG_MESSAGE);
        loadingDialog.show();
    }

    @Override
    public void onResult(Object listGameDetailObject) {
        loadingDialog.dismiss();
        try{
            listGameDetails= (List<GameDetailsWC>) listGameDetailObject;
            for(int i=0;i<integerList.size();i++)
                listGameDetails.get(i).setNo_of_players(integerList.get(i));
        }catch (Exception e){

        }
        cardViewAdapter = new CardViewAdapter(listGameDetails);
        recyclerView.setAdapter(cardViewAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromSecoundApi = new GetDataFromSecoundApi();
        getDataFromSecoundApi.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url8,url9,url10,url11,url12,url13,url14);
        getDataFromSecoundApi.setCallBackForPlayers(this);

    }


    @Override
    public void callBackForPlayer(Object resultPlayersList) {
        integerList= (List<Integer>) resultPlayersList;
        if(loadingDialog.isShowing()){}else{
            for(int i=0;i<integerList.size();i++){
                listGameDetails.get(i).setNo_of_players(integerList.get(i));}
            cardViewAdapter.notifyDataSetChanged();}

        }
}
