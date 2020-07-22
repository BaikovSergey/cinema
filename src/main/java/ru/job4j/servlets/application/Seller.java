package ru.job4j.servlets.application;

import ru.job4j.servlets.domain.Account;
import ru.job4j.servlets.domain.MovieSession;

public interface Seller {
    MovieSession getMovieSession();
    void saveAccount(Account account);
    void saveMovieSession(MovieSession movieSession);
}
