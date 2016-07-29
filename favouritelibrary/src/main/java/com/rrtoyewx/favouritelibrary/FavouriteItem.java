package com.rrtoyewx.favouritelibrary;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Rrtoyewx on 16/7/26.
 */
public class FavouriteItem {
    private static final int DEFAULT_IMAGE_WIDTH = 80;
    private static final int DEFAULT_IMAGE_HEIGHT = 80;
    private final int[] imageSrcS;
    private final Interpolator[] interpolatorS;
    private Random random;

    private int width;
    private int height;

    private Context context;
    private int imageSrc;
    private ImageView imageView;
    private RelativeLayout.LayoutParams layoutParams;
    private Interpolator interpolator;

    {
        random = new Random();
        imageSrcS = new int[]{
                R.drawable.pl_red, R.drawable.pl_blue, R.drawable.pl_yellow
        };

        interpolatorS = new Interpolator[]{
                new AccelerateInterpolator(), new DecelerateInterpolator(), new AccelerateDecelerateInterpolator()
        };
    }

    private FavouriteItem() {

    }

    private FavouriteItem(Context context, int imageSrc, int width, int height, Interpolator interpolator) {
        if (context == null) {
            throw new IllegalArgumentException("context must be not null");
        }
        this.context = context;
        this.imageSrc = imageSrc == 0 ? imageSrcS[random.nextInt(imageSrcS.length)] : imageSrc;
        this.interpolator = interpolator == null ? interpolatorS[random.nextInt(interpolatorS.length)] : interpolator;

        this.width = width == 0 ? DEFAULT_IMAGE_WIDTH : width;
        this.height = height == 0 ? DEFAULT_IMAGE_HEIGHT : height;

        init();
    }

    private void init() {
        layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

        imageView = new ImageView(context);
        imageView.setImageResource(imageSrc);
        imageView.setLayoutParams(layoutParams);
    }

    public ImageView imageView() {
        return imageView;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Interpolator interpolator() {
        return interpolator;
    }

    public static class FavouriteItemBuilder {
        private int imageSrc;
        private Interpolator interpolator;
        private int height;
        private int width;

        public FavouriteItemBuilder imageSrc(int imageSrc) {
            this.imageSrc = imageSrc;
            return this;
        }

        public FavouriteItemBuilder interpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public FavouriteItemBuilder height(int height) {
            this.height = height;
            return this;
        }

        public FavouriteItemBuilder width(int width) {
            this.width = width;
            return this;
        }

        public FavouriteItem build(Context context) {
            return new FavouriteItem(context, imageSrc, width, height, interpolator);
        }
    }
}
