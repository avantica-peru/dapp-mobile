package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import java.util.List;

import rx.Observable;

public interface PublicInvestmentProjectDataStore {

    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList(final String departmentName, int page);

    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityFilteredList(String ubigeo, String snipCode);

    Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityDetails(final String uniqueCode);
}
