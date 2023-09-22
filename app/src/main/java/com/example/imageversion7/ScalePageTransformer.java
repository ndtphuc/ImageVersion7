package com.example.imageversion7;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class ScalePageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.85f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            page.setScaleY(scaleFactor);
        } else {
            page.setScaleY(MIN_SCALE);
        }
    }
}
