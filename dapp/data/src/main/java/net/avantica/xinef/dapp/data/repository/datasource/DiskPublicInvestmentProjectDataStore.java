package net.avantica.xinef.dapp.data.repository.datasource;

import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;

import net.avantica.xinef.dapp.data.cache.PublicInvestmentProjectCache;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity_Table;
import net.avantica.xinef.dapp.data.exception.NetworkConnectionException;
import net.avantica.xinef.dapp.data.exception.PublicInvestmentProjectNotFoundException;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import java.util.List;

import rx.Observable;

/**
 * {@link PublicInvestmentProjectDataStore} implementation based on file system data store.
 */
public class DiskPublicInvestmentProjectDataStore implements PublicInvestmentProjectDataStore {

    private final PublicInvestmentProjectCache publicInvestmentProjectCache;

    /**
     * Construct a {@link PublicInvestmentProjectDataStore} based file system data store.
     *
     * @param publicInvestmentProjectCache A {@link PublicInvestmentProject} to cache data retrieved from the api.
     */
    public DiskPublicInvestmentProjectDataStore(PublicInvestmentProjectCache publicInvestmentProjectCache) {
        this.publicInvestmentProjectCache = publicInvestmentProjectCache;
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityList() {
        return Observable.create(subscriber -> {
            try {
                final Select select = SQLite.select();
                final From from = select.from(PublicInvestmentProjectEntity.class);
                final Where where = from.groupBy(PublicInvestmentProjectEntity_Table.UniqueCode);
                List<PublicInvestmentProjectEntity> list = where.queryList();

                subscriber.onNext(list);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }

    @Override
    public Observable<List<PublicInvestmentProjectEntity>> publicInvestmentProjectEntityDetails(String uniqueCode) {
        return Observable.create(subscriber -> {
            try {
                final Select select = SQLite.select();
                final From from = select.from(PublicInvestmentProjectEntity.class);
                final Where where = from.where(PublicInvestmentProjectEntity_Table.UniqueCode.like(uniqueCode));

                List<PublicInvestmentProjectEntity> publicInvestmentProjectEntity = where.queryList();

                if (publicInvestmentProjectEntity != null) {
                    subscriber.onNext(publicInvestmentProjectEntity);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(new PublicInvestmentProjectNotFoundException());
            }
        });
    }

    private boolean isStringValid(String string) {
        return string != null && !string.isEmpty();
    }
}
