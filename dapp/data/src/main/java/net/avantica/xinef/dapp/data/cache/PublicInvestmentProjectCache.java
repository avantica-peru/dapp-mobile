package net.avantica.xinef.dapp.data.cache;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import rx.Observable;

public interface PublicInvestmentProjectCache {
    /**
     * Gets an {@link Observable} which will emit a {@link PublicInvestmentProjectEntity}.
     *
     * @param uniqueCode The user id to retrieve data.
     */
    Observable<PublicInvestmentProjectEntity> get(final String uniqueCode);

    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    void put(PublicInvestmentProjectEntity userEntity);

    /**
     * Checks if an element ({@link PublicInvestmentProject}) exists in the cache.
     *
     * @param uniqueCode The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String uniqueCode);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
