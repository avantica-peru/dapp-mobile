package net.avantica.xinef.dapp.view.activity;

import android.os.Bundle;

import com.raizlabs.android.dbflow.config.FlowManager;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.data.repository.AppDataBase;
import net.avantica.xinef.dapp.di.HasComponent;
import net.avantica.xinef.dapp.di.components.DaggerPublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.di.modules.PublicInvestmentProjectModule;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.view.fragment.SplashFragment;

import java.util.List;

public class SplashActivity extends BaseActivity implements HasComponent<PublicInvestmentProjectComponent>, SplashFragment.SplashListener {

    private PublicInvestmentProjectComponent publicInvestmentProjectComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            FlowManager.getDatabase(AppDataBase.class).reset(getApplicationContext());
//            FlowManager.destroy();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        this.initializeInjector();

        setContentView(R.layout.activity_splash);

        addFragment(R.id.fragment_container, SplashFragment.newInstance());

    }

    @Override
    public void successfulLoad(List<PublicInvestmentProjectModel> publicInvestmentProjectModels) {
        this.navigator.navigateToPublicInvestmentProjectrList(this, publicInvestmentProjectModels);
    }

    @Override
    public void loadFailed() {

    }

    private void initializeInjector() {
        this.publicInvestmentProjectComponent = DaggerPublicInvestmentProjectComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .publicInvestmentProjectModule(new PublicInvestmentProjectModule(true))
                .build();
    }

    @Override
    public PublicInvestmentProjectComponent getComponent() {
        return this.publicInvestmentProjectComponent;
    }
}
