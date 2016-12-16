package net.avantica.xinef.dapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.components.PublicInvestmentProjectComponent;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.presenter.PublicInvestmentProjectListPresenter;
import net.avantica.xinef.dapp.view.PublicInvestmentProjectListView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectsMapFragment extends BaseFragment implements PublicInvestmentProjectListView {

    public interface PublicInvestmentProjectsMapListener {
        void onPublicInvestmentProjectClicked(final PublicInvestmentProjectModel userModel);
    }

    @Inject
    PublicInvestmentProjectListPresenter publicInvestmentProjectListPresenter;

//    private PublicInvestmentProjectsMapListener publicInvestmentProjectListListener;

    private Unbinder unbinder;

    public ProjectsMapFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static ProjectsMapFragment newInstance() {
        ProjectsMapFragment fragment = new ProjectsMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        this.publicInvestmentProjectListListener = (PublicInvestmentProjectsMapListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_project_map, container, false);
        unbinder = ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        return view;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.publicInvestmentProjectListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        this.publicInvestmentProjectListListener = null;
    }

    private void loadPublicInvestmentProjectList() {
        this.publicInvestmentProjectListPresenter.initialize();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void renderPublicInvestmentProjectList(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection != null) {

        }
    }

    @Override
    public void viewPublicInvestmentProject(PublicInvestmentProjectModel publicInvestmentProjectModel) {
//        this.publicInvestmentProjectListListener.onPublicInvestmentProjectClicked(publicInvestmentProjectModel);
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
        return this.getActivity().getApplicationContext();
    }
}
