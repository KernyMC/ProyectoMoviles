package com.example.pryapigoogle;

public class Restaurant {
    private String name;
    private double stars;
    private String category;
    private int imageUrl;
    private double x;
    private double y;

    public Restaurant(String name, double stars, String category, int imageUrl, double x, double y) {
        this.name = name;
        this.stars = stars;
        this.category = category;
        this.imageUrl = imageUrl;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getStars() {
        return stars;
    }

    public String getCategory() {
        return category;
    }

    public int getImageUrl() {
        return imageUrl;
    }

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
}