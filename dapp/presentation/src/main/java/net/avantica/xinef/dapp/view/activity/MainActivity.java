package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.view.fragment.ProjectListFragment;

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
        if (savedInstanceState == null) {
            showProjectListView();
        }
    }

    private void showProjectListView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        ProjectListFragment newFragment = ProjectListFragment.newInstance();
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
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
}
