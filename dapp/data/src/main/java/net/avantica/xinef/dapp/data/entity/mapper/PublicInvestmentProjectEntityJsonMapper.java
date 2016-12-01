package net.avantica.xinef.dapp.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class PublicInvestmentProjectEntityJsonMapper {

    private final Gson gson;

    @Inject
    public PublicInvestmentProjectEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link PublicInvestmentProjectEntity}.
     *
     * @param publicInvestmentProjectJsonResponse A json representing a public investment project.
     * @return {@link PublicInvestmentProjectEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public PublicInvestmentProjectEntity transformPublicInvestmentProjectEntity(String publicInvestmentProjectJsonResponse) throws JsonSyntaxException {
        try {
            Type publicInvestmentProjectEntityType = new TypeToken<PublicInvestmentProjectEntity>() {
            }.getType();
            PublicInvestmentProjectEntity publicInvestmentProjectEntity = this.gson.fromJson(publicInvestmentProjectJsonResponse, publicInvestmentProjectEntityType);

            return publicInvestmentProjectEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link PublicInvestmentProjectEntity}.
     *
     * @param publicInvestmentProjectListJsonResponse A json representing a collection of public investment projects.
     * @return List of {@link PublicInvestmentProjectEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<PublicInvestmentProjectEntity> transformPublicInvestmentProjectEntityCollection(String publicInvestmentProjectListJsonResponse)
            throws JsonSyntaxException {

        List<PublicInvestmentProjectEntity> publicInvestmentProjectEntityCollection;
        try {
            Type listOfPublicInvestmentProjectEntityType = new TypeToken<List<PublicInvestmentProjectEntity>>() {
            }.getType();
            publicInvestmentProjectEntityCollection = this.gson.fromJson(publicInvestmentProjectListJsonResponse, listOfPublicInvestmentProjectEntityType);

            return publicInvestmentProjectEntityCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
