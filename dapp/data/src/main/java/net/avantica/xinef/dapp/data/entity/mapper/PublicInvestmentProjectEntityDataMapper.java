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
     * @param userEntity Object to be transformed.
     * @return {@link PublicInvestmentProject} if valid {@link PublicInvestmentProjectEntity} otherwise null.
     */
    public PublicInvestmentProject transform(PublicInvestmentProjectEntity userEntity) {
        PublicInvestmentProject user = null;
        if (userEntity != null) {
            user = new PublicInvestmentProject();
        }

        return user;
    }

    /**
     * Transform a List of {@link PublicInvestmentProjectEntity} into a Collection of {@link PublicInvestmentProject}.
     *
     * @param userEntityCollection Object Collection to be transformed.
     * @return {@link PublicInvestmentProject} if valid {@link PublicInvestmentProjectEntity} otherwise null.
     */
    public List<PublicInvestmentProject> transform(Collection<PublicInvestmentProjectEntity> userEntityCollection) {
        List<PublicInvestmentProject> userList = new ArrayList<>(20);
        PublicInvestmentProject user;
        for (PublicInvestmentProjectEntity userEntity : userEntityCollection) {
            user = transform(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }

        return userList;
    }
}
