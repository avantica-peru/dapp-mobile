package net.avantica.xinef.dapp.data.net;

import net.avantica.xinef.dapp.data.BuildConfig;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import java.util.List;

import rx.Observable;

public interface RestApi {
    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST = BuildConfig.HOST + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_KEY + "&limit=50";
    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_DETAIL = BuildConfig.HOST + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_KEY + "&limit=2";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link PublicInvestmentProjectEntity}.
     */
    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link PublicInvestmentProjectEntity}.
     *
     * @param uniqueCode The unique code used to get public investment project data.
     */
    Observable<PublicInvestmentProjectEntity> publicInvestmentProjectEntityById(final String uniqueCode);
}
