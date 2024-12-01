package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.PizzaDto;
import com.dima.jdbc.starter.service.PizzaService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/pizzas")
public class AllPizzasServlet extends HttpServlet {

    private final PizzaService pizzaService = PizzaService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramValue = req.getParameter("param");
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println();

        List<PizzaDto> pizzaDtos = pizzaService.findAll();
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "12356");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("Пиццы в пиццерии:");
            for (PizzaDto pizzaDto : pizzaDtos) {
                writer.write(pizzaDto + ", ");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader();
             Stream<String> lines = reader.lines()) {
            lines.forEach(System.out::println);

        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
