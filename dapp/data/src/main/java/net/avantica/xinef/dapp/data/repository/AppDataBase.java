package net.avantica.xinef.dapp.data.repository;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION, foreignKeysSupported = true)
public class AppDataBase {
    public static final String NAME = "db_dapp";

    public static final int VERSION = 1;
}
