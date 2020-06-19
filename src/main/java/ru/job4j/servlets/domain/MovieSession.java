package ru.job4j.servlets.domain;

import java.util.List;

public class MovieSession {

    private int id;
    private String date;
    private String filmName;
    private String hallId;
    private String sessionTime;
    private String seats;

    public MovieSession(String date, String filmName, String hallId, String sessionTime,
                        String seats) {
        this.date = date;
        this.filmName = filmName;
        this.hallId = hallId;
        this.sessionTime = sessionTime;
        this.seats = seats;
    }

    public MovieSession(int id, String date, String filmName, String hallId, String sessionTime,
                        String seats) {
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

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
