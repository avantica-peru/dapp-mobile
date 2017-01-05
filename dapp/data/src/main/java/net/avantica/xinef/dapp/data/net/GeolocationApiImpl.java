package net.avantica.xinef.dapp.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.avantica.xinef.dapp.data.annotation.RxLogObservable;
import net.avantica.xinef.dapp.data.entity.AddressComponent;
import net.avantica.xinef.dapp.data.entity.ReverseGeocodingEntity;
import net.avantica.xinef.dapp.data.entity.ReverseGeocodingResult;
import net.avantica.xinef.dapp.data.entity.mapper.ReverseGeocodingEntityJsonMapper;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;
import net.avantica.xinef.dapp.data.exception.ReverseGeocodingNotFoundException;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import rx.Observable;

public class GeolocationApiImpl implements GeolocationApi {

    private static final String ADMINISTRATIVE_AREA_LEVEL_1 = "administrative_area_level_1";
    private final Context context;
    private final ReverseGeocodingEntityJsonMapper reverseGeocodingEntityJsonMapper;

    public GeolocationApiImpl(Context context, ReverseGeocodingEntityJsonMapper reverseGeocodingEntityJsonMapper) {
        this.context = context;
        this.reverseGeocodingEntityJsonMapper = reverseGeocodingEntityJsonMapper;
    }

    @RxLogObservable
    @Override
    public Observable<String> getDepartmentFromlatitudeLongitude(double latitude, double longitude) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String response = getDepartmentFromLatitudeLongitudeFromApi(latitude, longitude);

                    ReverseGeocodingEntity reverseGeocodingEntity = reverseGeocodingEntityJsonMapper.transformReverseGeocodingEntity(response);

                    if (reverseGeocodingEntity.getResults().size() > 0) {
                        ReverseGeocodingResult reverseGeocodingResult = reverseGeocodingEntity.getResults().get(0);

                        String departmentName = "";
                        outerloop:
                        for (AddressComponent addressComponent : reverseGeocodingResult.getAddressComponents()) {
                            for (String type : addressComponent.getTypes()) {
                                if (ADMINISTRATIVE_AREA_LEVEL_1.equalsIgnoreCase(type)) {
                                    departmentName = addressComponent.getLongName().toUpperCase();
                                    break outerloop;
                                }
                            }
                        }

                        subscriber.onNext(departmentName);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new ReverseGeocodingNotFoundException());
                    }
                } catch (SocketTimeoutException e) {
                    subscriber.onError(new SocketTimeoutException());
                } catch (Exception e) {
                    subscriber.onError(new JSONException(e.getMessage()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    protected String getDepartmentFromLatitudeLongitudeFromApi(double latitude, double longitude) throws IOException {
        return ApiConnection.createGET(String.format(API_URL_GET_REVERSE_GEOCODING, latitude, longitude)).requestSyncCall();
    }

    protected boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
