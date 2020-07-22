package ru.job4j.servlets.presentation;

import ru.job4j.servlets.application.TicketSeller;
import ru.job4j.servlets.domain.Account;
import ru.job4j.servlets.domain.MovieSession;
import ru.job4j.servlets.domain.Seat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("payment/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        MovieSession session = TicketSeller.instOf().getMovieSession();
        List<Seat> seats = session.getSeats();
        String[] selectedSeats = parseSeats(req.getParameter("seat").split(","));
        session.setSeats(occupySeats(seats, selectedSeats));
        TicketSeller.instOf().saveMovieSession(session);
        TicketSeller.instOf().saveAccount(new Account(req.getParameter(
                "userName"),
                req.getParameter("phoneNumber"), Double.parseDouble(req.getParameter("sum"))));
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }

    private String[] parseSeats(String[] seats) {
        String[] result = new String[seats.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = seats[i].substring(5);
        }
        return result;
    }

    private List<Seat> occupySeats(List<Seat> seats, String[] selectedSeats) {
        List<Seat> result = seats;
        for (int i = 0; i < selectedSeats.length; i++) {
            int row = Integer.parseInt(selectedSeats[i].substring(0, 1));
            int seat = Integer.parseInt(selectedSeats[i].substring(1));
            for (Seat place: result) {
                if (place.getRow() == row && place.getSeat() == seat) {
                    place.setOccupied(true);
                    break;
                }
            }
        }
        return result;
    }
}
