package com.steaminterview.steamapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import static com.steaminterview.steamapp.CatalogScreen.listGameDetails;

/**
 * Created by Sanket on 27-Feb-17.
 */

class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int position;
    private Bitmap[] images;

    public ViewPagerAdapter(Context context, int position) {
        this.context = context;
        this.position = position;
        images =new Bitmap[5];
        images[0] = listGameDetails.get(this.position).getScreenshot1();
        images[1]=listGameDetails.get(this.position).getScreenshot2();
        images[2]=listGameDetails.get(this.position).getScreenshot3();
        images[3]=listGameDetails.get(this.position).getScreenshot4();
        images[4]=listGameDetails.get(this.position).getScreenshot5();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_template, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageBitmap(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 0){
                    Toast.makeText(context, "Slide 1 Clicked", Toast.LENGTH_SHORT).show();
                } else if(position == 1) {
                    Toast.makeText(context, "Slide 2 Clicked", Toast.LENGTH_SHORT).show();
                } else if(position == 2){
                        Toast.makeText(context, "Slide 3 Clicked", Toast.LENGTH_SHORT).show();
                } else if(position == 3){
                        Toast.makeText(context, "Slide 4 Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Slide 5 Clicked", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}

