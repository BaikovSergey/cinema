package ru.job4j.servlets.domain;

import java.util.List;

public class MovieSession {

    private int id;
    private String date;
    private String filmName;
    private int hallId;
    private String sessionTime;
    private List<Seat> seats;

    public MovieSession(String date, String filmName, int hallId, String sessionTime,
                        List<Seat> seats) {
        this.date = date;
        this.filmName = filmName;
        this.hallId = hallId;
        this.sessionTime = sessionTime;
        this.seats = seats;
    }

    public MovieSession(int id, String date, String filmName, int hallId, String sessionTime,
                        List<Seat> seats) {
        this.id = id;
        this.date = date;
        this.filmName = filmName;
        this.hallId = hallId;
        this.sessionTime = sessionTime;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
