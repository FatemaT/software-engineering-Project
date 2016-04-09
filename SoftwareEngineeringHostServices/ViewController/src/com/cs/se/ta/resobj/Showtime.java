package com.cs.se.ta.resobj;

public class Showtime {
    private String showtimeId;
    private String showtimeName;
    private String showtimeAddress;
    private String showtimeDetails;
    private String showtimeCoordinates;
    private String shotimeCity;
    private String showtimeDuration;
    private String showtimeShows;
    private String cityId;
    public Showtime() {
        super();
    }

    public Showtime(String showtimeId, String showtimeName, String showtimeAddress, String showtimeDetails,
                    String showtimeCoordinates, String shotimeCity, String showtimeDuration, String showtimeShows,
                    String cityId) {
        this.showtimeId = showtimeId;
        this.showtimeName = showtimeName;
        this.showtimeAddress = showtimeAddress;
        this.showtimeDetails = showtimeDetails;
        this.showtimeCoordinates = showtimeCoordinates;
        this.shotimeCity = shotimeCity;
        this.showtimeDuration = showtimeDuration;
        this.showtimeShows = showtimeShows;
        this.cityId = cityId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeName(String showtimeName) {
        this.showtimeName = showtimeName;
    }

    public String getShowtimeName() {
        return showtimeName;
    }

    public void setShowtimeAddress(String showtimeAddress) {
        this.showtimeAddress = showtimeAddress;
    }

    public String getShowtimeAddress() {
        return showtimeAddress;
    }

    public void setShowtimeDetails(String showtimeDetails) {
        this.showtimeDetails = showtimeDetails;
    }

    public String getShowtimeDetails() {
        return showtimeDetails;
    }

    public void setShowtimeCoordinates(String showtimeCoordinates) {
        this.showtimeCoordinates = showtimeCoordinates;
    }

    public String getShowtimeCoordinates() {
        return showtimeCoordinates;
    }

    public void setShotimeCity(String shotimeCity) {
        this.shotimeCity = shotimeCity;
    }

    public String getShotimeCity() {
        return shotimeCity;
    }

    public void setShowtimeDuration(String showtimeDuration) {
        this.showtimeDuration = showtimeDuration;
    }

    public String getShowtimeDuration() {
        return showtimeDuration;
    }

    public void setShowtimeShows(String showtimeShows) {
        this.showtimeShows = showtimeShows;
    }

    public String getShowtimeShows() {
        return showtimeShows;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }
}
