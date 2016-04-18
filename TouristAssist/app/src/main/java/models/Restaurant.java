package models;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantDetails;
    private String restaurantCusines;
    private String coordinates;
    private String city;
    private String cityId;
    
    public Restaurant() {
        super();
    }

    public Restaurant(String restaurantId, String restaurantName, String restaurantAddress, String restaurantDetails,
                      String restaurantCusines, String coordinates, String city, String cityId) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantDetails = restaurantDetails;
        this.restaurantCusines = restaurantCusines;
        this.coordinates = coordinates;
        this.city = city;
        this.cityId = cityId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantDetails(String restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public String getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantCusines(String restaurantCusines) {
        this.restaurantCusines = restaurantCusines;
    }

    public String getRestaurantCusines() {
        return restaurantCusines;
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