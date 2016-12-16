package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.view.fragment.ProjectListFragment;
import net.avantica.xinef.dapp.view.fragment.ProjectsMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent>, ProjectListFragment.PublicInvestmentProjectListListener {
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

        this.initializeInjector();

        replaceFragment(R.id.container, ProjectListFragment.newInstance());
    }

    private void showProjectListView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ProjectListFragment newFragment = ProjectListFragment.newInstance();
        fragmentTransaction.replace(R.id.container, newFragment, ProjectListFragment.class.getName());
        fragmentTransaction.commit();
    }

    private void initializeInjector() {
        this.publicInvestmentProjectComponent = DaggerPublicInvestmentProjectComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public PublicInvestmentProjectComponent getComponent() {
        return this.publicInvestmentProjectComponent;
    }

    @Override
    public void onPublicInvestmentProjectClicked(PublicInvestmentProjectModel userModel) {
        this.navigator.navigateToPublicInvestmentProjectDetails(this, userModel.getUniqueCode());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_maps_map:
                replaceFragment(R.id.container, ProjectsMapFragment.newInstance());
                return true;
            case R.id.action_action_view_list:
                replaceFragment(R.id.container, ProjectListFragment.newInstance());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
