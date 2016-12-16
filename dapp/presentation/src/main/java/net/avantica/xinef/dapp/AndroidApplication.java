package net.avantica.xinef.dapp;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.avantica.xinef.dapp.di.components.ApplicationComponent;
import net.avantica.xinef.dapp.di.components.DaggerApplicationComponent;
import net.avantica.xinef.dapp.di.modules.ApplicationModule;
import net.avantica.xinef.dapp.font.FontCache;
import net.avantica.xinef.dapp.util.Constant;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
        if (BuildConfig.DEBUG)
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        this.initializeInjector();
        this.initializeLeakDetection();

        AndroidApplication.context = getApplicationContext();

        FontCache.getInstance().addFont(Constant.AVENIR, Constant.AVENIR_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_BOOK, Constant.AVENIR_BOOK_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_BLACK, Constant.AVENIR_BLACK_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_HEAVY, Constant.AVENIR_HEAVY_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_MEDIUM, Constant.AVENIR_MEDIUM_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_ROMAN, Constant.AVENIR_ROMAN_SOURCE);
        FontCache.getInstance().addFont(Constant.AVENIR_LIGHT, Constant.AVENIR_LIGHT_SOURCE);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
        }
    }

    public static Context getAppContext() {
        return AndroidApplication.context;
    }
}
