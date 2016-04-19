package com.cs.se.ta.builder;

import com.cs.se.ta.gen.GenResponses;
import com.cs.se.ta.response.AttractionResponse;
import com.cs.se.ta.response.CityResponse;
import com.cs.se.ta.response.ClubResponse;
import com.cs.se.ta.response.HospitalResponse;
import com.cs.se.ta.response.HotelResponse;
import com.cs.se.ta.response.MallResponse;
import com.cs.se.ta.response.RestaurantResponse;
import com.cs.se.ta.response.ReviewResponse;
import com.cs.se.ta.response.ShowtimeResponse;

public class ResponseBuilder {
    public ResponseBuilder() {
        super();
    }
    boolean getResponseFromDB = false;
    private GenResponses genResponse = new GenResponses();

    public CityResponse getCityFromCityName(String cityName) {
        CityResponse cityResponse = new CityResponse();
        cityResponse.setCity(genResponse.getCityByCityName(cityName));
        return cityResponse;
    }

    public CityResponse getCityFromCityCoordinates(String cityCoordinates) {
        CityResponse cityResponse = new CityResponse();
        //TODO: find from coordinates
        //cityResponse.setCity(genResponse.getCity(cityCoordinates));
        return cityResponse;
    }

    public CityResponse getCityFromCityId(String cityId) {
        CityResponse cityResponse = new CityResponse();
        //TODO: find from City Id
        //cityResponse.setCity(genResponse.getCity(cityId));
        return cityResponse;
    }

    public AttractionResponse getAttractions(String cityId) {
        AttractionResponse attractionResponse = new AttractionResponse();
        attractionResponse.setAttractions(genResponse.getAttractions(cityId));
        return attractionResponse;
    }

    public ClubResponse getClubs(String cityId) {
        ClubResponse clubResponse = new ClubResponse();
        clubResponse.setClubs(genResponse.getClubs(cityId));
        return clubResponse;
    }

    public HospitalResponse getHospitals(String cityId) {
        HospitalResponse hospitalResponse = new HospitalResponse();
        hospitalResponse.setHospitals(genResponse.getHospitals(cityId));
        return hospitalResponse;
    }

    public HotelResponse getHotels(String cityId) {
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setHotels(genResponse.getHotels(cityId));
        return hotelResponse; 
    }

    public MallResponse getMalls(String cityId) {
        MallResponse mallResponse = new MallResponse();
        mallResponse.setMalls(genResponse.getMalls(cityId));
        return mallResponse;
    }

    public RestaurantResponse getRestaurants(String cityId) {
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setRestaurants(genResponse.getRestaurants(cityId));
        return restaurantResponse; 
    }

    public ReviewResponse getReviews(String entityId) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviews(genResponse.getReviews(entityId));
        return reviewResponse;
    }

    public ShowtimeResponse getShowtimes(String cityId) {
        ShowtimeResponse showtimeResponse = new ShowtimeResponse();
        showtimeResponse.setShowtimes(genResponse.getShowtimes(cityId));
        return showtimeResponse; 
    }
}
