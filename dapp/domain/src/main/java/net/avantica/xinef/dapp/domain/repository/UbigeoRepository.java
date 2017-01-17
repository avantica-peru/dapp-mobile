package net.avantica.xinef.dapp.domain.repository;

import net.avantica.xinef.dapp.domain.entity.Ubigeo;

import java.util.List;

import rx.Observable;

public interface UbigeoRepository {
    Observable<List<Ubigeo>> getDepartments();

    Observable<List<Ubigeo>> getProvinces(String codDepartment);

    Observable<List<Ubigeo>> getDistricts(String codDeparment, String codProvince);
}
