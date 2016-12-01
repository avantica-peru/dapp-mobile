package net.avantica.xinef.dapp;

import android.app.Application;

import net.avantica.xinef.dapp.BuildConfig;
import net.avantica.xinef.dapp.di.components.ApplicationComponent;
import net.avantica.xinef.dapp.di.components.DaggerApplicationComponent;
import net.avantica.xinef.dapp.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
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
}
