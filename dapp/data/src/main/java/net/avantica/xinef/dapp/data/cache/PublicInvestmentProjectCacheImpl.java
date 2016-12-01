package net.avantica.xinef.dapp.data.cache;

import android.content.Context;

import net.avantica.xinef.dapp.data.cache.serializer.JsonSerializer;
import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.data.exception.PublicInvestmentProjectNotFoundException;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link PublicInvestmentProjectCache} implementation.
 */
@Singleton
public class PublicInvestmentProjectCacheImpl implements PublicInvestmentProjectCache {

    private static final String SETTINGS_FILE_NAME = "net.avantica.xinef.dapp.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "publicInvestmentProject_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link PublicInvestmentProjectCacheImpl}.
     *
     * @param context             A
     * @param publicInvestmentProjectCacheSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager         {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public PublicInvestmentProjectCacheImpl(Context context, JsonSerializer publicInvestmentProjectCacheSerializer,
                                            FileManager fileManager, ThreadExecutor executor) {
        if (context == null || publicInvestmentProjectCacheSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = publicInvestmentProjectCacheSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<PublicInvestmentProjectEntity> get(final String publicInvestmentProjectId) {
        return Observable.create(subscriber -> {
            File publicInvestmentProjectEntityFile = PublicInvestmentProjectCacheImpl.this.buildFile(publicInvestmentProjectId);
            String fileContent = PublicInvestmentProjectCacheImpl.this.fileManager.readFileContent(publicInvestmentProjectEntityFile);
            PublicInvestmentProjectEntity publicInvestmentProjectEntity = PublicInvestmentProjectCacheImpl.this.serializer.deserialize(fileContent);

            if (publicInvestmentProjectEntity != null) {
                subscriber.onNext(publicInvestmentProjectEntity);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new PublicInvestmentProjectNotFoundException());
            }
        });
    }

    @Override
    public void put(PublicInvestmentProjectEntity publicInvestmentProjectEntity) {
        if (publicInvestmentProjectEntity != null) {
            File publicInvestmentProjectEntityFile = this.buildFile(publicInvestmentProjectEntity.getUniqueCode());
            if (!isCached(publicInvestmentProjectEntity.getUniqueCode())) {
                String jsonString = this.serializer.serialize(publicInvestmentProjectEntity);
                this.executeAsynchronously(new CacheWriter(this.fileManager, publicInvestmentProjectEntityFile,
                        jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(String publicInvestmentProjectId) {
        File publicInvestmentProjectEntitiyFile = this.buildFile(publicInvestmentProjectId);
        return this.fileManager.exists(publicInvestmentProjectEntitiyFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param publicInvestmentProjectId The id publicInvestmentProject to build the file.
     * @return A valid file.
     */
    private File buildFile(String publicInvestmentProjectId) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(publicInvestmentProjectId);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
