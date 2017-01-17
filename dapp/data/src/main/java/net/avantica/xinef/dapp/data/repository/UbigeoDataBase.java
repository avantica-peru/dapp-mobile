package net.avantica.xinef.dapp.data.repository;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = UbigeoDataBase.NAME, version = UbigeoDataBase.VERSION, foreignKeysSupported = true)
public class UbigeoDataBase {
    public static final String NAME = "ubigeo";

    public static final int VERSION = 1;
}
