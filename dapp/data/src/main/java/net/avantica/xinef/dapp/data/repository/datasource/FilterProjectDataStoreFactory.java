package net.avantica.xinef.dapp.data.repository.datasource;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FilterProjectDataStoreFactory {
    private final Context context;


    @Inject
    public FilterProjectDataStoreFactory(Context context) {
        this.context = context;
    }

    public FilterProjectDataStore create() {
        FilterProjectDataStore filterProjectDataStore;

        filterProjectDataStore = new DiskFilterProjectDataStore();

        return filterProjectDataStore;
    }
}
