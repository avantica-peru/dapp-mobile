package net.avantica.xinef.dapp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.view.activity.ProjectDetailActivity;
import net.avantica.xinef.dapp.view.adapter.ProjectListAdapter;
import net.avantica.xinef.dapp.listener.RecyclerItemClickListener;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectListFragment extends Fragment {
    @BindView(R.id.rv_project_list)
    RecyclerView projectListRecyclerView;

    private List<PublicInvestmentProjectModel> publicInvestmentProjectModels;

    public ProjectListFragment() {
        // Required empty public constructor
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

        publicInvestmentProjectModels = new ArrayList<>();
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());
        publicInvestmentProjectModels.add(new PublicInvestmentProjectModel());

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(this, view);

        initializeRecyclerView();

        setHasOptionsMenu(true);

        return view;
    }

    public void initializeRecyclerView() {
        final ProjectListAdapter projectListAdapter = new ProjectListAdapter(getActivity(), publicInvestmentProjectModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);

        projectListRecyclerView.setLayoutManager(linearLayoutManager);
        projectListRecyclerView.setHasFixedSize(true);
        projectListRecyclerView.setAdapter(projectListAdapter);
        projectListRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_project_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
