package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.UbigeoRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class GetUbigeo extends UseCase {
    private final UbigeoRepository ubigeoRepository;

    @Inject
    public GetUbigeo(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UbigeoRepository ubigeoRepository) {
        super(threadExecutor, postExecutionThread);
        this.ubigeoRepository = ubigeoRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.ubigeoRepository.getDepartments();
    }

    public void getDepartments(Subscriber subScriber) {
        Observable observable = this.ubigeoRepository.getDepartments();
        execute(observable, subScriber);
    }

    public void getProvinces(Subscriber subScriber, String codDepartment) {
        Observable observable = this.ubigeoRepository.getProvinces(codDepartment);
        execute(observable, subScriber);
    }

    public void getDistrict(Subscriber subScriber, String codDeparment, String codProvince) {
        Observable observable = this.ubigeoRepository.getDistricts(codDeparment, codProvince);
        execute(observable, subScriber);
    }
}
