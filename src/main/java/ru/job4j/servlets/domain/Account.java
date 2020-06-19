package ru.job4j.servlets.domain;

public class Account {

    private int id;
    private String login;
    private String email;

    public Account(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public Account(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
