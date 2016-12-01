package net.avantica.xinef.dapp.data.repository.datasource;

import android.content.Context;

import net.avantica.xinef.dapp.data.cache.PublicInvestmentProjectCache;
import net.avantica.xinef.dapp.data.entity.mapper.PublicInvestmentProjectEntityJsonMapper;
import net.avantica.xinef.dapp.data.net.RestApi;
import net.avantica.xinef.dapp.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PublicInvestmentProjectDataStoreFactory {
    private final Context context;
    private final PublicInvestmentProjectCache userCache;

    @Inject
    public PublicInvestmentProjectDataStoreFactory(Context context, PublicInvestmentProjectCache userCache) {
        this.context = context;
        this.userCache = userCache;
    }

    /**
     * Create {@link PublicInvestmentProjectDataStore} from a user id
     */
    public PublicInvestmentProjectDataStore create(String userId) {
        PublicInvestmentProjectDataStore publicInvestmentProjectDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
            publicInvestmentProjectDataStore = new DiskPublicInvestmentProjectDataStore(this.userCache);
        } else {
            publicInvestmentProjectDataStore = createCloudDataStore();
        }

        return publicInvestmentProjectDataStore;
    }

    /**
     * Create {@link PublicInvestmentProjectDataStore} to retrieve data from the Cloud.
     */
    public PublicInvestmentProjectDataStore createCloudDataStore() {
        PublicInvestmentProjectEntityJsonMapper publicInvestmentProjectEntityJsonMapper = new PublicInvestmentProjectEntityJsonMapper();
        RestApi restApi = new RestApiImpl(this.context, publicInvestmentProjectEntityJsonMapper);

        return new CloudPublicInvestmentProjectDataStore(restApi, this.userCache);
    }
}
