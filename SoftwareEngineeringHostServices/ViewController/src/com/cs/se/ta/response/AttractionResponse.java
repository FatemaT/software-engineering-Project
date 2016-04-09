package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Attraction;

public class AttractionResponse {
    private Attraction[] attractions;
    
    public AttractionResponse() {
        super();
    }

    public void setAttractions(Attraction[] attractions) {
        this.attractions = attractions;
    }

    public Attraction[] getAttractions() {
        return attractions;
    }
}
