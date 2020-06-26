package ru.job4j.servlets.domain;

public class Seat {

    private int row;
    private int seat;
    private int price;
    private boolean occupied;

    public Seat(int row, int seat, int price, boolean occupied) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.occupied = occupied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
