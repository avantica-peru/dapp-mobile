package net.avantica.xinef.dapp.font;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.TextView;

public class Bindings {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        Log.d("font binding ", fontName);
        textView.setTypeface(FontCache.getInstance().get(fontName));
    }
}