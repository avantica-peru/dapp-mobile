package net.avantica.xinef.dapp.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder> {
    private Activity activity;
    private List<PublicInvestmentProjectModel> publicInvestmentProjectModels;

    public ProjectListAdapter(Activity activity, List<PublicInvestmentProjectModel> publicInvestmentProjectModels) {
        this.activity = activity;
        this.publicInvestmentProjectModels = publicInvestmentProjectModels;
    }

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_public_investment_project, parent, false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder holder, int position) {
        PublicInvestmentProjectModel publicInvestmentProjectModel = this.publicInvestmentProjectModels.get(position);
        holder.bind(publicInvestmentProjectModel);
    }

    @Override
    public int getItemCount() {
        return publicInvestmentProjectModels.size();
    }

    public static class ProjectListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_pip)
        TextView namePip;
        @BindView(R.id.tv_function_pip)
        TextView functionPip;
        @BindView(R.id.tv_cost_pip)
        TextView costPip;

        public ProjectListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PublicInvestmentProjectModel publicInvestmentProjectModel) {
            namePip.setText(publicInvestmentProjectModel.getName());
            functionPip.setText(publicInvestmentProjectModel.getFunction());
            costPip.setText(publicInvestmentProjectModel.getCost());
        }
    }
}
