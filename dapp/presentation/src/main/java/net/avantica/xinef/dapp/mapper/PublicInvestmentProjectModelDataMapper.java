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
     * @param user Object to be transformed.
     * @return {@link PublicInvestmentProjectModel}.
     */
    public PublicInvestmentProjectModel transform(PublicInvestmentProject user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        PublicInvestmentProjectModel userModel = new PublicInvestmentProjectModel();

        return userModel;
    }

    /**
     * Transform a Collection of {@link PublicInvestmentProject} into a Collection of {@link PublicInvestmentProjectModel}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link PublicInvestmentProjectModel}.
     */
    public Collection<PublicInvestmentProjectModel> transform(Collection<PublicInvestmentProject> usersCollection) {
        Collection<PublicInvestmentProjectModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (PublicInvestmentProject user : usersCollection) {
                userModelsCollection.add(transform(user));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
