package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Restaurant;

public class RestaurantResponse {
    
    private Restaurant[] restaurants;
    
    public RestaurantResponse() {
        super();
    }

    public void setRestaurants(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    public Restaurant[] getRestaurants() {
        return restaurants;
    }
}
