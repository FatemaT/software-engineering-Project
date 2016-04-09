package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Showtime;

public class ShowtimeResponse {
    
    private Showtime[] showtimes;
    public ShowtimeResponse() {
        super();
    }

    public void setShowtimes(Showtime[] showtimes) {
        this.showtimes = showtimes;
    }

    public Showtime[] getShowtimes() {
        return showtimes;
    }
}
