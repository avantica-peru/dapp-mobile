package net.avantica.xinef.dapp.data.entity.mapper;


import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;
import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link PublicInvestmentProjectEntity} (in the data layer) to {@link PublicInvestmentProject} in the
 * domain layer.
 */
@Singleton
public class PublicInvestmentProjectEntityDataMapper {

    @Inject
    public PublicInvestmentProjectEntityDataMapper() {
    }

    /**
     * Transform a {@link PublicInvestmentProjectEntity} into an {@link PublicInvestmentProject}.
     *
     * @param publicInvestmentProjectEntity Object to be transformed.
     * @return {@link PublicInvestmentProject} if valid {@link PublicInvestmentProjectEntity} otherwise null.
     */
    public PublicInvestmentProject transform(PublicInvestmentProjectEntity publicInvestmentProjectEntity) {
        PublicInvestmentProject publicInvestmentProject = null;
        if (publicInvestmentProjectEntity != null) {
            publicInvestmentProject = new PublicInvestmentProject();
            publicInvestmentProject.setDepartment(publicInvestmentProjectEntity.getDepartment());
            publicInvestmentProject.setProvince(publicInvestmentProjectEntity.getProvince());
            publicInvestmentProject.setDistrict(publicInvestmentProjectEntity.getDistrict());
            publicInvestmentProject.setZipCode(publicInvestmentProjectEntity.getZipCode());
            publicInvestmentProject.setLatitude(publicInvestmentProjectEntity.getLatitude());
            publicInvestmentProject.setLongitude(publicInvestmentProjectEntity.getLongitude());
            publicInvestmentProject.setPopulatedCenter(publicInvestmentProjectEntity.getPopulatedCenter());
            publicInvestmentProject.setFormulatingUnit(publicInvestmentProjectEntity.getFormulatingUnit());
            publicInvestmentProject.setSector(publicInvestmentProjectEntity.getSector());
            publicInvestmentProject.setFolder(publicInvestmentProjectEntity.getFolder());
            publicInvestmentProject.setExecutor(publicInvestmentProjectEntity.getExecutor());
            publicInvestmentProject.setLevel(publicInvestmentProjectEntity.getLevel());
            publicInvestmentProject.setSnipCode(publicInvestmentProjectEntity.getSnipCode());
            publicInvestmentProject.setUniqueCode(publicInvestmentProjectEntity.getUniqueCode());
            publicInvestmentProject.setName(publicInvestmentProjectEntity.getName());
            publicInvestmentProject.setFunction(publicInvestmentProjectEntity.getFunction());
            publicInvestmentProject.setProgram(publicInvestmentProjectEntity.getProgram());
            publicInvestmentProject.setSubprogram(publicInvestmentProjectEntity.getSubprogram());
            publicInvestmentProject.setFundingSource(publicInvestmentProjectEntity.getFundingSource());
            publicInvestmentProject.setRegistrationDate(publicInvestmentProjectEntity.getRegistrationDate());
            publicInvestmentProject.setSituation(publicInvestmentProjectEntity.getSituation());
            publicInvestmentProject.setState(publicInvestmentProjectEntity.getState());
            publicInvestmentProject.setClosed(publicInvestmentProjectEntity.getClosed());
            publicInvestmentProject.setViabDate(publicInvestmentProjectEntity.getViabDate());
            publicInvestmentProject.setViableAmount(publicInvestmentProjectEntity.getViableAmount());
            publicInvestmentProject.setBeneficiary(publicInvestmentProjectEntity.getBeneficiary());
            publicInvestmentProject.setObjective(publicInvestmentProjectEntity.getObjective());
            publicInvestmentProject.setAlternative(publicInvestmentProjectEntity.getAlternative());
            publicInvestmentProject.setCost(publicInvestmentProjectEntity.getCost());
        }

        return publicInvestmentProject;
    }

    /**
     * Transform a List of {@link PublicInvestmentProjectEntity} into a Collection of {@link PublicInvestmentProject}.
     *
     * @param publicInvestmentProjectEntityCollection Object Collection to be transformed.
     * @return {@link PublicInvestmentProject} if valid {@link PublicInvestmentProjectEntity} otherwise null.
     */
    public List<PublicInvestmentProject> transform(Collection<PublicInvestmentProjectEntity> publicInvestmentProjectEntityCollection) {
        List<PublicInvestmentProject> publicInvestmentProjectList = new ArrayList<>(20);
        PublicInvestmentProject publicInvestmentProject;
        for (PublicInvestmentProjectEntity publicInvestmentProjectEntity : publicInvestmentProjectEntityCollection) {
            publicInvestmentProject = transform(publicInvestmentProjectEntity);
            if (publicInvestmentProject != null) {
                publicInvestmentProjectList.add(publicInvestmentProject);
            }
        }

        return publicInvestmentProjectList;
    }
}
