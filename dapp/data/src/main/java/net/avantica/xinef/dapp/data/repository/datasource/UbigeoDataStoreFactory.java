package net.avantica.xinef.dapp.data.repository.datasource;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UbigeoDataStoreFactory {
    private final Context context;


    @Inject
    public UbigeoDataStoreFactory(Context context) {
        this.context = context;
    }

    public UbigeoDataStore create() {
        UbigeoDataStore ubigeoDataStore;

        ubigeoDataStore = new DiskUbigeoDataStore();

        return ubigeoDataStore;
    }
}
