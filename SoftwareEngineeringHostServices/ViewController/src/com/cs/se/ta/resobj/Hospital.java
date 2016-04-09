package com.cs.se.ta.resobj;

public class Hospital {
    
    private String hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalDetails;
    private String coordinates;
    private String specializations;
    private String city;
    private String cityId;
    
    public Hospital() {
        super();
    }

    public Hospital(String hospitalId, String hospitalName, String hospitalAddress, String hospitalDetails,
                    String coordinates, String specializations, String city, String cityId) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalDetails = hospitalDetails;
        this.coordinates = coordinates;
        this.specializations = specializations;
        this.city = city;
        this.cityId = cityId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalDetails(String hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }

    public String getHospitalDetails() {
        return hospitalDetails;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public String getSpecializations() {
        return specializations;
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
