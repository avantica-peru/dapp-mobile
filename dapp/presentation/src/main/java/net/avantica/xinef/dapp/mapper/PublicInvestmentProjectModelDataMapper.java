package net.avantica.xinef.dapp.mapper;

import net.avantica.xinef.dapp.di.PerActivity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;
import net.avantica.xinef.dapp.model.PublicInvestmentProjectModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link PublicInvestmentProject} (in the domain layer) to {@link PublicInvestmentProjectModel} in the
 * presentation layer.
 */
@PerActivity
public class PublicInvestmentProjectModelDataMapper {

    @Inject
    public PublicInvestmentProjectModelDataMapper() {
    }

    /**
     * Transform a {@link PublicInvestmentProject} into an {@link PublicInvestmentProjectModel}.
     *
     * @param publicInvestmentProject Object to be transformed.
     * @return {@link PublicInvestmentProjectModel}.
     */
    public PublicInvestmentProjectModel transform(PublicInvestmentProject publicInvestmentProject) {
        if (publicInvestmentProject == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        PublicInvestmentProjectModel publicInvestmentProjectModel = new PublicInvestmentProjectModel();
        publicInvestmentProjectModel.setDepartment(publicInvestmentProject.getDepartment());
        publicInvestmentProjectModel.setProvince(publicInvestmentProject.getProvince());
        publicInvestmentProjectModel.setDistrict(publicInvestmentProject.getDistrict());
        publicInvestmentProjectModel.setZipCode(publicInvestmentProject.getZipCode());
        publicInvestmentProjectModel.setLatitude(publicInvestmentProject.getLatitude());
        publicInvestmentProjectModel.setLongitude(publicInvestmentProject.getLongitude());
        publicInvestmentProjectModel.setPopulatedCenter(publicInvestmentProject.getPopulatedCenter());
        publicInvestmentProjectModel.setFormulatingUnit(publicInvestmentProject.getFormulatingUnit());
        publicInvestmentProjectModel.setSector(publicInvestmentProject.getSector());
        publicInvestmentProjectModel.setFolder(publicInvestmentProject.getFolder());
        publicInvestmentProjectModel.setExecutor(publicInvestmentProject.getExecutor());
        publicInvestmentProjectModel.setLevel(publicInvestmentProject.getLevel());
        publicInvestmentProjectModel.setSnipCode(publicInvestmentProject.getSnipCode());
        publicInvestmentProjectModel.setUniqueCode(publicInvestmentProject.getUniqueCode());
        publicInvestmentProjectModel.setName(publicInvestmentProject.getName());
        publicInvestmentProjectModel.setFunction(publicInvestmentProject.getFunction());
        publicInvestmentProjectModel.setProgram(publicInvestmentProject.getProgram());
        publicInvestmentProjectModel.setSubprogram(publicInvestmentProject.getSubprogram());
        publicInvestmentProjectModel.setFundingSource(publicInvestmentProject.getFundingSource());
        publicInvestmentProjectModel.setRegistrationDate(publicInvestmentProject.getRegistrationDate());
        publicInvestmentProjectModel.setSituation(publicInvestmentProject.getSituation());
        publicInvestmentProjectModel.setState(publicInvestmentProject.getState());
        publicInvestmentProjectModel.setClosed(publicInvestmentProject.getClosed());
        publicInvestmentProjectModel.setViabDate(publicInvestmentProject.getViabDate());
        publicInvestmentProjectModel.setViableAmount(publicInvestmentProject.getViableAmount());
        publicInvestmentProjectModel.setBeneficiary(publicInvestmentProject.getBeneficiary());
        publicInvestmentProjectModel.setObjective(publicInvestmentProject.getObjective());
        publicInvestmentProjectModel.setAlternative(publicInvestmentProject.getAlternative());
        publicInvestmentProjectModel.setCost(publicInvestmentProject.getCost());

        return publicInvestmentProjectModel;
    }

    /**
     * Transform a Collection of {@link PublicInvestmentProject} into a Collection of {@link PublicInvestmentProjectModel}.
     *
     * @param publicInvestmentProjectsCollection Objects to be transformed.
     * @return List of {@link PublicInvestmentProjectModel}.
     */
    public Collection<PublicInvestmentProjectModel> transform(Collection<PublicInvestmentProject> publicInvestmentProjectsCollection) {
        Collection<PublicInvestmentProjectModel> publicInvestmentProjectModelsCollection;

        if (publicInvestmentProjectsCollection != null && !publicInvestmentProjectsCollection.isEmpty()) {
            publicInvestmentProjectModelsCollection = new ArrayList<>();
            for (PublicInvestmentProject publicInvestmentProject : publicInvestmentProjectsCollection) {
                publicInvestmentProjectModelsCollection.add(transform(publicInvestmentProject));
            }
        } else {
            publicInvestmentProjectModelsCollection = Collections.emptyList();
        }

        return publicInvestmentProjectModelsCollection;
    }
}
