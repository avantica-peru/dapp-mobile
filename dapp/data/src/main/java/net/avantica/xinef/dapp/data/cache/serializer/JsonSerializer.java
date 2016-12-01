package net.avantica.xinef.dapp.data.cache.serializer;

import com.google.gson.Gson;

import net.avantica.xinef.dapp.data.entity.PublicInvestmentProjectEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class public investment project as Serializer/Deserializer for public investment project entities.
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
     * @param publicInvestmentProjectEntity {@link PublicInvestmentProjectEntity} to serialize.
     */
    public String serialize(PublicInvestmentProjectEntity publicInvestmentProjectEntity) {
        String jsonString = gson.toJson(publicInvestmentProjectEntity, PublicInvestmentProjectEntity.class);
        return jsonString;
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link PublicInvestmentProjectEntity}
     */
    public PublicInvestmentProjectEntity deserialize(String jsonString) {
        PublicInvestmentProjectEntity publicInvestmentProjectEntity = gson.fromJson(jsonString, PublicInvestmentProjectEntity.class);
        return publicInvestmentProjectEntity;
    }
}
