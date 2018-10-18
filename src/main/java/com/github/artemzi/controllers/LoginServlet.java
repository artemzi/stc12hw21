package com.github.artemzi.controllers;

import com.github.artemzi.pojo.User;
import com.github.artemzi.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if ("logout".equals(req.getParameter("action"))) {
            req.getSession().invalidate();
            LOGGER.info("Successfully log out");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (IOException | ServletException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (UserService.getInstance().checkAuth(email, password)) {
            User currentUser = UserService.getInstance().getUserByEmail(email);
            LOGGER.info("Successfully log in user: " + currentUser);
            req.getSession().setAttribute("authenticated", "success");
            req.getSession().setAttribute("user", currentUser);
        } else {
            LOGGER.error("Authentication fail for user with email: " + email);
            req.getSession().setAttribute("authenticated", "fail");
        }

        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
