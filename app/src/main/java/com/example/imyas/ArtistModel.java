package com.example.imyas;

public class ArtistModel {

    private String id, firstname, lastname, contactNumber, image, email, gender, password, hair, makeUp;
    private Double rating;

    public ArtistModel() {
    }

    public ArtistModel(String id, String firstname, String lastname, String contactNumber, String image, String email, String gender, String password, String hair, String makeUp, Double rating) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
        this.image = image;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.hair = hair;
        this.makeUp = makeUp;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getMakeUp() {
        return makeUp;
    }

    public void setMakeUp(String makeUp) {
        this.makeUp = makeUp;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
