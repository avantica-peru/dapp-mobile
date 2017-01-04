package net.avantica.xinef.dapp.data.repository.datasource;

import net.avantica.xinef.dapp.data.net.GeolocationApi;

import rx.Observable;

public class CloudReverseGeocodingDataStore implements ReverseGeocodingDataStore {
    private final GeolocationApi geolocationApi;

    public CloudReverseGeocodingDataStore(GeolocationApi geolocationApi) {
        this.geolocationApi = geolocationApi;
    }

    @Override
    public Observable<String> departmentNameFromLatitudeLongitude(double latitude, double longitude) {
        return this.geolocationApi.getDepartmentFromlatitudeLongitude(latitude, longitude);
    }
}
