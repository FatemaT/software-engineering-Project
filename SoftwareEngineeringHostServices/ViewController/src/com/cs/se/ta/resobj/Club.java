package com.cs.se.ta.resobj;

public class Club {
    private String clubId;
    private String clubName;
    private String clubAddress;
    private String clubDetails;
    private String coordinates;
    private String city;
    private String club_disc;
    private String cityId;
    
    public Club() {
        super();
    }

    public Club(String clubId, String clubName, String clubAddress, String clubDetails, String coordinates, String city,
                String club_disc, String cityId) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubAddress = clubAddress;
        this.clubDetails = clubDetails;
        this.coordinates = coordinates;
        this.city = city;
        this.club_disc = club_disc;
        this.cityId = cityId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubAddress(String clubAddress) {
        this.clubAddress = clubAddress;
    }

    public String getClubAddress() {
        return clubAddress;
    }

    public void setClubDetails(String clubDetails) {
        this.clubDetails = clubDetails;
    }

    public String getClubDetails() {
        return clubDetails;
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

    public void setClub_disc(String club_disc) {
        this.club_disc = club_disc;
    }

    public String getClub_disc() {
        return club_disc;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }
}
