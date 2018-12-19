package com.seemantov.pokmy.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtils {

    public static void setActivityTypeFaceAr(Context context, ViewGroup viewGroup) {

        Object tagAux;

        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View viewChild = viewGroup.getChildAt(i);

            if (viewChild instanceof ViewGroup) {
                setActivityTypeFaceAr(context, (ViewGroup) viewChild);

            } else if (viewChild instanceof TextView) {

                tagAux = viewChild.getTag();

                if (tagAux != null) {


                    ((TextView) viewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "cairo_regular.ttf"));
                }
            }
        }
    }

    public static void setActivityTypeFaceFr(Context context, ViewGroup viewGroup) {

        Object tagAux;

        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View viewChild = viewGroup.getChildAt(i);

            if (viewChild instanceof ViewGroup) {
                setActivityTypeFaceAr(context, (ViewGroup) viewChild);

            } else if (viewChild instanceof TextView) {

                tagAux = viewChild.getTag();

                if (tagAux != null) {

                    if (((String) tagAux).compareTo("title") == 0) {
                        ((TextView) viewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
                    }
                } else if (((String) tagAux).compareTo("content") == 0) {
                    ((TextView) viewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "roboto_regular.otf"));
                }
            }
        }
    }

}
