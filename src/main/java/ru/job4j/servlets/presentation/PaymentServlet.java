package ru.job4j.servlets.presentation;

import ru.job4j.servlets.domain.Account;
import ru.job4j.servlets.persistence.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("payment/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().saveAccount(new Account(req.getParameter("userName"),
                req.getParameter("phoneNumber"), Double.parseDouble(req.getParameter("sum"))));
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
