package net.avantica.xinef.dapp.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.presenter.PublicInvestmentProjectListPresenter;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class SplashFragment extends BaseFragment implements PublicInvestmentProjectListView {

    public interface SplashListener {
        void successfulLoad(final List<PublicInvestmentProjectModel> publicInvestmentProjectModels);

        void loadFailed();
    }

    @Inject
    PublicInvestmentProjectListPresenter publicInvestmentProjectListPresenter;

    private SplashListener publicInvestmentProjectListListener;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.publicInvestmentProjectListListener = (SplashFragment.SplashListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.publicInvestmentProjectListPresenter.setView(this);

        if (savedInstanceState == null) {
            this.loadPublicInvestmentProjectList();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        this.publicInvestmentProjectListListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.publicInvestmentProjectListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.publicInvestmentProjectListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.publicInvestmentProjectListPresenter.destroy();
    }

    private void loadPublicInvestmentProjectList() {
        this.publicInvestmentProjectListPresenter.initialize();
    }

    @Override
    public void renderPublicInvestmentProjectList(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection != null) {
            this.publicInvestmentProjectListListener.successfulLoad((List<PublicInvestmentProjectModel>) publicInvestmentProjectModelCollection);
        }
    }

    @Override
    public void viewPublicInvestmentProject(PublicInvestmentProjectModel publicInvestmentProjectModel) {
        //Not needed
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }
}
