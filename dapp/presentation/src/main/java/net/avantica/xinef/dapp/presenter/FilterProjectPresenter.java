package net.avantica.xinef.dapp.presenter;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.exception.DefaultErrorBundle;
import net.avantica.xinef.dapp.domain.exception.ErrorBundle;
import net.avantica.xinef.dapp.domain.interactor.DefaultSubscriber;
import net.avantica.xinef.dapp.domain.interactor.GetFilterProjectList;
import net.avantica.xinef.dapp.exception.ErrorMessageFactory;
import net.avantica.xinef.dapp.mapper.PublicInvestmentProjectModelDataMapper;
import net.avantica.xinef.dapp.view.FilterProjectView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class FilterProjectPresenter implements Presenter {
    private FilterProjectView viewListView;
    private final GetFilterProjectList getFilterProjectList;
    private final PublicInvestmentProjectModelDataMapper publicInvestmentProjectModelDataMapper;

    @Inject
    public FilterProjectPresenter(@Named("filterProjectPresenter") GetFilterProjectList getFilterProjectList, PublicInvestmentProjectModelDataMapper publicInvestmentProjectModelDataMapper) {
        this.getFilterProjectList = getFilterProjectList;
        this.publicInvestmentProjectModelDataMapper = publicInvestmentProjectModelDataMapper;
    }

    public void setView(FilterProjectView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getFilterProjectList.unsubscribe();
        this.viewListView = null;
    }

    private void loadDepartments() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getDepartments();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    public void getDepartments() {
        this.getFilterProjectList.getDepartments(new DepartmentSubscriber());
    }

    public void getProvinces(String department) {

    }

    public void getDistricts(String department, String province) {

    }

    private void showDepartmentsInView(List<String> publicInvestmentProject) {

    }

    private final class DepartmentSubscriber extends DefaultSubscriber<List<String>> {

        @Override
        public void onCompleted() {
            FilterProjectPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            FilterProjectPresenter.this.hideViewLoading();
            FilterProjectPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            FilterProjectPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<String> publicInvestmentProject) {
            FilterProjectPresenter.this.showDepartmentsInView(publicInvestmentProject);
        }

    }


}
