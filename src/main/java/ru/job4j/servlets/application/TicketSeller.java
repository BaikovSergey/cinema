package ru.job4j.servlets.application;

import java.util.logging.Logger;

public class TicketSeller {

    private final static Logger LOGGER = Logger.getLogger(TicketSeller.class.getName());

    private static final class Lazy {
        private static final TicketSeller INST = new TicketSeller();
    }

    public static TicketSeller instOf() {
        return Lazy.INST;
    }
}
