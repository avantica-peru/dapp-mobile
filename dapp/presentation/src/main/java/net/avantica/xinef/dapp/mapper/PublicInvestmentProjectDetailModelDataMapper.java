package net.avantica.xinef.dapp.mapper;

import android.content.Context;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.model.ItemPublicInvestmentProjectDetailModel;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;
import net.avantica.xinef.dapp.util.Constant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PublicInvestmentProjectDetailModelDataMapper {

    @Inject
    public PublicInvestmentProjectDetailModelDataMapper() {
    }

    /**
     * Transform a {@link PublicInvestmentProjectModel} into an {@link ItemPublicInvestmentProjectDetailModel}.
     *
     * @param publicInvestmentProjectModel Object to be transformed.
     * @return {@link PublicInvestmentProjectModel}.
     */
    public List<ItemPublicInvestmentProjectDetailModel> transform(Context context, PublicInvestmentProjectModel publicInvestmentProjectModel) {
        if (publicInvestmentProjectModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        List<ItemPublicInvestmentProjectDetailModel> itemPublicInvestmentProjectDetailModels = new ArrayList<>();

        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(Constant.EMPTY_STRING, publicInvestmentProjectModel.getName()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.costo), String.format(context.getString(R.string.currency_x), publicInvestmentProjectModel.getCost())));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.centro_poblado), publicInvestmentProjectModel.getPopulatedCenter()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.unidad_formuladora), publicInvestmentProjectModel.getFormulatingUnit()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.sector), publicInvestmentProjectModel.getSector()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.pliego), publicInvestmentProjectModel.getFolder()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.ejecutora), publicInvestmentProjectModel.getExecutor()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.codigo_snip), publicInvestmentProjectModel.getSnipCode()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.codigo_unico), publicInvestmentProjectModel.getUniqueCode()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.funcion), publicInvestmentProjectModel.getFunction()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.programa), publicInvestmentProjectModel.getProgram()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.sub_programa), publicInvestmentProjectModel.getSubprogram()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.fuente_financiamiento), publicInvestmentProjectModel.getFundingSource()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.fecha_registro), publicInvestmentProjectModel.getRegistrationDate()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.situacion), publicInvestmentProjectModel.getState()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.situacion), publicInvestmentProjectModel.getSituation()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.closed), publicInvestmentProjectModel.getClosed()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.fecha_viab), publicInvestmentProjectModel.getViabDate()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.monto_viable), publicInvestmentProjectModel.getViableAmount()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.beneficiario), publicInvestmentProjectModel.getBeneficiary()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.objetivo), publicInvestmentProjectModel.getObjective()));
        itemPublicInvestmentProjectDetailModels.add(new ItemPublicInvestmentProjectDetailModel(context.getString(R.string.alternativa), publicInvestmentProjectModel.getAlternative()));

        return itemPublicInvestmentProjectDetailModels;
    }

}
