package net.avantica.xinef.dapp.data.cache.serializer;

import com.google.gson.Gson;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

    private final Gson gson = new Gson();

    @Inject
    public JsonSerializer() {
    }

    /**
     * Serialize an object to Json.
     *
     * @param userEntity {@link PublicInvestmentProjectEntity} to serialize.
     */
    public String serialize(PublicInvestmentProjectEntity userEntity) {
        String jsonString = gson.toJson(userEntity, PublicInvestmentProjectEntity.class);
        return jsonString;
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link PublicInvestmentProjectEntity}
     */
    public PublicInvestmentProjectEntity deserialize(String jsonString) {
        PublicInvestmentProjectEntity userEntity = gson.fromJson(jsonString, PublicInvestmentProjectEntity.class);
        return userEntity;
    }
}
