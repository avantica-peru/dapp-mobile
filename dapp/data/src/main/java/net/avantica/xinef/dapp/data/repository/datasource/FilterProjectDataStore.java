package net.avantica.xinef.dapp.data.repository.datasource;

import java.util.List;

import rx.Observable;

public interface FilterProjectDataStore {
    Observable<List<String>> getDepartments();

    Observable<List<String>> filter();
}
