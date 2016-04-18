package models;

import java.io.Serializable;

public class Hotel implements Serializable {

    private String hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelDetails;
    private String coordinates;
    private String city;
    private String cityId;

    public Hotel() {
        super();
    }

    public Hotel(String hotelId, String hotelName, String hotelAddress, String hotelDetails, String coordinates,
                 String city, String cityId) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelDetails = hotelDetails;
        this.coordinates = coordinates;
        this.city = city;
        this.cityId = cityId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelDetails(String hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public String getHotelDetails() {
        return hotelDetails;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
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