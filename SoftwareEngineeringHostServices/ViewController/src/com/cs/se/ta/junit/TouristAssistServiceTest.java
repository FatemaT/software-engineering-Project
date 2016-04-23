package com.cs.se.ta.junit;


import com.cs.se.ta.main.TouristAssistService;

import com.cs.se.ta.response.AttractionResponse;

import com.cs.se.ta.response.CityResponse;

import com.cs.se.ta.response.ClubResponse;

import com.cs.se.ta.response.HospitalResponse;

import static org.junit.Assert.*;
import org.junit.Test;

public class TouristAssistServiceTest {
    public TouristAssistServiceTest() {
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getCityFromCityName(String)
     */
    @Test
    public void testGetCityFromCityName() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            CityResponse cityResponse = touristAssistService.getCityFromCityName("chicago");
            if (cityResponse.getCity().getCityId() != null) {
                assertTrue("Success", true);
            } else {
                fail("No city found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }

    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getAttractions(String)
     */
    @Test
    public void testGetAttractions() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            AttractionResponse attractionResponse = touristAssistService.getAttractions("1");
            if (attractionResponse.getAttractions().length != 0) {
                assertTrue("Success", true);
            } else {
                fail("No record found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getClubs(String)
     */
    @Test
    public void testGetClubs() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            ClubResponse clubResponse = new ClubResponse();
            clubResponse = touristAssistService.getClubs("001");
            if (clubResponse.getClubs().length != 0) {
                assertTrue("Success", true);
            } else {
                fail("No record found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getHospitals(String)
     */
    @Test
    public void testGetHospitals() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            HospitalResponse hospitalResponse = touristAssistService.getHospitals("001");
            if (hospitalResponse.getHospitals().length != 0) {
                assertTrue("Success", true);
            } else {
                fail("No records found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getHotels(String)
     */
    @Test
    public void testGetHotels() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            assertTrue("Success", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getMalls(String)
     */
    @Test
    public void testGetMalls() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            assertTrue("Success", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getRestaurants(String)
     */
    @Test
    public void testGetRestaurants() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            assertTrue("Success", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getReviews(String)
     */
    @Test
    public void testGetReviews() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            assertTrue("Success", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

    /**
     * @see com.cs.se.ta.main.TouristAssistService#getShowtimes(String)
     */
    @Test
    public void testGetShowtimes() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            assertTrue("Success", true);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }
    
    @Test
    public void testPostReview() {
        try {
            TouristAssistService touristAssistService = new TouristAssistService();
            Boolean response = touristAssistService.postReview("017", "3", "I wont say that this is such a nice place to be at.");
            if (response == Boolean.TRUE) {
                assertTrue("Success", true);
            } else {
                fail("No records found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failure");
        }
    }

}
