package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.service.PizzaService;
import com.dima.jdbc.starter.service.impl.PizzaServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pizzas")
public class AllPizzasServlet extends HttpServlet {

    private final PizzaService pizzaService = PizzaServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pizzas", pizzaService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("pizzas"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PizzaDto pizzaDto = PizzaDto
                .builder()
                .pizzaName(req.getParameter("pizzaName"))
                .build();

            pizzaService.save(pizzaDto);
            resp.sendRedirect("/pizzas");

    }

}
