package net.avantica.xinef.dapp.presenter;

import android.support.annotation.NonNull;

import net.avantica.xinef.dapp.data.annotation.RxLogSubscriber;
import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;
import net.avantica.xinef.dapp.domain.exception.DefaultErrorBundle;
import net.avantica.xinef.dapp.domain.exception.ErrorBundle;
import net.avantica.xinef.dapp.domain.interactor.DefaultSubscriber;
import net.avantica.xinef.dapp.domain.interactor.UseCase;
import net.avantica.xinef.dapp.exception.ErrorMessageFactory;
import net.avantica.xinef.dapp.mapper.PublicInvestmentProjectModelDataMapper;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PublicInvestmentProjectDetailsPresenter implements Presenter {

    private PublicInvestmentProjectDetailsView viewDetailsView;

    private final UseCase getUserDetailsUseCase;
    private final PublicInvestmentProjectModelDataMapper userModelDataMapper;

    @Inject
    public PublicInvestmentProjectDetailsPresenter(@Named("userDetails") UseCase getUserDetailsUseCase, PublicInvestmentProjectModelDataMapper userModelDataMapper) {
        this.getUserDetailsUseCase = getUserDetailsUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull PublicInvestmentProjectDetailsView view) {
        this.viewDetailsView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getUserDetailsUseCase.unsubscribe();
        this.viewDetailsView = null;
    }

    /**
     * Initializes the presenter by start retrieving user details.
     */
    public void initialize() {
        this.loadUserDetails();
    }

    /**
     * Loads user details.
     */
    private void loadUserDetails() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserDetails();
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
                errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showUserDetailsInView(PublicInvestmentProject user) {
        final PublicInvestmentProjectModel userModel = this.userModelDataMapper.transform(user);
        this.viewDetailsView.renderUser(userModel);
    }

    private void getUserDetails() {
        this.getUserDetailsUseCase.execute(new UserDetailsSubscriber());
    }

    @RxLogSubscriber
    private final class UserDetailsSubscriber extends DefaultSubscriber<PublicInvestmentProject> {

        @Override
        public void onCompleted() {
            PublicInvestmentProjectDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            PublicInvestmentProjectDetailsPresenter.this.hideViewLoading();
            PublicInvestmentProjectDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PublicInvestmentProjectDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(PublicInvestmentProject user) {
            PublicInvestmentProjectDetailsPresenter.this.showUserDetailsInView(user);
        }
    }
}
