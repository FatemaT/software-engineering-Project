package com.cs.se.ta.main;

import com.cs.se.ta.builder.ResponseBuilder;
import com.cs.se.ta.response.AttractionResponse;
import com.cs.se.ta.response.CityResponse;
import com.cs.se.ta.response.ClubResponse;
import com.cs.se.ta.response.HospitalResponse;
import com.cs.se.ta.response.HotelResponse;
import com.cs.se.ta.response.MallResponse;
import com.cs.se.ta.response.RestaurantResponse;
import com.cs.se.ta.response.ReviewResponse;
import com.cs.se.ta.response.ShowtimeResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "TouristAssistService")
public class TouristAssistService {
    public TouristAssistService() {
        super();
    }

    @WebMethod
    public CityResponse getCityFromCityName(@WebParam(name = "arg0") String cityName) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getCityFromCityName(cityName);
    }

    @WebMethod
    public CityResponse getCityFromCityCoordinates(@WebParam(name = "arg0") String cityCoordinates) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getCityFromCityCoordinates(cityCoordinates);
    }

    @WebMethod
    public CityResponse getCityFromCityId(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getCityFromCityId(cityId);
    }

    @WebMethod
    public AttractionResponse getAttractions(@WebParam(name = "arg0") String cityId) {
        System.out.println(cityId);
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getAttractions(cityId);
    }

    @WebMethod
    public ClubResponse getClubs(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getClubs(cityId);
    }

    @WebMethod
    public HospitalResponse getHospitals(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getHospitals(cityId);
    }

    @WebMethod
    public HotelResponse getHotels(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getHotels(cityId);
    }

    @WebMethod
    public MallResponse getMalls(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getMalls(cityId);
    }

    @WebMethod
    public RestaurantResponse getRestaurants(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getRestaurants(cityId);
    }

    @WebMethod
    public ReviewResponse getReviews(@WebParam(name = "arg0") String entityId) {
        System.out.println(entityId);
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getReviews(entityId);
    }

    @WebMethod
    public ShowtimeResponse getShowtimes(@WebParam(name = "arg0") String cityId) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.getShowtimes(cityId);
    }
    
    @WebMethod
    public Boolean postReview(@WebParam(name = "arg0")String entityId, @WebParam(name = "arg1")String reviewScore, @WebParam(name = "arg2")String reviewText){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Boolean postReviewResponse = responseBuilder.postReview(entityId, reviewScore, reviewText);
        return postReviewResponse;
        }
}
