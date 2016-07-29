package com.rrtoyewx.favouritelibrary;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Rrtoyewx on 16/7/26.
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF pointf1;
    private PointF pointf2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointf1 = pointF1;
        this.pointf2 = pointF2;
    }

    //B(t) = P0(1-t)3+3P1(1-t)2+3(1-t)
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF currentPointF = new PointF();

        currentPointF.x = (1 - fraction) * (1 - fraction) * (1 - fraction) * startValue.x
                + 3 * fraction * (1 - fraction) * (1 - fraction) * pointf1.x
                + 3 * fraction * fraction * (1 - fraction) * pointf2.x
                + fraction * fraction * fraction * endValue.x;

        currentPointF.y = (1 - fraction) * (1 - fraction) * (1 - fraction) * startValue.y
                + 3 * fraction * (1 - fraction) * (1 - fraction) * pointf1.y
                + 3 * fraction * fraction * (1 - fraction) * pointf2.y
                + fraction * fraction * fraction * endValue.y;

        return currentPointF;
    }
}
