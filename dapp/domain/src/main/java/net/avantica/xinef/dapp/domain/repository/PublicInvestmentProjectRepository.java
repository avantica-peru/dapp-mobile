package net.avantica.xinef.dapp.domain.repository;

import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link PublicInvestmentProject} related data.
 */
public interface PublicInvestmentProjectRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link PublicInvestmentProject}.
     */
    Observable<List<PublicInvestmentProject>> users();

    /**
     * Get an {@link Observable} which will emit a {@link PublicInvestmentProject}.
     *
     * @param userId The user id used to retrieve user data.
     */
    Observable<PublicInvestmentProject> user(final String userId);
}
