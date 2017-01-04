package net.avantica.xinef.dapp.data.net;

import net.avantica.xinef.dapp.data.BuildConfig;

import rx.Observable;

public interface GeolocationApi {
    String API_URL_GET_REVERSE_GEOCODING = BuildConfig.HOST_MAPS + "json?latlng=%1$s,%2$s&key=" + BuildConfig.GOOGLE_MAPS_GEOCODING_API_KEY;

    Observable<String> getDepartmentFromlatitudeLongitude(double latitude, double lontidude);

}
