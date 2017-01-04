package net.avantica.xinef.dapp.di.modules;

import android.content.Context;

import net.avantica.xinef.dapp.AndroidApplication;
import net.avantica.xinef.dapp.UIThread;
import net.avantica.xinef.dapp.data.cache.PublicInvestmentProjectCache;
import net.avantica.xinef.dapp.data.cache.PublicInvestmentProjectCacheImpl;
import net.avantica.xinef.dapp.data.executor.JobExecutor;
import net.avantica.xinef.dapp.data.repository.PublicInvestmentProjectDataRepository;
import net.avantica.xinef.dapp.data.repository.ReverseGeocodingDataRepository;
import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;
import net.avantica.xinef.dapp.domain.repository.ReverseGeocodingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    PublicInvestmentProjectCache providePublicInvestmentProjectCache(PublicInvestmentProjectCacheImpl publicInvestmentProjectCache) {
        return publicInvestmentProjectCache;
    }

    @Provides
    @Singleton
    PublicInvestmentProjectRepository providePublicInvestmentProjectRepository(PublicInvestmentProjectDataRepository publicInvestmentProjectDataRepository) {
        return publicInvestmentProjectDataRepository;
    }

    @Provides
    @Singleton
    ReverseGeocodingRepository provideReverseGeocodingRepository(ReverseGeocodingDataRepository reverseGeocodingDataRepository) {
        return reverseGeocodingDataRepository;
    }
}
