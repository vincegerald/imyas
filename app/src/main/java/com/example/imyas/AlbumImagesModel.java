package com.example.imyas;

import java.util.Date;

public class AlbumImagesModel {

    private String imageId;
    private String portfolioId;
    private String imageImage;
    private String imageDescription;
    private Date imageDate;

    public AlbumImagesModel() {
    }

    public AlbumImagesModel(String imageId, String portfolioId, String imageImage, String imageDescription, Date imageDate) {
        this.imageId = imageId;
        this.portfolioId = portfolioId;
        this.imageImage = imageImage;
        this.imageDescription = imageDescription;
        this.imageDate = imageDate;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getImageImage() {
        return imageImage;
    }

    public void setImageImage(String imageImage) {
        this.imageImage = imageImage;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public Date getImageDate() {
        return imageDate;
    }

    public void setImageDate(Date imageDate) {
        this.imageDate = imageDate;
    }
}
