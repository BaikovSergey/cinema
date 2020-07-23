package ru.job4j.application;

import ru.job4j.domain.Account;
import ru.job4j.domain.MovieSession;
import ru.job4j.persistence.PsqlStore;


public class TicketSeller implements Seller {

    private static final class Lazy {
        private static final Seller INST = new TicketSeller();
    }

    public static Seller instOf() {
        return Lazy.INST;
    }

    @Override
    public MovieSession getMovieSession() {
        return PsqlStore.instOf().findMovieSession(1);
    }

    @Override
    public void saveAccount(Account account) {
        PsqlStore.instOf().saveAccount(account);
    }

    @Override
    public void saveMovieSession(MovieSession movieSession) {
        PsqlStore.instOf().saveMovieSession(movieSession);
    }
}
