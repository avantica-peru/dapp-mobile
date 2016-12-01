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
