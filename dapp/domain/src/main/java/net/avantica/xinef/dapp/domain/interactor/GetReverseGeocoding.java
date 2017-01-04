package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.ReverseGeocodingRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetReverseGeocoding extends UseCase {

    private final ReverseGeocodingRepository reverseGeocodingRepository;
    private double latitude;
    private double longitude;

    @Inject
    public GetReverseGeocoding(double latitude, double longitude, ReverseGeocodingRepository reverseGeocodingRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.latitude = latitude;
        this.longitude = longitude;
        this.reverseGeocodingRepository = reverseGeocodingRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.reverseGeocodingRepository.departmentNamefromlatitudeLongitude(latitude, longitude);
    }
}
