package net.avantica.xinef.dapp.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.model.ItemPublicInvestmentProjectDetailModel;
import net.avantica.xinef.dapp.util.BlurBuilder;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ItemPublicInvestmentProjectDetailModel> itemPublicInvestmentProjectDetailModels;
    private String namePip;

    private Typeface fontMedium;
    private Typeface fontBlack;

    @Inject
    public ProjectDetailAdapter(Context context) {
        this.context = context;

        fontMedium = Typeface.createFromAsset(this.context.getAssets(), "fonts/Avenir-Medium.otf");
        fontBlack = Typeface.createFromAsset(this.context.getAssets(), "fonts/Avenir-Black.otf");
    }

    @Override
    public int getItemViewType(int posicion) {
        return posicion == 0 ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0) {
            View view = inflater.inflate(R.layout.item_public_investment_project_header, parent, false);
            viewHolder = new ProjectDetailHeaderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_public_investment_project_detail, parent, false);
            viewHolder = new ProjectDetailViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemPublicInvestmentProjectDetailModel itemPublicInvestmentProjectDetailModel = this.itemPublicInvestmentProjectDetailModels.get(position);

        if (position == 0) {
            ProjectDetailHeaderViewHolder projectDetailHeaderViewHolder = (ProjectDetailHeaderViewHolder) holder;
            projectDetailHeaderViewHolder.name.setTypeface(fontBlack);
            projectDetailHeaderViewHolder.name.setText(itemPublicInvestmentProjectDetailModel.getValue());

            final Bitmap bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bkg_header);
            final Bitmap blurredBitmap = BlurBuilder.blur(this.context, bitmap);

            projectDetailHeaderViewHolder.backgroundHeader.setImageBitmap(blurredBitmap);
        } else {
            ProjectDetailViewHolder projectDetailViewHolder = (ProjectDetailViewHolder) holder;

            projectDetailViewHolder.key.setTypeface(fontMedium);
            projectDetailViewHolder.value.setTypeface(fontMedium);

            projectDetailViewHolder.key.setText(itemPublicInvestmentProjectDetailModel.getKey());
            projectDetailViewHolder.value.setText(itemPublicInvestmentProjectDetailModel.getValue());
        }
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

    static class ProjectDetailHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.iv_background_header)
        ImageView backgroundHeader;

        public ProjectDetailHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ProjectDetailViewHolder extends RecyclerView.ViewHolder {
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
