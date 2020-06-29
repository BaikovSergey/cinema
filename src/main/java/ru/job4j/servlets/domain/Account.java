package ru.job4j.servlets.domain;

public class Account {

    private int id;
    private String name;
    private String phoneNumber;
    private double sum;

    public Account(String name, String phoneNumber, double sum) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sum = sum;

    }

    public Account(int id, String name, String phoneNumber, double sum) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
