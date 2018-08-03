package com.example.shanna.orbital2;

import com.google.firebase.database.FirebaseDatabase;

public class Feedback {
    private Float Rating;
    private String Comments;
    private String Title;
    private String FeedbackFrom;

    public Feedback(){

    }

    public Feedback(Float rating, String comments, String title, String feedbackFrom){
        this.Rating = rating;
        this.Comments = comments;
        this.Title = title;
        this.FeedbackFrom = feedbackFrom;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float rating) {
        this.Rating = rating;
    }

    public String getComments() {
        return Comments;
    }

    public void setDescription(String comments) { this.Comments = comments; }

    public String getTitle() {return Title;}

    public void setTitle(String title) {this.Title = title;}

    public String getFeedbackFrom() {return FeedbackFrom;}

    public void setFeedbackFrom(String feedbackFrom) {this.FeedbackFrom = feedbackFrom;}
}
