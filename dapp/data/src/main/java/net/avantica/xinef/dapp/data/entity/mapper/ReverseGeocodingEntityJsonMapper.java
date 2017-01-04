package net.avantica.xinef.dapp.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.avantica.xinef.dapp.data.entity.ReverseGeocodingEntity;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class ReverseGeocodingEntityJsonMapper {
    private final Gson gson;

    @Inject
    public ReverseGeocodingEntityJsonMapper() {
        this.gson = new Gson();
    }

    public ReverseGeocodingEntity transformReverseGeocodingEntity(String pipResultResponse) throws JsonSyntaxException {
        try {
            Type pipResult = new TypeToken<ReverseGeocodingEntity>() {
            }.getType();
            ReverseGeocodingEntity result = this.gson.fromJson(pipResultResponse, pipResult);

            return result;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
