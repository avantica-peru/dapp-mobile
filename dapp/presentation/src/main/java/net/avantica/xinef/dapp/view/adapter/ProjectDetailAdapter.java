package net.avantica.xinef.dapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.model.ItemPublicInvestmentProjectDetailModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.ProjectDetailViewHolder> {
    private Context context;
    private List<ItemPublicInvestmentProjectDetailModel> itemPublicInvestmentProjectDetailModels;

    @Inject
    public ProjectDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ProjectDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_public_investment_project_detail, parent, false);
        return new ProjectDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectDetailViewHolder holder, int position) {
        final ItemPublicInvestmentProjectDetailModel itemPublicInvestmentProjectDetailModel = this.itemPublicInvestmentProjectDetailModels.get(position);

        holder.key.setText(itemPublicInvestmentProjectDetailModel.getKey());
        holder.value.setText(itemPublicInvestmentProjectDetailModel.getValue());
    }

    @Override
    public int getItemCount() {
        return (this.itemPublicInvestmentProjectDetailModels != null) ? this.itemPublicInvestmentProjectDetailModels.size() : 0;
    }

    public void setItemPublicInvestmentProjectDetailCollection(List<ItemPublicInvestmentProjectDetailModel> publicInvestmentProjectModelCollection) {
        this.validatePublicInvestmentProjectsCollection(publicInvestmentProjectModelCollection);
        this.itemPublicInvestmentProjectDetailModels = publicInvestmentProjectModelCollection;
        this.notifyDataSetChanged();
    }

    private void validatePublicInvestmentProjectsCollection(Collection<ItemPublicInvestmentProjectDetailModel> publicInvestmentProjectModelCollection) {
        if (publicInvestmentProjectModelCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public static class ProjectDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_key)
        TextView key;
        @BindView(R.id.tv_value)
        TextView value;

        public ProjectDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
