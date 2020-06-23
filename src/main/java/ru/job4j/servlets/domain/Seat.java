package ru.job4j.servlets.domain;

public class Seat {

    private String row;
    private String seat;
    private String price;
    private boolean occupied;

    public Seat(String row, String seat, String price, boolean occupied) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.occupied = occupied;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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
