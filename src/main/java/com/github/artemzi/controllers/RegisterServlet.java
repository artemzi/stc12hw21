package com.github.artemzi.controllers;

import com.github.artemzi.pojo.User;
import com.github.artemzi.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

//        String name = req.getParameter("name");
//        String email = req.getParameter("email");
//        String pass = req.getParameter("password");

        List<User> user = userService.getAll();

        out.println(user);
    }
}
