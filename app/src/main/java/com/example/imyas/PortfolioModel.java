package com.example.imyas;

import java.util.Date;

public class PortfolioModel {

    private String artistId;
    private String portfolioImage;
    private String portfolioId;
    private String portfolioDescription;
    private Date portfolioDate;


    public PortfolioModel() {
    }

    public PortfolioModel(String artistId, String portfolioImage, String portfolioId, String portfolioDescription, Date portfolioDate) {
        this.artistId = artistId;
        this.portfolioImage = portfolioImage;
        this.portfolioId = portfolioId;
        this.portfolioDescription = portfolioDescription;
        this.portfolioDate = portfolioDate;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getPortfolioImage() {
        return portfolioImage;
    }

    public void setPortfolioImage(String portfolioImage) {
        this.portfolioImage = portfolioImage;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioDescription() {
        return portfolioDescription;
    }

    public void setPortfolioDescription(String portfolioDescription) {
        this.portfolioDescription = portfolioDescription;
    }

    public Date getPortfolioDate() {
        return portfolioDate;
    }

    public void setPortfolioDate(Date portfolioDate) {
        this.portfolioDate = portfolioDate;
    }
}
