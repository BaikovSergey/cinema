package ru.job4j.servlets.domain;

public class Ticket {

    private String data;
    private String film;
    private String time;
    private String hall;
    private String seat;
    private String price;

    public Ticket(String data, String film, String time, String hall, String seat, String price) {
        this.data = data;
        this.film = film;
        this.time = time;
        this.hall = hall;
        this.seat = seat;
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
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
}
