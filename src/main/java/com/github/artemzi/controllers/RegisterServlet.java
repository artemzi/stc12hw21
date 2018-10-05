package com.github.artemzi.controllers;

import com.github.artemzi.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("role"));

        boolean success = userService.addFromStringsParam(name, email, pass, roleId);
        if (success) {
            resp.sendRedirect("/user/login");
        } else {
            req.getSession().setAttribute("errorMessage", "Cannot create record, already registered?");
            resp.sendRedirect("/register.jsp");
        }
    }
}
