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
import net.avantica.xinef.dapp.presenter.PublicInvestmentProjectDetailsPresenter;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectDetailsView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectDetailFragment extends BaseFragment implements PublicInvestmentProjectDetailsView {

    @Inject
    PublicInvestmentProjectDetailsPresenter publicInvestmentProjectDetailsPresenter;

//    @BindViews({})

    private Unbinder unbinder;

    public ProjectDetailFragment() {
    }

    public static ProjectDetailFragment newInstance() {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.publicInvestmentProjectDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadPublicInvestmentProjectDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.publicInvestmentProjectDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.publicInvestmentProjectDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.publicInvestmentProjectDetailsPresenter.destroy();
    }

    @Override
    public void renderPublicInvestmentProject(Collection<PublicInvestmentProjectModel> publicInvestmentProject) {

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
        return getActivity().getApplicationContext();
    }

    /**
     * Loads project detail.
     */
    private void loadPublicInvestmentProjectDetails() {
        if (this.publicInvestmentProjectDetailsPresenter != null) {
            this.publicInvestmentProjectDetailsPresenter.initialize();
        }
    }
}
