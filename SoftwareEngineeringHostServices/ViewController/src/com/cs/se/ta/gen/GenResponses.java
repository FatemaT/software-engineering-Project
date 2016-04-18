package com.cs.se.ta.gen;

import com.cs.se.ta.resobj.Attraction;
import com.cs.se.ta.resobj.City;

import com.cs.se.ta.resobj.Club;

import com.cs.se.ta.resobj.Hospital;

import com.cs.se.ta.resobj.Hotel;

import com.cs.se.ta.resobj.Mall;

import com.cs.se.ta.resobj.Restaurant;

import com.cs.se.ta.resobj.Review;

import com.cs.se.ta.resobj.Showtime;

import java.util.ArrayList;
import java.util.List;

public class GenResponses {
    public GenResponses() {
        super();
    }
    private static boolean isDBDataAvailable = false;

    public City getCity(String cityId) {
        City city = new City("001", "Chicago", "00000000");
        return city;
    }

    public Attraction[] getAttractions(String cityId) {
        ArrayList<Attraction> attractions = new ArrayList<Attraction>();
        if (!isDBDataAvailable) {
            attractions.add(new Attraction("002", "Chicago Meuseum", "3241 Columbus Avenue", "Meuseum of Arts",
                                           "014342000,15672200", "20.00 USD", "Chicago", "001"));

            attractions.add(new Attraction("003", "Lake View", "3241 Da Vinci Avenue", "Lake", "014321,15691280",
                                           "Free", "Chicago", "001"));

            attractions.add(new Attraction("004", "Meuseum of Science", "3241 XYZ Avenue", "Meuseum of Science",
                                           "014321,15691280", "10.00 USD", "Chicago", "001"));
        }
        Attraction[] attractionArray = attractions.toArray(new Attraction[attractions.size()]);
        return attractionArray;
    }


    public Club[] getClubs(String cityId) {
        ArrayList<Club> clubs = new ArrayList<Club>();
        if (!isDBDataAvailable) {
            clubs.add(new Club("005", "Stephanie Club", "211 FTRE Street", "Adutls Only club", "01249876443", "Chicago",
                               "Yes", "001"));
            clubs.add(new Club("006", "Romeo Club", "211 PWQR Street", "Buzz Aldrin was here", "91991914453,981112131",
                               "Chicago", "Yes", "001"));
            clubs.add(new Club("007", "Fundamental Physics Club", "211 RLMN Street", "Obama was here",
                               "002100119,0100111", "Chicago", "NO", "001"));
        }
        Club[] clubArray = clubs.toArray(new Club[clubs.size()]);
        return clubArray;
    }

    public Hospital[] getHospitals(String cityId) {
        ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
        if (!isDBDataAvailable) {
            hospitals.add(new Hospital("008", "UIC Hospital", "3287 UIC Hospitals Road", "Best hospital of the world",
                                       "3112291,99887876", "Cardiac and Emergency", "Chicago", "001"));
            hospitals.add(new Hospital("009", "Emergency Hospital", "Gamma Beta Street", "Automated surgery",
                                       "31198791,99831286", "Cardiac and ENT", "Chicago", "001"));
            hospitals.add(new Hospital("010", "Rush Hospital", "42nd South Street", "Alpha hospital of North Chicago",
                                       "44553291,94788376", "Cardiac and Emergency", "Chicago", "001"));
        }
        Hospital[] hospitalArray = hospitals.toArray(new Hospital[hospitals.size()]);
        return hospitalArray;
    }

    public Hotel[] getHotels(String cityId) {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        if (!isDBDataAvailable) {
            hotels.add(new Hotel("011", "Chicago Hotel", "121 East Avenue", "Lake View Hotels", "000101110,7787678",
                                 "Chicago", "001"));
            hotels.add(new Hotel("012", "Lake View Hotels", "134 South Wabash Avenue", "Alpha Hotel of the Downtown",
                                 "53454343,14253542", "Chicago", "001"));
            hotels.add(new Hotel("013", "Green Graden Hotels", "112 Alpha Avenue", "Hotel with Indian Cusine",
                                 "531121343,1412121542", "Chicago", "001"));
        }
        Hotel[] hotelArray = hotels.toArray(new Hotel[hotels.size()]);
        return hotelArray;
    }

    public Mall[] getMalls(String cityId) {
        ArrayList<Mall> malls = new ArrayList<Mall>();
        if (!isDBDataAvailable) {
            malls.add(new Mall("014", "Chicago Central Mall", "454 Beta Avenue", "Mall with elevator",
                               "000011010334, 454354322111", "Chicago", "Jewelery, Boots", "Nike, Adidas", "001"));
            malls.add(new Mall("015", "Chicago Eastern Mall", "XYZ Beta Avenue", "Mall with british stores",
                               "000011010334, 454354322111", "Chicago", "Golf Accessories, Winter Boots",
                               "Golf Stands, Stallion", "001"));
            malls.add(new Mall("016", "Chicago Western Mall", "NXW Beta Avenue", "Mall with best accessories",
                               "987678901111, 123454321", "Chicago", "Jewelery, Perfumes, Boots", "Alias, XYZ, Adidas",
                               "001"));
        }
        Mall[] mallArray = malls.toArray(new Mall[malls.size()]);
        return mallArray;

    }

    public Restaurant[] getRestaurants(String cityId) {
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        if (!isDBDataAvailable) {
            restaurants.add(new Restaurant("017", "Opart Thai", "2311 Alex Wabash", "Best Thai Cusine", "Thai, Chinese",
                                           "001213131, 0132142312", "Chicago", "001"));
            restaurants.add(new Restaurant("018", "NXZ Italia", "2122 Beta Avenue", "Best Italian Restaurant",
                                           "Italian, Mexican", "003454131, 01356765412", "Chicago", "001"));
            restaurants.add(new Restaurant("019", "YYY Chinese", "4433 Delta Street", "Best Chinese Restaurant",
                                           "Chinese, Thai", "45642325, 345675432", "Chicago", "001"));

        }
        Restaurant[] restaruantArray = restaurants.toArray(new Restaurant[restaurants.size()]);
        return restaruantArray;
    }

    public Review[] getReviews(String entityId) {
        ArrayList<Review> reviews = new ArrayList<Review>();
        if (!isDBDataAvailable) {
            reviews.add(new Review("020", "004", "3.5", "Very nice Place"));
            reviews.add(new Review("021", "006", "4", "Awesome Place"));
            reviews.add(new Review("022", "010", "5", "Best Place"));
        }
        Review[] reviewArray = reviews.toArray(new Review[reviews.size()]);
        return  reviewArray;
    }

    public Showtime[] getShowtimes(String cityId) {
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        if (!isDBDataAvailable) {
            showtimes.add(new Showtime("023", "Mowgli", "Amc River East", "4 shows", "009876t5678,09876789", "Chicago",
                                       "2 hours", "All day", "Chicago"));
            showtimes.add(new Showtime("024", "Batman v Superman", "Amc River East", "4 shows", "009876t5678,09876789", "Chicago",
                                       "2 hours", "All day", "Chicago"));
            showtimes.add(new Showtime("025", "10 Cloverfield", "Amc River East", "4 shows", "009876t5678,09876789", "Chicago", "2 hours", "All day", "Chicago"));

        }
        
        Showtime[] showtimeArray = showtimes.toArray(new Showtime[showtimes.size()]);
        return showtimeArray;
    }
}
