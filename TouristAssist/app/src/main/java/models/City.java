package models;

public class City {
    private String cityId;
    private String cityName;
    private String coordinates;
    public City() {
        super();
    }

    public City(String cityId, String cityName, String coordinates) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.coordinates = coordinates;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
