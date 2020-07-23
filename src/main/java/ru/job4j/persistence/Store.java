package ru.job4j.persistence;

import ru.job4j.domain.Account;
import ru.job4j.domain.MovieSession;

import java.util.Collection;

public interface Store {

    void saveAccount(Account account);
    void saveMovieSession(MovieSession movieSession);

    void deleteAccount(int id);
    void deleteMovieSession(int id);

    Account findAccountById(int id);
    MovieSession findMovieSession(int id);

    Collection<Account> findAllAccounts();
    Collection<MovieSession> findAllMovieSessions();

}
