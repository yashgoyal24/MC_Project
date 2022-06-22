package com.mc2022.template.model;

public class CarInfo {
    private String imageUrl;
    private String transmission;
    private String manYear;
    private String brand;
    private String kmTravelled;
    private String fuelType;
    private String engineSize;
    private String mileage;
    private String seller_email;

    public CarInfo() {

    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setManYear(String manYear) {
        this.manYear = manYear;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setKmTravelled(String kmTravelled) {
        this.kmTravelled = kmTravelled;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getManYear() {
        return manYear;
    }

    public String getBrand() {
        return brand;
    }

    public String getKmTravelled() {
        return kmTravelled;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public String getMileage() {
        return mileage;
    }
}
