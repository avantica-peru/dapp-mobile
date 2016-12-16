package net.avantica.xinef.dapp.font;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import net.avantica.xinef.dapp.AndroidApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontCache {

    private static String TAG = "FontCache";
    private static final String FONT_DIR = "fonts";
    private static Map<String, Typeface> cache = new HashMap<>();
    private static Map<String, String> fontMapping = new HashMap<>();
    private static FontCache instance;

    public static FontCache getInstance() {
        if (instance == null) {
            instance = new FontCache();
        }
        return instance;
    }

    public void addFont(String name, String fontFilename) {
        fontMapping.put(name, fontFilename);
    }

    private FontCache() {
        AssetManager am = AndroidApplication.getAppContext().getResources().getAssets();
        String fileList[];
        try {
            fileList = am.list(FONT_DIR);
        } catch (IOException e) {
            Log.e(TAG, "Error loading fonts from assets/fonts.");
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            fontMapping.put(alias, filename);
            fontMapping.put(alias.toLowerCase(), filename);
        }
    }

    public Typeface get(String fontName) {
        Log.d("font ", fontName);
        String fontFilename = fontMapping.get(fontName);
        if (fontFilename == null) {
            Log.e(TAG, "Couldn't find font " + fontName + ". Maybe you need to call addFont() first?");
            return null;
        }
        if (cache.containsKey(fontName)) {
            return cache.get(fontName);
        } else {
            Typeface typeface = Typeface.createFromAsset(AndroidApplication.getAppContext().getAssets(), FONT_DIR + "/" + fontFilename);
            //Typeface typeface = Typeface.createFromAsset(AndroidApplication.getAppContext().getAssets(),  fontFilename);
            cache.put(fontFilename, typeface);
            return typeface;
        }
    }
}