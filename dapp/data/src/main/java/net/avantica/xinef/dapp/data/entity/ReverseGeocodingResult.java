package net.avantica.xinef.dapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReverseGeocodingResult {
    @SerializedName("address_components")
    private List<AddressComponent> addressComponents;
    @SerializedName("formatted_address")
    private String formattedAddress;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("types")
    private List<String> types;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
