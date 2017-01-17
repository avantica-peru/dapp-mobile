package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.modules.PublicInvestmentProjectModule;
import net.avantica.xinef.dapp.view.fragment.BaseFragment;
import net.avantica.xinef.dapp.view.fragment.FilterDialogFragment;
import net.avantica.xinef.dapp.view.fragment.ProjectListFragment;
import net.avantica.xinef.dapp.view.fragment.ProjectsMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent>, BaseFragment.PublicInvestmentProjectListListener, FilterDialogFragment.OnFiltersSelectedListener {
    private double latitude;
    private double longitude;
    private String departmentName;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PublicInvestmentProjectComponent publicInvestmentProjectComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        latitude = getLatitude(this);
        longitude = getLongitude(this);
        departmentName = getDepartmentName(this);

        this.initializeInjector();

        replaceFragment(R.id.container, ProjectsMapFragment.newInstance(latitude, longitude));
    }

    private void initializeInjector() {
        PublicInvestmentProjectModule publicInvestmentProjectModule = new PublicInvestmentProjectModule();
        publicInvestmentProjectModule.setDepartmentName(departmentName);

        this.publicInvestmentProjectComponent = DaggerPublicInvestmentProjectComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .publicInvestmentProjectModule(publicInvestmentProjectModule)
                .build();
    }

    @Override
    public PublicInvestmentProjectComponent getComponent() {
        return this.publicInvestmentProjectComponent;
    }

    @Override
    public void onPublicInvestmentProjectClicked(String uniqueCode) {
        this.navigator.navigateToPublicInvestmentProjectDetails(this, uniqueCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_maps_map:
                replaceFragment(R.id.container, ProjectsMapFragment.newInstance(latitude, longitude));
                return true;
            case R.id.action_action_view_list:
                replaceFragment(R.id.container, ProjectListFragment.newInstance());
                return true;
            case R.id.action_filter:
                showFilterDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFilterDialog() {
        DialogFragment newFragment = FilterDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onUbigeoEntered(String ubigeo) {
        Fragment projectListFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        Toast.makeText(this, ubigeo, Toast.LENGTH_SHORT).show();
    }
}
