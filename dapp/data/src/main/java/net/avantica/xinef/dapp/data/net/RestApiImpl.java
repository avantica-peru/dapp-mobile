package net.avantica.xinef.dapp.data.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import net.avantica.xinef.dapp.data.annotation.RxLogObservable;
import net.avantica.xinef.dapp.data.entity.Array;
import net.avantica.xinef.dapp.data.entity.PIPResult;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.data.entity.mapper.PublicInvestmentProjectEntityJsonMapper;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

    private final Context context;
    private final PublicInvestmentProjectEntityJsonMapper publicInvestmentProjectEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context                                 {@link Context}.
     * @param publicInvestmentProjectEntityJsonMapper {@link PublicInvestmentProjectEntityJsonMapper}.
     */
    public RestApiImpl(Context context, PublicInvestmentProjectEntityJsonMapper publicInvestmentProjectEntityJsonMapper) {
        this.context = context;
        this.publicInvestmentProjectEntityJsonMapper = publicInvestmentProjectEntityJsonMapper;
    }

    @RxLogObservable
    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList() {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserEntities = getPublicInvestmentProjectListFromApi();

                    PIPResult pipResult = publicInvestmentProjectEntityJsonMapper.transformPublicInvestmentProjectEntity(responseUserEntities);

                    final List<PublicInvestmentProjectEntity> entities = new ArrayList<>();
                    final List<Array> arrayResult = pipResult.getResult().getfArray();
                    int totalCols = pipResult.getResult().getfCols();
                    int totalSize = pipResult.getResult().getfArray().size();
                    int row = 0;


                    Array array;
                    int x;

                    for (int i = totalCols; i < totalSize; i += totalCols) {
                        x = i;
                        final PublicInvestmentProjectEntity entity = new PublicInvestmentProjectEntity();

//                        array = arrayResult.get(x);
//                        entity.setDepartment(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.setProvince(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.setDistrict(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.setZipCode(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.setLatitude(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.setLongitude(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;
//
//                        array = arrayResult.get(x);
//                        entity.set(array.getfStr());
//                        x++;

                        entities.add(entity);
                    }

                    subscriber.onNext(entities);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<PublicInvestmentProjectEntity> publicInvestmentProjectEntityById(String uniqueCode) {
        return null;
    }

    private String getPublicInvestmentProjectListFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST).requestSyncCall();
    }

    private String getPublicInvestmentProjectDetailsFromApi(int userId) throws MalformedURLException {
        String apiUrl = API_URL_GET_PUBLIC_INVESTMENT_PROJECT_DETAIL;
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    public String getAppVersion() {
        if (context != null) {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = null;
            try {
                info = manager.getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("PackageManagerError", e.getMessage());
            }
            if (info != null) {
                return info.versionName;
            }
            return "";
        } else {
            return "";
        }
    }
}
