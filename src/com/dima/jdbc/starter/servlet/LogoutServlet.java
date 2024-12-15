package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.dima.jdbc.starter.util.UrlPath.LOGIN;
import static com.dima.jdbc.starter.util.UrlPath.LOGOUT;

@WebServlet(LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(LOGIN);
    }
}
