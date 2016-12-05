package net.avantica.xinef.dapp.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.avantica.xinef.dapp.data.entity.PIPResult;
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
     * Transform from valid json string to {@link PIPResult}.
     *
     * @param pipResultResponse A json representing a public investment project.
     * @return {@link PIPResult}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public PIPResult transformPublicInvestmentProjectEntity(String pipResultResponse) throws JsonSyntaxException {
        try {
            Type pipResult = new TypeToken<PIPResult>() {
            }.getType();
            PIPResult result = this.gson.fromJson(pipResultResponse, pipResult);

            return result;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link PublicInvestmentProjectEntity}.
     *
     * @param pipResultResponse A json representing a collection of public investment projects.
     * @return List of {@link PublicInvestmentProjectEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<PIPResult> transformPublicInvestmentProjectEntityCollection(String pipResultResponse)
            throws JsonSyntaxException {

        List<PIPResult> pipResults;
        try {
            Type listOfPublicInvestmentProjectEntityType = new TypeToken<List<PIPResult>>() {
            }.getType();
            pipResults = this.gson.fromJson(pipResultResponse, listOfPublicInvestmentProjectEntityType);

            return pipResults;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
