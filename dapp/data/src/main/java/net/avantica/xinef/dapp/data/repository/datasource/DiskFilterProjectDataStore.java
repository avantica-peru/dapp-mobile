package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class DiskFilterProjectDataStore implements FilterProjectDataStore {

    @Override
    public Observable<List<String>> getDepartments() {
        return Observable.create(subscriber -> {
            try {

                List<String> list = new ArrayList<>();

                subscriber.onNext(list);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }

    @Override
    public Observable<List<String>> filter() {
        return null;
    }
}
