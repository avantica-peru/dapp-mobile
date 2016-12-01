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
     * @param userJsonResponse A json representing a user profile.
     * @return {@link PublicInvestmentProjectEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public PublicInvestmentProjectEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        try {
            Type userEntityType = new TypeToken<PublicInvestmentProjectEntity>() {
            }.getType();
            PublicInvestmentProjectEntity publicInvestmentProjectEntity = this.gson.fromJson(userJsonResponse, userEntityType);

            return publicInvestmentProjectEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link PublicInvestmentProjectEntity}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link PublicInvestmentProjectEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<PublicInvestmentProjectEntity> transformUserEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {

        List<PublicInvestmentProjectEntity> userEntityCollection;
        try {
            Type listOfUserEntityType = new TypeToken<List<PublicInvestmentProjectEntity>>() {
            }.getType();
            userEntityCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);

            return userEntityCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
