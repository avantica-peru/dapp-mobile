package net.avantica.xinef.dapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import net.avantica.xinef.dapp.view.adapter.ProjectListAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectListFragment extends BaseFragment implements PublicInvestmentProjectListView {

    public interface PublicInvestmentProjectListListener {
        void onPublicInvestmentProjectClicked(final PublicInvestmentProjectModel userModel);
    }

    @BindView(R.id.rv_project_list)
    RecyclerView projectListRecyclerView;

    @Inject
    PublicInvestmentProjectListPresenter publicInvestmentProjectListPresenter;

    @Inject
    ProjectListAdapter projectListAdapter;

    private PublicInvestmentProjectListListener publicInvestmentProjectListListener;

    private Unbinder unbinder;

    public ProjectListFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    public static ProjectListFragment newInstance() {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PublicInvestmentProjectComponent.class).inject(this);

        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.publicInvestmentProjectListListener = (PublicInvestmentProjectListListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
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
        projectListRecyclerView.setAdapter(null);
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
        this.publicInvestmentProjectListListener = null;
    }

    public void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);

        this.projectListAdapter.setOnItemClickListener(onItemClickListener);
        projectListRecyclerView.setLayoutManager(linearLayoutManager);
        projectListRecyclerView.setHasFixedSize(true);
        projectListRecyclerView.setAdapter(projectListAdapter);
    }

    private void loadPublicInvestmentProjectList() {
        this.publicInvestmentProjectListPresenter.initialize();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void renderPublicInvestmentProjectList(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection != null) {
            this.projectListAdapter.setPublicInvestmentProjectCollection(publicInvestmentProjectModelCollection);
        }
    }

    @Override
    public void viewPublicInvestmentProject(PublicInvestmentProjectModel publicInvestmentProjectModel) {
        this.publicInvestmentProjectListListener.onPublicInvestmentProjectClicked(publicInvestmentProjectModel);
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

    private ProjectListAdapter.OnItemClickListener onItemClickListener =
            new ProjectListAdapter.OnItemClickListener() {
                @Override
                public void onPublicInvestmentProjectItemClicked(PublicInvestmentProjectModel publicInvestmentProjectModel) {
                    if (ProjectListFragment.this.publicInvestmentProjectListPresenter != null && publicInvestmentProjectModel != null) {
                        ProjectListFragment.this.publicInvestmentProjectListPresenter.onPublicInvestmentProjectClicked(publicInvestmentProjectModel);
                    }
                }
            };
}
