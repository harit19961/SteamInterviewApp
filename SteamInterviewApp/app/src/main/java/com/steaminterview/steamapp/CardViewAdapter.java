package com.steaminterview.steamapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


/**
 * Created by haiha on 03-12-2017.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewHolder>{

    private List<GameDetailsWC> listGameDetails;
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.catalog_screen, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, final int position) {
        GameDetailsWC gameDetailsWC = listGameDetails.get(position);
        cardViewHolder.imageView.setImageBitmap(gameDetailsWC.getHeaderImage());
        cardViewHolder.textView.setText(gameDetailsWC.getName());
        cardViewHolder.textView1.setText(""+gameDetailsWC.getNo_of_players()+" Players Currently Playing");
        cardViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent=new Intent(context,DetailedScreen.class);
                intent.putExtra("GameObjects",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGameDetails.size();
    }

    public CardViewAdapter(List<GameDetailsWC> listGameDetails) {
        this.listGameDetails=listGameDetails;
    }
}
