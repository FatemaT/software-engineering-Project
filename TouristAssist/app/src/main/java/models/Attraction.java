package models;

import java.io.Serializable;

public class Attraction implements Serializable {

    private String attractionId;
    private String attractionName;
    private String attractionAddress;
    private String attractionDetails;
    private String coordinates;
    private String fee;
    private String city;
    private String cityId;
    public Attraction() {
        super();
    }

    public Attraction(String attractionId, String attractionName, String attractionAddress, String attractionDetails,
                      String coordinates, String fee, String city, String cityId) {
        this.attractionId = attractionId;
        this.attractionName = attractionName;
        this.attractionAddress = attractionAddress;
        this.attractionDetails = attractionDetails;
        this.coordinates = coordinates;
        this.fee = fee;
        this.city = city;
        this.cityId = cityId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionAddress(String attractionAddress) {
        this.attractionAddress = attractionAddress;
    }

    public String getAttractionAddress() {
        return attractionAddress;
    }

    public void setAttractionDetails(String attractionDetails) {
        this.attractionDetails = attractionDetails;
    }

    public String getAttractionDetails() {
        return attractionDetails;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee() {
        return fee;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }
}
