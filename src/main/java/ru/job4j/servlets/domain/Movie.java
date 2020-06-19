package ru.job4j.servlets.domain;

public class Movie {

    private String id;
    private String name;
    private String year;
    private String country;
    private String director;
    private String genre;
    private String age;
    private String ratingMPAA;
    private String time;

    public Movie(String name, String year, String country, String director, String genre,
                 String age, String ratingMPAA, String time) {
        this.name = name;
        this.year = year;
        this.country = country;
        this.director = director;
        this.genre = genre;
        this.age = age;
        this.ratingMPAA = ratingMPAA;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
