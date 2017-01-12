package net.avantica.xinef.dapp.domain.repository;

import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import java.util.List;

import rx.Observable;

public interface FilterProjectRepository {
    Observable<List<String>> getDepartments();

    Observable<List<PublicInvestmentProject>> filter();
}
