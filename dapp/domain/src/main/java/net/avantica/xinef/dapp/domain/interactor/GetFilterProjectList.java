package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.FilterProjectRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class GetFilterProjectList extends UseCase {
    private final FilterProjectRepository filterProjectRepository;

    @Inject
    public GetFilterProjectList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, FilterProjectRepository filterProjectRepository) {
        super(threadExecutor, postExecutionThread);
        this.filterProjectRepository = filterProjectRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.filterProjectRepository.getDepartments();
    }

    public void getDepartments(Subscriber subScriber) {
        Observable observable = this.filterProjectRepository.getDepartments();
        execute(observable, subScriber);
    }

    public void getProvinces(Subscriber subScriber, String department) {
    }

    public void getDistrict(Subscriber subScriber, String deparment, String province) {
    }
}
