package net.avantica.xinef.dapp.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;

public class FilterActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent> {
    private PublicInvestmentProjectComponent publicInvestmentProjectComponent;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        initializeInjector();
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
}
