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
    private final PublicInvestmentProjectCache publicInvestmentProjectCache;

    @Inject
    public PublicInvestmentProjectDataStoreFactory(Context context, PublicInvestmentProjectCache publicInvestmentProjectCache) {
        this.context = context;
        this.publicInvestmentProjectCache = publicInvestmentProjectCache;
    }

    /**
     * Create {@link PublicInvestmentProjectDataStore} from a public investment project id
     */
    public PublicInvestmentProjectDataStore create(String publicInvestmentProjectId) {
        PublicInvestmentProjectDataStore publicInvestmentProjectDataStore;

//        if (!this.publicInvestmentProjectCache.isExpired() && this.publicInvestmentProjectCache.isCached(publicInvestmentProjectId)) {
        publicInvestmentProjectDataStore = new DiskPublicInvestmentProjectDataStore(this.publicInvestmentProjectCache);
//        } else {
//            publicInvestmentProjectDataStore = createCloudDataStore();
//        }

        return publicInvestmentProjectDataStore;
    }

    /**
     * Create {@link PublicInvestmentProjectDataStore} to retrieve data from the Cloud.
     */
    public PublicInvestmentProjectDataStore createCloudDataStore() {
        PublicInvestmentProjectEntityJsonMapper publicInvestmentProjectEntityJsonMapper = new PublicInvestmentProjectEntityJsonMapper();
        RestApi restApi = new RestApiImpl(this.context, publicInvestmentProjectEntityJsonMapper);

        return new CloudPublicInvestmentProjectDataStore(restApi, this.publicInvestmentProjectCache);
    }
}
