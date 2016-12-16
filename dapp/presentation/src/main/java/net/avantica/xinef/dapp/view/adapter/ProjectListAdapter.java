package net.avantica.xinef.dapp.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
    private Typeface fontMedium;
    private Typeface fontBlack;

    @Inject
    public ProjectListAdapter(Context activity) {
        this.activity = activity;
        fontMedium = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Avenir-Medium.otf");
        fontBlack = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Avenir-Black.otf");
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_public_investment_project, parent, false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder holder, int position) {
        final PublicInvestmentProjectModel publicInvestmentProjectModel = this.publicInvestmentProjectModels.get(position);
        holder.namePip.setTypeface(fontMedium);
        holder.functionPip.setTypeface(fontMedium);
        holder.costPip.setTypeface(fontBlack);

        holder.namePip.setText(publicInvestmentProjectModel.getName());
        holder.functionPip.setText(publicInvestmentProjectModel.getFunction());
        holder.costPip.setText(String.format(this.activity.getString(R.string.currency_x), publicInvestmentProjectModel.getCost()));
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

    static class ProjectListViewHolder extends RecyclerView.ViewHolder {
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
