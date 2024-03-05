package com.example.pryapigoogle;

public class Restaurant {
    private String name;
    private double stars;
    private double distance;
    private String imageUrl;
    private double x;
    private double y;

    // Constructor, getters and setters
    public Restaurant(String name, double stars, double distance, String imageUrl, double x, double y) {
        this.name = name;
        this.stars = stars;
        this.distance = distance;
        this.imageUrl = imageUrl;
        this.x = x;
        this.y = y;
    }

    // Getters and setters for x and y
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getStars() {
        return stars;
    }

    public double getDistance() {
        return distance;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}