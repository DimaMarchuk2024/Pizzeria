package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.enumJsp.JspEnum;
import com.dima.jdbc.starter.service.UserPizzeriaService;
import com.dima.jdbc.starter.service.impl.UserPizzeriaServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.dima.jdbc.starter.util.UrlPath.LOGIN;
import static com.dima.jdbc.starter.util.UrlPath.PIZZAS;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserPizzeriaService userPizzeriaService = UserPizzeriaServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(JspEnum.LOGIN.getJsp()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userPizzeriaService.login(req.getParameter("phoneNumber"),req.getParameter("password"))
                .ifPresentOrElse(
                        userPizzeriaDto -> onLoginSuccess(userPizzeriaDto, req, resp),
                        () -> onLoginFail(req, resp));
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(LOGIN + "?error&phoneNumber=" + req.getParameter("phoneNumber"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onLoginSuccess(UserPizzeriaDto userPizzeriaDto,HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("userPizzeriaDto", userPizzeriaDto);
        try {
            resp.sendRedirect(PIZZAS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
