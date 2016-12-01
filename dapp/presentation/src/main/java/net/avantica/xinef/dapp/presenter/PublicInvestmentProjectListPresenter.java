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

    private final UseCase getUserListUseCase;
    private final PublicInvestmentProjectModelDataMapper userModelDataMapper;

    @Inject
    public PublicInvestmentProjectListPresenter(@Named("userList") UseCase getUserListUserCase,
                                                PublicInvestmentProjectModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUserCase;
        this.userModelDataMapper = userModelDataMapper;
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
        this.getUserListUseCase.unsubscribe();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    public void onUserClicked(PublicInvestmentProjectModel userModel) {
        this.viewListView.viewUser(userModel);
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

    private void showUsersCollectionInView(Collection<PublicInvestmentProject> usersCollection) {
        final Collection<PublicInvestmentProjectModel> userModelsCollection =
                this.userModelDataMapper.transform(usersCollection);
        this.viewListView.renderUserList(userModelsCollection);
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListSubscriber());
    }

    private final class UserListSubscriber extends DefaultSubscriber<List<PublicInvestmentProject>> {

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
        public void onNext(List<PublicInvestmentProject> users) {
            PublicInvestmentProjectListPresenter.this.showUsersCollectionInView(users);
        }
    }
}
