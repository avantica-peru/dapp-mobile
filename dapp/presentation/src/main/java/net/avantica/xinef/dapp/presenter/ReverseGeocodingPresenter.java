package net.avantica.xinef.dapp.presenter;


import android.support.annotation.NonNull;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.exception.DefaultErrorBundle;
import net.avantica.xinef.dapp.domain.exception.ErrorBundle;
import net.avantica.xinef.dapp.domain.interactor.DefaultSubscriber;
import net.avantica.xinef.dapp.domain.interactor.UseCase;
import net.avantica.xinef.dapp.exception.ErrorMessageFactory;
import net.avantica.xinef.dapp.view.ReverseGeocodingView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ReverseGeocodingPresenter implements Presenter {
    private ReverseGeocodingView viewReverseGeocodingView;

    private final UseCase getReverseGeocodingUseCase;

    @Inject
    public ReverseGeocodingPresenter(@Named("reverseGeocoding") UseCase getReverseGeocodingUseCase) {
        this.getReverseGeocodingUseCase = getReverseGeocodingUseCase;
    }

    public void setView(@NonNull ReverseGeocodingView view) {
        this.viewReverseGeocodingView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getReverseGeocodingUseCase.unsubscribe();
        this.viewReverseGeocodingView = null;
    }

    public void initialize() {
        this.loadDepartmentName();
    }

    private void loadDepartmentName() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getDepartmentName();
    }

    private void showViewLoading() {
        this.viewReverseGeocodingView.showLoading();
    }

    private void hideViewLoading() {
        this.viewReverseGeocodingView.hideLoading();
    }

    private void showViewRetry() {
        this.viewReverseGeocodingView.showRetry();
    }

    private void hideViewRetry() {
        this.viewReverseGeocodingView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewReverseGeocodingView.context(),
                errorBundle.getException());
        this.viewReverseGeocodingView.showError(errorMessage);
    }

    private void showDepartmentNameInView(String departmentName) {
        this.viewReverseGeocodingView.getDepartmentName(departmentName);
    }

    private void getDepartmentName() {
        this.getReverseGeocodingUseCase.execute(new ReverseGeocodingSubscriber());
    }

    private final class ReverseGeocodingSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            ReverseGeocodingPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ReverseGeocodingPresenter.this.hideViewLoading();
            ReverseGeocodingPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ReverseGeocodingPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String departmentName) {
            ReverseGeocodingPresenter.this.showDepartmentNameInView(departmentName);
        }
    }
}
