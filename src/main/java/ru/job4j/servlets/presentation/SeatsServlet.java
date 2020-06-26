package ru.job4j.servlets.presentation;

import org.json.JSONArray;
import ru.job4j.servlets.application.TicketSeller;
import ru.job4j.servlets.domain.MovieSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SeatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/JSON");
        resp.setCharacterEncoding("UTF-8");
        MovieSession movieSession = TicketSeller.instOf().getMovieSession();
        JSONArray jsonArray = new JSONArray(movieSession.getSeats());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(jsonArray);
        writer.flush();
    }
}
