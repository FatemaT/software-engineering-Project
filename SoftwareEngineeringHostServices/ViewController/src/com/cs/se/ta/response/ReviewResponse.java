package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Review;

public class ReviewResponse {
    
    private Review[] reviews;
    
    public ReviewResponse() {
        super();
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }

    public Review[] getReviews() {
        return reviews;
    }
}
