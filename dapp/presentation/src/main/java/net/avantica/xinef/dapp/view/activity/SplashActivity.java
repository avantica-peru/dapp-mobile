package net.avantica.xinef.dapp.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.RelativeLayout;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.modules.PublicInvestmentProjectModule;
import net.avantica.xinef.dapp.util.TrackGPS;
import net.avantica.xinef.dapp.view.fragment.SplashFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent>, SplashFragment.SplashListener {
    @BindView(R.id.rl_splash)
    RelativeLayout splashRelativeLayout;

    private PublicInvestmentProjectComponent publicInvestmentProjectComponent;
    private final int PERMISSION_ACCESS_COARSE_LOCATION = 100;

    private TrackGPS gps;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int accessCoarseLocation = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFineLocation = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestCoarseLocation();
            } else {
                getLocation();
            }
        } else {
            getLocation();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void requestCoarseLocation() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_ACCESS_COARSE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(splashRelativeLayout, R.string.gps_is_required, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.retry, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    requestCoarseLocation();
                                }
                            });

                    snackbar.show();
                }
                break;
        }
    }

    private void getLocation() {
        gps = new TrackGPS(this);

        if (gps.canGetLocation()) {
            storeLatitudeLongitude(this, gps.getLatitude(), gps.getLongitude());
            this.initializeInjector(gps.getLatitude(), gps.getLongitude());
            addFragment(R.id.fragment_container, SplashFragment.newInstance());
        } else {
            gps.showSettingsAlert();
        }
    }

    @Override
    public void success(String departmentName) {
        storeDepartmentName(this, departmentName);
        this.navigator.navigateToMainActivity(this);
    }

    @Override
    public void error() {

    }

    private void initializeInjector(double latitude, double longitude) {
        this.publicInvestmentProjectComponent = DaggerPublicInvestmentProjectComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .publicInvestmentProjectModule(new PublicInvestmentProjectModule(latitude, longitude))
                .build();
    }

    @Override
    public PublicInvestmentProjectComponent getComponent() {
        return this.publicInvestmentProjectComponent;
    }
}
