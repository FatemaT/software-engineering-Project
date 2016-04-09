package com.cs.se.ta.resobj;

public class Review {
    private String reviewId;
    private String entityId;
    private String rating;
    private String review;
    
    public Review() {
        super();
    }

    public Review(String reviewId, String entityId, String rating, String review) {
        this.reviewId = reviewId;
        this.entityId = entityId;
        this.rating = rating;
        this.review = review;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }
}
