package com.rrtoyewx.favouritelibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Rrtoyewx on 16/7/26.
 */
public class FavouriteLayout extends RelativeLayout {
    private List<FavouriteItem> favouriteItems = new ArrayList<>();

    int width;
    int height;

    Random random;

    {
        random = new Random();
    }

    public FavouriteLayout(Context context) {
        this(context, null);
    }

    public FavouriteLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FavouriteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addItemList(List<FavouriteItem> items) {
        favouriteItems.addAll(items);
        for (int i = 0; i < items.size(); i++) {
            addView(items.get(i).imageView());
            startAnimation(items.get(i));
        }
    }

    public void addItem(FavouriteItem item) {
        favouriteItems.add(item);
        addView(item.imageView());
        startAnimation(item);
    }

    private void startAnimation(FavouriteItem item) {
        final ImageView imageView = item.imageView();

        AnimatorSet animatorSet = AnimationUtil.generateEnterAnimation(imageView);
        AnimatorSet finalSet = new AnimatorSet();

        PointF startPoint = new PointF((width - item.width()) / 2, height - item.height());
        PointF endPoint = new PointF(random.nextInt(width), 0);
        PointF pointF1 = randomPointF(2);
        PointF pointF2 = randomPointF(1);

        ValueAnimator valueAnimator = AnimationUtil.generateUpAnimation(startPoint, pointF1, pointF2, endPoint, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                float animatedFraction = animation.getAnimatedFraction();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                imageView.setAlpha(1 - animatedFraction);
            }
        });

        finalSet.playSequentially(animatorSet, valueAnimator);
        finalSet.setTarget(imageView);
        finalSet.setInterpolator(item.interpolator());

        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }
        });

        finalSet.start();
    }

    private PointF randomPointF(int position) {
        PointF pointF = new PointF();
        pointF.x = random.nextInt((width));
        pointF.y = random.nextInt((height)) / position;
        return pointF;
    }
}
