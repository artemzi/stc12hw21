package com.github.artemzi.controllers;

import com.github.artemzi.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        int roleId = 0;
        try {
            roleId = Integer.parseInt(req.getParameter("role"));
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        boolean success = userService.addUser(name, email, pass, roleId);
        if (success) {
            LOGGER.info("Successfully register new user");
            try {
                resp.sendRedirect("/user/login");
            } catch (IOException e) {
                LOGGER.error(e);
            }
        } else {
            LOGGER.info("Cannot register new user");
            req.getSession().setAttribute("errorMessage", "Cannot create record, already registered?");
            try {
                resp.sendRedirect("/register.jsp");
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }
}
