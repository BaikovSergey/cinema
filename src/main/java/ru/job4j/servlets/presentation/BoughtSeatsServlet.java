package ru.job4j.servlets.presentation;

import ru.job4j.servlets.application.TicketSeller;
import ru.job4j.servlets.domain.Seat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BoughtSeatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        StringBuilder result = new StringBuilder();
        int sum = 0;
        List<Seat> seats = TicketSeller.instOf().getMovieSession().getSeats();
        String[] data = req.getParameter("seats").split(",");
        for (String seatData : data) {
            int row = Integer.parseInt(seatData.substring(5,6));
            int place = Integer.parseInt(seatData.substring(6));
            for (Seat seat: seats) {
                if (seat.getRow() == row && seat.getSeat() == place) {
                    sum = sum + seat.getPrice();
                    result.append("ряд ")
                            .append(seat.getRow())
                            .append(" ")
                            .append("место ")
                            .append(seat.getSeat())
                            .append(",");
                }
            }
        }
        result.append("Сумма: ")
                .append(sum);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),
                StandardCharsets.UTF_8), true);
        writer.println(result.toString());
        writer.flush();
    }
}
