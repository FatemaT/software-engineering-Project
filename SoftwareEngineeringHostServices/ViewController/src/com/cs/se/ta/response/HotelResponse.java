package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Hotel;

public class HotelResponse {
    private Hotel[] hotels;
    public HotelResponse() {
        super();
    }

    public void setHotels(Hotel[] hotels) {
        this.hotels = hotels;
    }

    public Hotel[] getHotels() {
        return hotels;
    }
}
