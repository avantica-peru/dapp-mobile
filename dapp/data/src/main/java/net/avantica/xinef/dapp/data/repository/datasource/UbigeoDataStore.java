package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.entity.UbigeoEntity;

import java.util.List;

import rx.Observable;

public interface UbigeoDataStore {
    Observable<List<UbigeoEntity>> getDepartments();
    Observable<List<UbigeoEntity>> getProvinces(String codDepartment);
    Observable<List<UbigeoEntity>> getDistricts(String codDepartment, String codProvince);
}
