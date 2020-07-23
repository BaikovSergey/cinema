package ru.job4j.application;

import ru.job4j.domain.Account;
import ru.job4j.domain.MovieSession;

public interface Seller {
    MovieSession getMovieSession();
    void saveAccount(Account account);
    void saveMovieSession(MovieSession movieSession);
}
