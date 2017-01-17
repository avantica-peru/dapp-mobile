package net.avantica.xinef.dapp.data.repository.datasource;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.avantica.xinef.dapp.data.entity.UbigeoEntity;
import net.avantica.xinef.dapp.data.entity.UbigeoEntity_Table;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;

import java.util.List;

import rx.Observable;

public class DiskUbigeoDataStore implements UbigeoDataStore {

    @Override
    public Observable<List<UbigeoEntity>> getDepartments() {
        return Observable.create(subscriber -> {
            try {
                final List<UbigeoEntity> departments = SQLite.select().from(UbigeoEntity.class).where(UbigeoEntity_Table.codProv.eq("00")).and(UbigeoEntity_Table.codDist.eq("00")).queryList();

                subscriber.onNext(departments);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }

    @Override
    public Observable<List<UbigeoEntity>> getProvinces(String codDepartment) {
        return Observable.create(subscriber -> {
            try {
                final List<UbigeoEntity> provinces = SQLite.select().from(UbigeoEntity.class).where(UbigeoEntity_Table.codDpto.eq(codDepartment)).and(UbigeoEntity_Table.codProv.notEq("00")).and(UbigeoEntity_Table.codDist.eq("00")).queryList();

                subscriber.onNext(provinces);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }

    @Override
    public Observable<List<UbigeoEntity>> getDistricts(String codDepartment, String codProvince) {
        return Observable.create(subscriber -> {
            try {
                final List<UbigeoEntity> districts = SQLite.select().from(UbigeoEntity.class).where(UbigeoEntity_Table.codDpto.eq(codDepartment)).and(UbigeoEntity_Table.codProv.eq(codProvince)).and(UbigeoEntity_Table.codDist.notEq("00")).queryList();

                subscriber.onNext(districts);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }
}
