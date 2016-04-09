package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Club;

public class ClubResponse {
    
    private Club[] clubs;
    public ClubResponse() {
        super();
    }

    public void setClubs(Club[] clubs) {
        this.clubs = clubs;
    }

    public Club[] getClubs() {
        return clubs;
    }
}
