package net.avantica.xinef.dapp.data.net;

import net.avantica.xinef.dapp.data.BuildConfig;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import java.util.List;

import rx.Observable;

public interface RestApi {
    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST = BuildConfig.HOST_MEF + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_MEF_KEY + "&limit=50";
//    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST_BY_DEPARTMENT = BuildConfig.HOST_MEF + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_MEF_KEY + "&query=&qtype=column0&filter0=column0%5B%3D%3D%5D";
    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST_BY_DEPARTMENT = BuildConfig.HOST_MEF + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_MEF_KEY + "&query=&qtype=column0&filter0=column0%5B%3D%3D%5D";
    String API_URL_GET_PUBLIC_INVESTMENT_PROJECT_DETAIL = BuildConfig.HOST_MEF + "datastreams/PIP-GEORE-DE-GOBIE-LOCAL/data.json/?auth_key=" + BuildConfig.AUTH_MEF_KEY + "&limit=2";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link PublicInvestmentProjectEntity}.
     */
    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList(String departmentName, int page);

    /**
     * Retrieves an {@link Observable} which will emit a {@link PublicInvestmentProjectEntity}.
     *
     * @param uniqueCode The unique code used to get public investment project data.
     */
    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityById(final String uniqueCode);
}
