package com.cs.se.ta.response;

import com.cs.se.ta.resobj.City;

public class CityResponse {
    private City city;
    public CityResponse() {
        super();
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }
}
