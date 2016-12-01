package net.avantica.xinef.dapp.data.repository;

import net.avantica.xinef.dapp.data.entity.mapper.PublicInvestmentProjectEntityDataMapper;
import net.avantica.xinef.dapp.data.repository.datasource.PublicInvestmentProjectDataStore;
import net.avantica.xinef.dapp.data.repository.datasource.PublicInvestmentProjectDataStoreFactory;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link PublicInvestmentProject} for retrieving user data.
 */
@Singleton
public class PublicInvestmentProjectDataRepository implements PublicInvestmentProjectRepository {
    private final PublicInvestmentProjectDataStoreFactory userDataStoreFactory;
    private final PublicInvestmentProjectEntityDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link PublicInvestmentProject}.
     *
     * @param userDataStoreFactory A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link PublicInvestmentProjectEntityDataMapper}.
     */
    @Inject
    public PublicInvestmentProjectDataRepository(PublicInvestmentProjectDataStoreFactory userDataStoreFactory, PublicInvestmentProjectEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = userDataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @Override
    public Observable<List<PublicInvestmentProject>> users() {
        //we always get all users from the cloud
        final PublicInvestmentProjectDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.publicInvestmentProjectEntityList().map(this.userEntityDataMapper::transform);
    }

    @Override
    public Observable<PublicInvestmentProject> user(String uniqueCode) {
        final PublicInvestmentProjectDataStore userDataStore = this.userDataStoreFactory.create(uniqueCode);
        return userDataStore.publicInvestmentProjectEntityDetails(uniqueCode).map(this.userEntityDataMapper::transform);
    }
}
