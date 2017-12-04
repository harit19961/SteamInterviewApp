package com.steaminterview.steamapp;

import android.graphics.Bitmap;

/**
 * Created by haiha on 03-12-2017.
 */

public class GameDetailsWC {
    String name;
    Bitmap headerImage;
    Bitmap screenshot1;
    Bitmap screenshot2;
    Bitmap screenshot3;
    Bitmap screenshot4;
    Bitmap screenshot5;
    String description;
    boolean is_free;
    int cost;
    String currency;
    int no_of_players;

    public int getNo_of_players() {
        return no_of_players;
    }

    public void setNo_of_players(int no_of_players) {
        this.no_of_players = no_of_players;
    }

    public Bitmap getScreenshot1() {
        return screenshot1;
    }

    public void setScreenshot1(Bitmap screenshot1) {
        this.screenshot1 = screenshot1;
    }

    public Bitmap getScreenshot2() {
        return screenshot2;
    }

    public void setScreenshot2(Bitmap screenshot2) {
        this.screenshot2 = screenshot2;
    }

    public Bitmap getScreenshot3() {
        return screenshot3;
    }

    public void setScreenshot3(Bitmap screenshot3) {
        this.screenshot3 = screenshot3;
    }

    public Bitmap getScreenshot4() {
        return screenshot4;
    }

    public void setScreenshot4(Bitmap screenshot4) {
        this.screenshot4 = screenshot4;
    }

    public Bitmap getScreenshot5() {
        return screenshot5;
    }

    public void setScreenshot5(Bitmap screenshot5) {
        this.screenshot5 = screenshot5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(Bitmap headerImage) {
        this.headerImage = headerImage;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
