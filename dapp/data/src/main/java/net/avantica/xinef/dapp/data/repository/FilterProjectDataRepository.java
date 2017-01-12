package net.avantica.xinef.dapp.data.repository;

import net.avantica.xinef.dapp.data.entity.mapper.PublicInvestmentProjectEntityDataMapper;
import net.avantica.xinef.dapp.data.repository.datasource.FilterProjectDataStore;
import net.avantica.xinef.dapp.data.repository.datasource.FilterProjectDataStoreFactory;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;
import net.avantica.xinef.dapp.domain.repository.FilterProjectRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class FilterProjectDataRepository implements FilterProjectRepository {
    private final FilterProjectDataStoreFactory filterProjectDataStoreFactory;
    private final PublicInvestmentProjectEntityDataMapper publicInvestmentProjectEntityDataMapper;

    @Inject
    public FilterProjectDataRepository(FilterProjectDataStoreFactory publicInvestmentProjectDataStoreFactory, PublicInvestmentProjectEntityDataMapper publicInvestmentProjectEntityDataMapper) {
        this.filterProjectDataStoreFactory = publicInvestmentProjectDataStoreFactory;
        this.publicInvestmentProjectEntityDataMapper = publicInvestmentProjectEntityDataMapper;
    }

    @Override
    public Observable<List<String>> getDepartments() {
        final FilterProjectDataStore publicInvestmentProjectDataStore = this.filterProjectDataStoreFactory.create();
        return publicInvestmentProjectDataStore.getDepartments();
    }

    @Override
    public Observable<List<PublicInvestmentProject>> filter() {
        return null;
    }
}
