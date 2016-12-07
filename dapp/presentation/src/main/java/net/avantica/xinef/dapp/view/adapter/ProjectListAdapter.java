package net.avantica.xinef.dapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder> {
    public interface OnItemClickListener {
        void onPublicInvestmentProjectItemClicked(PublicInvestmentProjectModel publicInvestmentProjectModel);
    }

    private OnItemClickListener onItemClickListener;
    private Context activity;
    private List<PublicInvestmentProjectModel> publicInvestmentProjectModels;

    @Inject
    public ProjectListAdapter(Context activity) {
        this.activity = activity;
    }

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_public_investment_project, parent, false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder holder, int position) {
        final PublicInvestmentProjectModel publicInvestmentProjectModel = this.publicInvestmentProjectModels.get(position);
        holder.namePip.setText(publicInvestmentProjectModel.getName());
        holder.functionPip.setText(publicInvestmentProjectModel.getFunction());
        holder.costPip.setText(publicInvestmentProjectModel.getCost());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProjectListAdapter.this.onItemClickListener != null) {
                    ProjectListAdapter.this.onItemClickListener.onPublicInvestmentProjectItemClicked(publicInvestmentProjectModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.publicInvestmentProjectModels != null) ? this.publicInvestmentProjectModels.size() : 0;
    }

    public void setPublicInvestmentProjectCollection(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        this.validatePublicInvestmentProjectsCollection(publicInvestmentProjectModelCollection);
        this.publicInvestmentProjectModels = (List<PublicInvestmentProjectModel>) publicInvestmentProjectModelCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validatePublicInvestmentProjectsCollection(Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
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
    }
}
