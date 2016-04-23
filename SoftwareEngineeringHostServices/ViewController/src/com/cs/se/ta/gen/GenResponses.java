package com.cs.se.ta.gen;

import com.cs.se.ta.db.DBConnectionMgr;
import com.cs.se.ta.db.SQLAggregatedConnectionObjects;
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

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenResponses {
    public GenResponses() {
        super();
    }
    private static boolean isDBDataAvailable = true;
    private DBConnectionMgr connectionMgr = DBConnectionMgr.getInstnace();

    public City getCityByCityName(String cityName) {
        City city = null;
        if (!isDBDataAvailable) {
            city = new City("001", "Chicago", "00000000");
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from city where UPPER(city_name) LIKE UPPER('%" + cityName + "%')";
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();

            try {
                while (resultSet.next()) {
                    String city_id = resultSet.getString("city_id"), city_name =
                        resultSet.getString("city_name"), city_cdt = resultSet.getString("city_cdt");
                    city = new City(city_id, city_name, city_cdt);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from atrxns where atrxn_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String atrxn_id = resultSet.getString("atrxn_id"), atrxn_name =
                        resultSet.getString("atrxn_name"), atrxn_addr = resultSet.getString("atrxn_addr"), atrxn_cdt =
                        resultSet.getString("atrxn_cdt"), atrxn_fee = resultSet.getString("atrxn_fee"), atrxn_ct =
                        resultSet.getString("atrxn_ct"), atrxn_ct_id = resultSet.getString("atrxn_ct_id"), atrxn_dtls =
                        resultSet.getString("atrxn_dtls");
                    attractions.add(new Attraction(atrxn_id, atrxn_name, atrxn_addr, atrxn_dtls, atrxn_cdt, atrxn_fee,
                                                   atrxn_ct, atrxn_ct_id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from CLUBS where CLUB_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String club_id = resultSet.getString("club_id"), club_name =
                        resultSet.getString("club_name"), club_addr = resultSet.getString("club_addr"), club_dtls =
                        resultSet.getString("club_dtls"), CLUB_cdt = resultSet.getString("CLUB_cdt"), CLUB_DISC =
                        resultSet.getString("CLUB_DISC"), CLUB_ct = resultSet.getString("CLUB_ct"), CLUB_ct_id =
                        resultSet.getString("CLUB_ct_id");
                    clubs.add(new Club(club_id, club_name, club_addr, club_dtls, CLUB_cdt, CLUB_ct, CLUB_DISC,
                                       CLUB_ct_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from HOSPITALS where HOSPITAL_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String HOSPITAL_id = resultSet.getString("HOSPITAL_id"), HOSPITAL_name =
                        resultSet.getString("HOSPITAL_name"), HOSPITAL_addr =
                        resultSet.getString("HOSPITAL_addr"), HOSPITAL_dtls =
                        resultSet.getString("HOSPITAL_dtls"), HOSPITAL_cdt =
                        resultSet.getString("HOSPITAL_cdt"), HOSPITAL_SPL =
                        resultSet.getString("HOSPITAL_SPL"), HOSPITAL_ct =
                        resultSet.getString("HOSPITAL_ct"), HOSPITAL_ct_id = resultSet.getString("HOSPITAL_ct_id");
                    hospitals.add(new Hospital(HOSPITAL_id, HOSPITAL_name, HOSPITAL_addr, HOSPITAL_dtls, HOSPITAL_cdt,
                                               HOSPITAL_SPL, HOSPITAL_ct, HOSPITAL_ct_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from HOTELS where HOTEL_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String HOTEL_id = resultSet.getString("HOTEL_id"), HOTEL_name =
                        resultSet.getString("HOTEL_name"), HOTEL_addr = resultSet.getString("HOTEL_addr"), HOTEL_dtls =
                        resultSet.getString("HOTEL_dtls"), HOTEL_cdt = resultSet.getString("HOTEL_cdt"), HOTEL_ct =
                        resultSet.getString("HOTEL_ct"), HOTEL_ct_id = resultSet.getString("HOTEL_ct_id");
                    hotels.add(new Hotel(HOTEL_id, HOTEL_name, HOTEL_addr, HOTEL_dtls, HOTEL_cdt, HOTEL_ct,
                                         HOTEL_ct_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from MALLS where MALL_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String MALL_id = resultSet.getString("MALL_id"), MALL_name =
                        resultSet.getString("MALL_name"), MALL_addr = resultSet.getString("MALL_addr"), MALL_dtls =
                        resultSet.getString("MALL_dtls"), MALL_cdt = resultSet.getString("MALL_cdt"), MALL_ct =
                        resultSet.getString("MALL_ct"), MALL_ct_id = resultSet.getString("MALL_ct_id"), MALL_STORE =
                        resultSet.getString("MALL_STORE"), MALL_BRAND = resultSet.getString("MALL_BRAND");
                    malls.add(new Mall(MALL_id, MALL_name, MALL_addr, MALL_dtls, MALL_cdt, MALL_ct, MALL_STORE,
                                       MALL_BRAND, MALL_ct_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from RSTRNTS where RSTRNT_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String RSTRNT_id = resultSet.getString("RSTRNT_id"), RSTRNT_name =
                        resultSet.getString("RSTRNT_name"), RSTRNT_addr =
                        resultSet.getString("RSTRNT_addr"), RSTRNT_dtls =
                        resultSet.getString("RSTRNT_dtls"), RSTRNT_cdt = resultSet.getString("RSTRNT_cdt"), RSTRNT_ct =
                        resultSet.getString("RSTRNT_ct"), RSTRNT_ct_id =
                        resultSet.getString("RSTRNT_ct_id"), RSTRNT_CUS = resultSet.getString("RSTRNT_CUS");
                    restaurants.add(new Restaurant(RSTRNT_id, RSTRNT_name, RSTRNT_addr, RSTRNT_dtls, RSTRNT_CUS,
                                                   RSTRNT_cdt, RSTRNT_ct, RSTRNT_ct_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from REVIEWS where ET_ID=" + entityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String REVIEW_id = resultSet.getString("REVIEW_id"), ET_ID =
                        resultSet.getString("ET_ID"), REVIEW_RVW = resultSet.getString("REVIEW_RVW"), REVIEW_RATING =
                        resultSet.getString("REVIEW_RATING");
                    reviews.add(new Review(REVIEW_id, ET_ID, REVIEW_RATING, REVIEW_RVW));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Review[] reviewArray = reviews.toArray(new Review[reviews.size()]);
        return reviewArray;
    }

    public Showtime[] getShowtimes(String cityId) {
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        if (!isDBDataAvailable) {
            showtimes.add(new Showtime("023", "Mowgli", "Amc River East", "4 shows", "009876t5678,09876789", "Chicago",
                                       "2 hours", "All day", "Chicago"));
            showtimes.add(new Showtime("024", "Batman v Superman", "Amc River East", "4 shows", "009876t5678,09876789",
                                       "Chicago", "2 hours", "All day", "Chicago"));
            showtimes.add(new Showtime("025", "10 Cloverfield", "Amc River East", "4 shows", "009876t5678,09876789",
                                       "Chicago", "2 hours", "All day", "Chicago"));

        } else {
            SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
            String query = "select * from SHWTMS where SHWTM_ct_id=" + cityId;
            sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
            ResultSet resultSet = sqlObjects.getResultSet();
            try {
                while (resultSet.next()) {
                    String SHWTM_id = resultSet.getString("SHWTM_id"), SHWTM_name =
                        resultSet.getString("SHWTM_name"), SHWTM_addr = resultSet.getString("SHWTM_addr"), SHWTM_dtls =
                        resultSet.getString("SHWTM_dtls"), SHWTM_cdt = resultSet.getString("SHWTM_cdt"), SHWTM_ct =
                        resultSet.getString("SHWTM_ct"), SHWTM_ct_id = resultSet.getString("SHWTM_ct_id"), SHWTM_DRN =
                        resultSet.getString("SHWTM_DRN"), SHWTM_SHWS = resultSet.getString("SHWTM_SHWS");
                    showtimes.add(new Showtime(SHWTM_id, SHWTM_name, SHWTM_addr, SHWTM_dtls, SHWTM_cdt, SHWTM_ct,
                                               SHWTM_DRN, SHWTM_SHWS, SHWTM_ct_id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionMgr.closeConnectionObjects(sqlObjects);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Showtime[] showtimeArray = showtimes.toArray(new Showtime[showtimes.size()]);
        return showtimeArray;
    }
    
    public Boolean postReview(String entityId, String reviewScore, String reviewText){
        Boolean response = Boolean.FALSE;
        int latest_id = getLatestId();
        if (latest_id != 0){
                SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
                String query = "INSERT INTO `tourist_assist`.`REVIEWS` " +
                    "(`REVIEW_id`, `ET_ID`, `REVIEW_RVW`, `REVIEW_RATING`)" +
                    "VALUES ('"+(latest_id+1)+"', '"+entityId+"', '"+reviewText+"', '"+reviewScore+"')";
                sqlObjects = connectionMgr.insertOrUpdateObjectsUsingQuery(query, sqlObjects);
                
                try {
                    int rowinserted = sqlObjects.getStatement().executeUpdate(query);
                    if (rowinserted == 1){
                        response = Boolean.TRUE;
                        this.updateLatestID(latest_id);
                            } else {
                        response = Boolean.FALSE;
                        }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connectionMgr.closeConnectionObjects(sqlObjects);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        return response;
        }

    private int getLatestId() {
        int latest_id = 0;
        SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
        String query = "select * from LATEST_ID ORDER BY LATEST_ID DESC LIMIT 1";
        sqlObjects = connectionMgr.fetchResultSetFromQuery(query, sqlObjects);
        ResultSet resultSet = sqlObjects.getResultSet();
        try {
            while (resultSet.next()) {
                latest_id = resultSet.getInt("LATEST_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionMgr.closeConnectionObjects(sqlObjects);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return latest_id;
    }

    private void updateLatestID(int latest_id) {
        SQLAggregatedConnectionObjects sqlObjects = new SQLAggregatedConnectionObjects();
        String query = "UPDATE `tourist_assist`.`LATEST_ID` " +
            "SET `LATEST_ID`="+(latest_id+1)+" where `LATEST_ID`="+latest_id;
        sqlObjects = connectionMgr.insertOrUpdateObjectsUsingQuery(query, sqlObjects);
        try {
                int rowinserted = sqlObjects.getStatement().executeUpdate(query);
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionMgr.closeConnectionObjects(sqlObjects);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
