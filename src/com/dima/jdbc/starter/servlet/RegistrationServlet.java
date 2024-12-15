package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.UserPizzeriaDto;
import com.dima.jdbc.starter.enumJsp.JspEnum;
import com.dima.jdbc.starter.exception.ValidationException;
import com.dima.jdbc.starter.service.RoleService;
import com.dima.jdbc.starter.service.UserPizzeriaService;
import com.dima.jdbc.starter.service.impl.RoleServiceImpl;
import com.dima.jdbc.starter.service.impl.UserPizzeriaServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.dima.jdbc.starter.util.UrlPath.LOGIN;
import static com.dima.jdbc.starter.util.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final UserPizzeriaService userPizzeriaService = UserPizzeriaServiceImpl.getInstance();

    private final RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(JspEnum.REGISTRATION.getJsp()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserPizzeriaDto userPizzeriaDto = UserPizzeriaDto
                .builder()
                .firstName(req.getParameter("firstName").isEmpty() ? null : req.getParameter("firstName"))
                .lastName(req.getParameter("lastName").isEmpty() ? null : req.getParameter("lastName"))
                .phoneNumber(req.getParameter("phoneNumber").isEmpty() ? null : req.getParameter("phoneNumber"))
                .email(req.getParameter("email").isEmpty() ? null : req.getParameter("email"))
                .birthDate(req.getParameter("birthDate"))
                .password(req.getParameter("password").isEmpty() ? null : req.getParameter("password"))
                .roleName(req.getParameter("role"))
                .build();
        try {
            userPizzeriaService.save(userPizzeriaDto);
            resp.sendRedirect(LOGIN);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }


    }
}
