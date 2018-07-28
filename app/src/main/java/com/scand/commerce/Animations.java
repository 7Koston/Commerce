package com.scand.commerce;

import android.view.View;

public final class Animations {

    public static void showElementFade(final View view, int duration) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setAlpha(0.0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .setDuration(duration)
                .alpha(1.0f);
    }

    public static void hideElementFade(final View view, int duration) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        view.animate()
                .setDuration(duration)
                .alpha(0.0f);
        view.setVisibility(View.GONE);
    }
}
