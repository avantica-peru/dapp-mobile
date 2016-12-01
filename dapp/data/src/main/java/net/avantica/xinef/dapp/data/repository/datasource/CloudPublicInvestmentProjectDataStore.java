package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.cache.PublicInvestmentProjectCache;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.data.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link PublicInvestmentProjectDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudPublicInvestmentProjectDataStore implements PublicInvestmentProjectDataStore {

    private final RestApi restApi;
    private final PublicInvestmentProjectCache userCache;

    private final Action1<PublicInvestmentProjectEntity> saveTocacheAction = userEntity -> {
        if (userEntity != null) {
            CloudPublicInvestmentProjectDataStore.this.userCache.put(userEntity);
        }
    };

    public CloudPublicInvestmentProjectDataStore(RestApi restApi, PublicInvestmentProjectCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList() {
        return null;
    }

    @Override
    public Observable<PublicInvestmentProjectEntity> publicInvestmentProjectEntityDetails(String userId) {
        return null;
    }
}
