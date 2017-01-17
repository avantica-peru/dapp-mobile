package net.avantica.xinef.dapp.presenter;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.entity.Ubigeo;
import net.avantica.xinef.dapp.domain.exception.DefaultErrorBundle;
import net.avantica.xinef.dapp.domain.exception.ErrorBundle;
import net.avantica.xinef.dapp.domain.interactor.DefaultSubscriber;
import net.avantica.xinef.dapp.domain.interactor.GetUbigeo;
import net.avantica.xinef.dapp.exception.ErrorMessageFactory;
import net.avantica.xinef.dapp.mapper.UbigeoModelDataMapper;
import net.avantica.xinef.dapp.view.UbigeoView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class UbigeoPresenter implements Presenter {
    private UbigeoView viewListView;
    private final GetUbigeo getUbigeo;
    private final UbigeoModelDataMapper ubigeoModelDataMapper;

    @Inject
    public UbigeoPresenter(@Named("ubigeoPresenter") GetUbigeo getUbigeo, UbigeoModelDataMapper ubigeoModelDataMapper) {
        this.getUbigeo = getUbigeo;
        this.ubigeoModelDataMapper = ubigeoModelDataMapper;
    }

    public void setView(UbigeoView view) {
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
        this.getUbigeo.unsubscribe();
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
        this.getUbigeo.getDepartments(new DepartmentSubscriber());
    }

    public void getProvinces(String codDepartment) {
        this.getUbigeo.getProvinces(new ProvinceSubscriber(), codDepartment);
    }

    public void getDistricts(String codDepartment, String codProvince) {
        this.getUbigeo.getDistrict(new DistrictsSubscriber(), codDepartment, codProvince);
    }

    private void showDepartmentsInView(Collection<Ubigeo> ubigeos) {
        viewListView.renderDepartments(ubigeoModelDataMapper.transform(ubigeos));
    }

    private void showProvincesInView(List<Ubigeo> ubigeos) {
        viewListView.renderProvinces(ubigeoModelDataMapper.transform(ubigeos));
    }

    private void showDistrictsInView(List<Ubigeo> ubigeos) {
        viewListView.renderDistricts(ubigeoModelDataMapper.transform(ubigeos));
    }

    private final class DepartmentSubscriber extends DefaultSubscriber<List<Ubigeo>> {

        @Override
        public void onCompleted() {
            UbigeoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UbigeoPresenter.this.hideViewLoading();
            UbigeoPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UbigeoPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Ubigeo> ubigeos) {
            UbigeoPresenter.this.showDepartmentsInView(ubigeos);
        }

    }

    private final class ProvinceSubscriber extends DefaultSubscriber<List<Ubigeo>> {

        @Override
        public void onCompleted() {
            UbigeoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UbigeoPresenter.this.hideViewLoading();
            UbigeoPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UbigeoPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Ubigeo> ubigeos) {
            UbigeoPresenter.this.showProvincesInView(ubigeos);
        }

    }

    private final class DistrictsSubscriber extends DefaultSubscriber<List<Ubigeo>> {

        @Override
        public void onCompleted() {
            UbigeoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UbigeoPresenter.this.hideViewLoading();
            UbigeoPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UbigeoPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Ubigeo> ubigeos) {
            UbigeoPresenter.this.showDistrictsInView(ubigeos);
        }

    }
}
