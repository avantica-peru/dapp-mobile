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
 * {@link PublicInvestmentProject} for retrieving publicInvestmentProject data.
 */
@Singleton
public class PublicInvestmentProjectDataRepository implements PublicInvestmentProjectRepository {
    private final PublicInvestmentProjectDataStoreFactory publicInvestmentProjectDataStoreFactory;
    private final PublicInvestmentProjectEntityDataMapper publicInvestmentProjectEntityDataMapper;

    /**
     * Constructs a {@link PublicInvestmentProject}.
     *
     * @param publicInvestmentProjectDataStoreFactory A factory to construct different data source implementations.
     * @param publicInvestmentProjectEntityDataMapper {@link PublicInvestmentProjectEntityDataMapper}.
     */
    @Inject
    public PublicInvestmentProjectDataRepository(PublicInvestmentProjectDataStoreFactory publicInvestmentProjectDataStoreFactory, PublicInvestmentProjectEntityDataMapper publicInvestmentProjectEntityDataMapper) {
        this.publicInvestmentProjectDataStoreFactory = publicInvestmentProjectDataStoreFactory;
        this.publicInvestmentProjectEntityDataMapper = publicInvestmentProjectEntityDataMapper;
    }

    @Override
    public Observable<List<PublicInvestmentProject>> publicInvestmentProjects(String departmentName, int page) {
        //we always get all publicInvestmentProjects from the cloud
        final PublicInvestmentProjectDataStore publicInvestmentProjectDataStore = this.publicInvestmentProjectDataStoreFactory.createCloudDataStore();
        return publicInvestmentProjectDataStore.publicInvestmentProjectEntityList(departmentName, page).map(this.publicInvestmentProjectEntityDataMapper::transform);
    }

    @Override
    public Observable<List<PublicInvestmentProject>> publicInvestmentProjectsFiltered(String ubigeo, String snipCode) {
        final PublicInvestmentProjectDataStore publicInvestmentProjectDataStore = this.publicInvestmentProjectDataStoreFactory.createCloudDataStore();
        return publicInvestmentProjectDataStore.publicInvestmentProjectEntityFilteredList(ubigeo, snipCode).map(this.publicInvestmentProjectEntityDataMapper::transform);
    }

    @Override
    public Observable<List<PublicInvestmentProject>> publicInvestmentProject(String snipCode) {
        final PublicInvestmentProjectDataStore publicInvestmentProjectDataStore = this.publicInvestmentProjectDataStoreFactory.create();
        return publicInvestmentProjectDataStore.publicInvestmentProjectEntityDetails(snipCode).map(this.publicInvestmentProjectEntityDataMapper::transform);
    }
}
