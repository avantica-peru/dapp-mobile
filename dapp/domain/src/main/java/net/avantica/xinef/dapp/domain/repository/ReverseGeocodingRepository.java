package net.avantica.xinef.dapp.domain.repository;

import rx.Observable;

public interface ReverseGeocodingRepository {
    Observable<String> departmentNamefromlatitudeLongitude(double latitude, double longitude);
}
