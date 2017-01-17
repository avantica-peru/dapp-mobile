package net.avantica.xinef.dapp.data.repository;

import net.avantica.xinef.dapp.data.entity.mapper.UbigeoEntityDataMapper;
import net.avantica.xinef.dapp.data.repository.datasource.UbigeoDataStore;
import net.avantica.xinef.dapp.data.repository.datasource.UbigeoDataStoreFactory;
import net.avantica.xinef.dapp.domain.entity.Ubigeo;
import net.avantica.xinef.dapp.domain.repository.UbigeoRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class UbigeoDataRepository implements UbigeoRepository {
    private final UbigeoDataStoreFactory ubigeoDataStoreFactory;
    private final UbigeoEntityDataMapper ubigeoEntityDataMapper;

    @Inject
    public UbigeoDataRepository(UbigeoDataStoreFactory publicInvestmentProjectDataStoreFactory, UbigeoEntityDataMapper ubigeoEntityDataMapper) {
        this.ubigeoDataStoreFactory = publicInvestmentProjectDataStoreFactory;
        this.ubigeoEntityDataMapper = ubigeoEntityDataMapper;
    }

    @Override
    public Observable<List<Ubigeo>> getDepartments() {
        final UbigeoDataStore ubigeoDataStore = this.ubigeoDataStoreFactory.create();
        return ubigeoDataStore.getDepartments().map(this.ubigeoEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Ubigeo>> getProvinces(String codDeparment) {
        final UbigeoDataStore ubigeoDataStore = this.ubigeoDataStoreFactory.create();
        return ubigeoDataStore.getProvinces(codDeparment).map(this.ubigeoEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Ubigeo>> getDistricts(String codDepartment, String codProvince) {
        final UbigeoDataStore ubigeoDataStore = this.ubigeoDataStoreFactory.create();
        return ubigeoDataStore.getDistricts(codDepartment, codProvince).map(this.ubigeoEntityDataMapper::transform);
    }
}
