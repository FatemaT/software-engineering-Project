package com.cs.se.ta.resobj;

public class Mall {
    
    private String mallId;
    private String mallName;
    private String mallAddress;
    private String mallDetails;
    private String coordinates;
    private String city;
    private String mall_stores;
    private String mall_brands;
    private String cityId;

    public Mall(String mallId, String mallName, String mallAddress, String mallDetails, String coordinates, String city,
                String mall_stores, String mall_brands, String cityId) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
        this.mallDetails = mallDetails;
        this.coordinates = coordinates;
        this.city = city;
        this.mall_stores = mall_stores;
        this.mall_brands = mall_brands;
        this.cityId = cityId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallAddress(String mallAddress) {
        this.mallAddress = mallAddress;
    }

    public String getMallAddress() {
        return mallAddress;
    }

    public void setMallDetails(String mallDetails) {
        this.mallDetails = mallDetails;
    }

    public String getMallDetails() {
        return mallDetails;
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

    public void setMall_stores(String mall_stores) {
        this.mall_stores = mall_stores;
    }

    public String getMall_stores() {
        return mall_stores;
    }

    public void setMall_brands(String mall_brands) {
        this.mall_brands = mall_brands;
    }

    public String getMall_brands() {
        return mall_brands;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }
    public Mall() {
        super();
    }
    
}
