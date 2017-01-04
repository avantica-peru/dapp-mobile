package net.avantica.xinef.dapp.data.repository.datasource;

import rx.Observable;

public interface ReverseGeocodingDataStore {
    Observable<String> departmentNameFromLatitudeLongitude(double latitude, double longitude);
}
