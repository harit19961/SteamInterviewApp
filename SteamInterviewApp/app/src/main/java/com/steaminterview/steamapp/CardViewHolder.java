package com.steaminterview.steamapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by haiha on 03-12-2017.
 */
public class CardViewHolder extends RecyclerView.ViewHolder{

    public final ImageView imageView;
    public final TextView textView1;
    public final TextView textView;
    public final CardView cardView;
    public CardViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.textView);
        textView1 = (TextView) itemView.findViewById(R.id.textView1);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
    }
}
