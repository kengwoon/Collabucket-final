package com.example.shanna.orbital2;

public class Upload {
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getUrl() {
        return mImageUrl;
    }

    public void setUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
