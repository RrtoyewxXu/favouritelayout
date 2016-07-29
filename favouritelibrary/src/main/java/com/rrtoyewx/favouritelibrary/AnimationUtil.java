package com.rrtoyewx.favouritelibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Rrtoyewx on 16/7/26.
 */
public class AnimationUtil {

    public static AnimatorSet generateEnterAnimation(final View targetView) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(targetView, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(targetView, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        return enter;
    }

    public static ValueAnimator generateUpAnimation(PointF startPoint, PointF pointF1, PointF pointF2, PointF endPoint, ValueAnimator.AnimatorUpdateListener listener) {
        BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, startPoint, endPoint);
        animator.setDuration(3000);

        animator.addUpdateListener(listener);
        return animator;
    }
}
