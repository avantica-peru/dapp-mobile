package net.avantica.xinef.dapp.data.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Delete;

import net.avantica.xinef.dapp.data.annotation.RxLogObservable;
import net.avantica.xinef.dapp.data.entity.Array;
import net.avantica.xinef.dapp.data.entity.PIPResult;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.data.entity.mapper.PublicInvestmentProjectEntityJsonMapper;
import net.avantica.xinef.dapp.data.exception.JsonException;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
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
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList(String departmentName) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {

                    String departmentNameNew = clearDepartmentName(departmentName);


                    String responseJson = getPublicInvestmentProjectListFromApi(departmentNameNew);

                    PIPResult pipResult = publicInvestmentProjectEntityJsonMapper.transformPublicInvestmentProjectEntity(responseJson);

                    final List<PublicInvestmentProjectEntity> entities = new ArrayList<>();
                    final List<Array> arrayResult = pipResult.getResult().getfArray();
                    int totalCols = pipResult.getResult().getfCols();
                    int totalSize = pipResult.getResult().getfArray().size();

                    Array array;
                    int col;

//                    String[] columns = {"department", "province", "district", "zipCode"};

                    Delete.table(PublicInvestmentProjectEntity.class);

                    for (int row = totalCols; row < totalSize; row += totalCols) {
                        col = row;
                        final PublicInvestmentProjectEntity entity = new PublicInvestmentProjectEntity();

                        array = arrayResult.get(col);
                        entity.setDepartment(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setProvince(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setDistrict(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setZipCode(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setLatitude(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setLongitude(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setPopulatedCenter(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setFormulatingUnit(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setSector(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setFolder(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setExecutor(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setLevel(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setSnipCode(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setUniqueCode(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setName(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setFunction(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setProgram(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setSubprogram(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setFundingSource(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setRegistrationDate(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setSituation(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setState(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setClosed(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setViabDate(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setViableAmount(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setBeneficiary(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setObjective(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setAlternative(array.getfStr());
                        col++;

                        array = arrayResult.get(col);
                        entity.setCost(array.getfStr());
                        col++;

//                        entity.setId(entities.size() + 1);

                        entity.save();
                        entities.add(entity);
                    }

                    subscriber.onNext(entities);
                    subscriber.onCompleted();
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

    private String clearDepartmentName(String departmentName) {
        String aux = new String(departmentName);
        aux = aux.toUpperCase();
        aux = aux.replaceAll("DEPARTAMENTO", "");
        aux = aux.replaceAll("PROVINCIA", "");
        aux = aux.replaceAll("DISTRITO", "");
        aux = aux.replaceAll("DEPARTMENT", "");
        aux = aux.replaceAll("PROVINCE", "");
        aux = aux.replaceAll("REGION", "");
        aux = aux.replaceAll("DE", "");
        aux = aux.replaceAll("CUZCO", "CUSCO");
        return aux.trim();
    }

    @RxLogObservable
    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityById(String snipCode) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) try {
                String responseJson = getPublicInvestmentProjectDetailsFromApi(snipCode);

                PIPResult pipResult = publicInvestmentProjectEntityJsonMapper.transformPublicInvestmentProjectEntity(responseJson);

                subscriber.onNext(null);
                subscriber.onCompleted();
            } catch (SocketTimeoutException e) {
                subscriber.onError(new SocketTimeoutException());
            } catch (Exception e) {
                subscriber.onError(new JsonException(e.getCause()));
            }
            else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    private String getPublicInvestmentProjectListFromApi(String departmentName) throws IOException {
//        return ApiConnection.createGET(API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST).requestSyncCall();
        String url = API_URL_GET_PUBLIC_INVESTMENT_PROJECT_LIST_BY_DEPARTMENT + departmentName;
        return ApiConnection.createGET(url).requestSyncCall();
    }

    private String getPublicInvestmentProjectDetailsFromApi(String uniqueCode) throws IOException {
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
