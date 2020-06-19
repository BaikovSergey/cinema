package ru.job4j.servlets.domain;

public class Seat {

    private String name;
    private String price;
    private boolean occupied;

    public Seat(String name, String price, boolean occupied) {
        this.name = name;
        this.price = price;
        this.occupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
