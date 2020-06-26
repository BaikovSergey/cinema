package ru.job4j.servlets.application;

import ru.job4j.servlets.domain.MovieSession;
import ru.job4j.servlets.persistence.PsqlStore;

import java.util.logging.Logger;

public class TicketSeller implements Seller {

    private final static Logger LOGGER = Logger.getLogger(TicketSeller.class.getName());

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
}
