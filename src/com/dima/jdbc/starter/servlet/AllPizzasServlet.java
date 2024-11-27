package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.service.PizzaService;
import com.dima.jdbc.starter.service.impl.PizzaServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/pizzas")
public class AllPizzasServlet extends HttpServlet {

    private final PizzaService pizzaService = PizzaServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PizzaDto> pizzaDtos = pizzaService.findAll();
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            for (PizzaDto pizzaDto : pizzaDtos) {
                writer.write(pizzaDto + ", ");
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
