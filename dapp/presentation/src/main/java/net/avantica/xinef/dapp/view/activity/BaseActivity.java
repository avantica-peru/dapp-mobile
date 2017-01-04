package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.avantica.xinef.dapp.AndroidApplication;
import net.avantica.xinef.dapp.di.components.ApplicationComponent;
import net.avantica.xinef.dapp.di.modules.ActivityModule;
import net.avantica.xinef.dapp.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String NAME_PREFERENCES = "net.avantica.xinef.dapp.my_preferences";
    private static final String LATITUDE_ID = "latitude_id";
    private static final String LONGITUDE_ID = "longitude_id";
    private static final String DEPARTMENT_NAME_ID = "department_name_id";
    private static final double DEFAULT_LATITUDE_VALUE = -12.045480;
    private static final double DEFAULT_LONGITUDE_VALUE = -77.030275;

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Replaces a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where replace the fragment.
     * @param fragment        The fragment to be replaced.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private SharedPreferences getGCMPreferences(Context context) {
        return getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    protected void storeDepartmentName(Context context, String departmentName) {
        final SharedPreferences prefs = getGCMPreferences(context);
        Editor editor = prefs.edit();
        editor.putString(DEPARTMENT_NAME_ID, departmentName);
        editor.apply();
    }

    public String getDepartmentName(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        return prefs.getString(DEPARTMENT_NAME_ID, "");
    }

    protected void storeLatitudeLongitude(Context context, double latitude, double longitude) {
        final SharedPreferences prefs = getGCMPreferences(context);
        Editor editor = prefs.edit();
        editor = putDouble(editor, LATITUDE_ID, latitude);
        editor = putDouble(editor, LONGITUDE_ID, longitude);
        editor.apply();
    }

    public double getLatitude(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        return getDouble(prefs, LATITUDE_ID, DEFAULT_LATITUDE_VALUE);
    }

    public double getLongitude(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        return getDouble(prefs, LONGITUDE_ID, DEFAULT_LONGITUDE_VALUE);
    }

    private Editor putDouble(final Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }

    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }
}
