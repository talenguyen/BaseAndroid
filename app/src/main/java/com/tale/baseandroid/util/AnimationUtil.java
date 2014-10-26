package com.tale.baseandroid.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ViewAnimator;

/**
 * Created by tale on 10/26/14.
 */
public class AnimationUtil {

    public static Animator animEnterBottom(View target, long duration) {
        if (target == null) {
            throw new NullPointerException("Target must not be null");
        }

        ViewParent parent = target.getParent();
        int startY = 0;
        if (parent == null) {
            startY = target.getHeight();
        } else {
            startY = ((ViewGroup) parent).getBottom() - target.getTop();
        }

        // Make the view Visible on screen.
        target.setVisibility(View.VISIBLE);
        target.setAlpha(1);

        // Move the view to the startY.
        target.setTranslationY(startY);
        return ObjectAnimator.ofFloat(target, ViewAnimator.TRANSLATION_Y, 0).setDuration(duration);
    }
}
