package net.avantica.xinef.dapp.presenter;

import android.support.annotation.NonNull;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;
import net.avantica.xinef.dapp.domain.exception.DefaultErrorBundle;
import net.avantica.xinef.dapp.domain.exception.ErrorBundle;
import net.avantica.xinef.dapp.domain.interactor.DefaultSubscriber;
import net.avantica.xinef.dapp.domain.interactor.UseCase;
import net.avantica.xinef.dapp.exception.ErrorMessageFactory;
import net.avantica.xinef.dapp.mapper.PublicInvestmentProjectModelDataMapper;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PublicInvestmentProjectListPresenter implements Presenter {

    private PublicInvestmentProjectListView viewListView;

    private final UseCase getPublicInvestmentProjectListUseCase;
    private final PublicInvestmentProjectModelDataMapper publicInvestmentProjectModelDataMapper;

    @Inject
    public PublicInvestmentProjectListPresenter(@Named("publicInvestmentProjectList") UseCase getPublicInvestmentProjectListUseCase, PublicInvestmentProjectModelDataMapper publicInvestmentProjectModelDataMapper) {
        this.getPublicInvestmentProjectListUseCase = getPublicInvestmentProjectListUseCase;
        this.publicInvestmentProjectModelDataMapper = publicInvestmentProjectModelDataMapper;
    }

    public void setView(@NonNull PublicInvestmentProjectListView view) {
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
        this.getPublicInvestmentProjectListUseCase.unsubscribe();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the publicInvestmentProject list.
     */
    public void initialize() {
        this.loadPublicInvestmentProjectList();
    }

    /**
     * Loads all public investment projects.
     */
    private void loadPublicInvestmentProjectList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPublicInvestmentProjectList();
    }

    public void onPublicInvestmentProjectClicked(String snipCode) {
        this.viewListView.viewPublicInvestmentProject(snipCode);
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

    private void showPublicInvestmentProjectCollectionInView(Collection<PublicInvestmentProject> publicInvestmentProjectsCollection) {
        final Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelsCollection =
                this.publicInvestmentProjectModelDataMapper.transform(publicInvestmentProjectsCollection);
        this.viewListView.renderPublicInvestmentProjectList(publicInvestmentProjectModelsCollection);
    }

    private void getPublicInvestmentProjectList() {
        this.getPublicInvestmentProjectListUseCase.execute(new PublicInvestmentProjectListSubscriber());
    }

    private final class PublicInvestmentProjectListSubscriber extends DefaultSubscriber<List<PublicInvestmentProject>> {

        @Override
        public void onCompleted() {
            PublicInvestmentProjectListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            PublicInvestmentProjectListPresenter.this.hideViewLoading();
            PublicInvestmentProjectListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PublicInvestmentProjectListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<PublicInvestmentProject> publicInvestmentProject) {
            PublicInvestmentProjectListPresenter.this.showPublicInvestmentProjectCollectionInView(publicInvestmentProject);
        }
    }
}
