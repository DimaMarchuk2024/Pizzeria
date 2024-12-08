package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.exception.ValidationException;
import com.dima.jdbc.starter.service.UserPizzeriaService;
import com.dima.jdbc.starter.service.impl.UserPizzeriaServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserPizzeriaService userPizzeriaService = UserPizzeriaServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("Admin", "User"));

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserPizzeriaDto userPizzeriaDto = UserPizzeriaDto
                .builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .phoneNumber(req.getParameter("phoneNumber"))
                .email(req.getParameter("email"))
                .birthDate(LocalDate.parse(req.getParameter("birthDate")))
                .password(req.getParameter("password"))
                .roleName(req.getParameter("role"))
                .build();
        try {
            userPizzeriaService.save(userPizzeriaDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }


    }
}
