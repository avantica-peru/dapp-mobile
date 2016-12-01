package net.avantica.xinef.dapp.data.net;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import java.util.List;

import rx.Observable;

public interface RestApi {

    String API_BASE_URL = "";

    String API_URL_LOGIN = API_BASE_URL + "";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link PublicInvestmentProjectEntity}.
     */
    Observable<List<PublicInvestmentProjectEntity>> userEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link PublicInvestmentProjectEntity}.
     *
     * @param userId The user id used to get user data.
     */
    Observable<PublicInvestmentProjectEntity> userEntityById(final int userId);
}
