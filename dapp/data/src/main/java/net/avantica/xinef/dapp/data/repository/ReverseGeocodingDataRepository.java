package net.avantica.xinef.dapp.data.repository;

import net.avantica.xinef.dapp.data.repository.datasource.ReverseGeocodingDataStore;
import net.avantica.xinef.dapp.data.repository.datasource.ReverseGeocodingDataStoreFactory;
import net.avantica.xinef.dapp.domain.repository.ReverseGeocodingRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ReverseGeocodingDataRepository implements ReverseGeocodingRepository {
    private final ReverseGeocodingDataStoreFactory reverseGeocodingDataStoreFactory;

    @Inject
    public ReverseGeocodingDataRepository(ReverseGeocodingDataStoreFactory reverseGeocodingDataStoreFactory) {
        this.reverseGeocodingDataStoreFactory = reverseGeocodingDataStoreFactory;
    }

    @Override
    public Observable<String> departmentNamefromlatitudeLongitude(double latitude, double longitude) {
        final ReverseGeocodingDataStore reverseGeocodingDataStore = this.reverseGeocodingDataStoreFactory.createCloudDataStore();
        return reverseGeocodingDataStore.departmentNameFromLatitudeLongitude(latitude, longitude);
    }
}
