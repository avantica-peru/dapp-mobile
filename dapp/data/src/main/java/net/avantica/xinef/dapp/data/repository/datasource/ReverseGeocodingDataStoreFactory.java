package net.avantica.xinef.dapp.data.repository.datasource;

import android.content.Context;

import net.avantica.xinef.dapp.data.entity.mapper.ReverseGeocodingEntityJsonMapper;
import net.avantica.xinef.dapp.data.net.GeolocationApi;
import net.avantica.xinef.dapp.data.net.GeolocationApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReverseGeocodingDataStoreFactory {
    private final Context context;

    @Inject
    public ReverseGeocodingDataStoreFactory(Context context) {
        this.context = context;
    }

    public ReverseGeocodingDataStore createCloudDataStore() {
        ReverseGeocodingEntityJsonMapper reverseGeocodingEntityJsonMapper = new ReverseGeocodingEntityJsonMapper();
        GeolocationApi geolocationApi = new GeolocationApiImpl(this.context, reverseGeocodingEntityJsonMapper);

        return new CloudReverseGeocodingDataStore(geolocationApi);
    }
}
