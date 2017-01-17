package net.avantica.xinef.dapp.di.components;

import android.content.Context;

import net.avantica.xinef.dapp.di.modules.ApplicationModule;
import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.UbigeoRepository;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;
import net.avantica.xinef.dapp.domain.repository.ReverseGeocodingRepository;
import net.avantica.xinef.dapp.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    PublicInvestmentProjectRepository publicInvestmentProjectRepository();

    ReverseGeocodingRepository reverseGeocodingRepository();

    UbigeoRepository filterProjectRepository();
}
