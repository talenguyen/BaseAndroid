package com.tale.baseandroid;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tale on 10/22/14.
 */
public class Toasts {
    private final Context context;

    public Toasts(Context context) {
        this.context = context;
    }

    public void showShort(int msgId) {
        Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
    }

    public void showLong(int msgId) {
        Toast.makeText(context, msgId, Toast.LENGTH_LONG).show();
    }

    public void showShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
