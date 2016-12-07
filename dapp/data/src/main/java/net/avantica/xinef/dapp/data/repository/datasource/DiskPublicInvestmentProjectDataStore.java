package net.avantica.xinef.dapp.data.repository.datasource;

import com.raizlabs.android.dbflow.sql.language.Select;

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
                List<PublicInvestmentProjectEntity> list = new Select().from(PublicInvestmentProjectEntity.class).as("PublicInvestmentProject").queryList();

                subscriber.onNext(list);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }

    @Override
    public Observable<PublicInvestmentProjectEntity> publicInvestmentProjectEntityDetails(String uniqueCode) {
        return Observable.create(subscriber -> {
            try {
                PublicInvestmentProjectEntity publicInvestmentProjectEntity = new Select().from(PublicInvestmentProjectEntity.class).as("PublicInvestmentProject").where(PublicInvestmentProjectEntity_Table.UniqueCode.like(uniqueCode)).querySingle();

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
