package com.rrtoyewx.favouritelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rrtoyewx.favouritelibrary.FavouriteItem;
import com.rrtoyewx.favouritelibrary.FavouriteLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FavouriteLayout favouriteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favouriteLayout = (FavouriteLayout) findViewById(R.id.favouritelayout);
    }

    public void start(View view) {
        List<FavouriteItem> favoriteList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            favoriteList.add(new FavouriteItem.FavouriteItemBuilder().build(this));
        }
        favouriteLayout.addItemList(favoriteList);
    }
}
