package net.avantica.xinef.dapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.modules.PublicInvestmentProjectModule;
import net.avantica.xinef.dapp.view.fragment.ProjectDetailFragment;

public class ProjectDetailActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent> {
    private static final String INTENT_EXTRA_PARAM_UNIQUE_CODE = "net.avantica.xinef.dapp.INTENT_PARAM_CODE_PROJECT";

    private String uniqueCode;
    private PublicInvestmentProjectComponent publicInvestmentProjectComponent;

    public static Intent getCallingIntent(Context context, String codeProject) {
        Intent callingIntent = new Intent(context, ProjectDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_UNIQUE_CODE, codeProject);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.publicInvestmentProjectComponent = DaggerPublicInvestmentProjectComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .publicInvestmentProjectModule(new PublicInvestmentProjectModule(this.uniqueCode))
                .build();
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.uniqueCode = getIntent().getStringExtra(INTENT_EXTRA_PARAM_UNIQUE_CODE);
            addFragment(R.id.container, new ProjectDetailFragment());
        } else {
            this.uniqueCode = savedInstanceState.getString(INTENT_EXTRA_PARAM_UNIQUE_CODE);
        }
    }

    @Override
    public PublicInvestmentProjectComponent getComponent() {
        return this.publicInvestmentProjectComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
