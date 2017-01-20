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
    private final PublicInvestmentProjectCache publicInvestmentProjectCache;

    private final Action1<List<PublicInvestmentProjectEntity>> saveToDataBase = publicInvestmentProjectEntityList -> {
        if (publicInvestmentProjectEntityList != null) {
            
        }
    };

    public CloudPublicInvestmentProjectDataStore(RestApi restApi, PublicInvestmentProjectCache publicInvestmentProjectCache) {
        this.restApi = restApi;
        this.publicInvestmentProjectCache = publicInvestmentProjectCache;
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList(String departmentName, int page) {
        return this.restApi.publicInvestmentProjectEntityList(departmentName, page).doOnNext(saveToDataBase);
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityFilteredList(String ubigeo, String snipCode) {
        return null;
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityDetails(String snipCode) {
        return this.restApi.publicInvestmentProjectEntityById(snipCode);
    }
}
