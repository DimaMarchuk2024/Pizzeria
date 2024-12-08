package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.service.CompositionOfPizzaService;
import com.dima.jdbc.starter.service.impl.CompositionOfPizzaServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/saveCompositionOfPizza")
public class SaveCompositionOfPizzaServlet extends HttpServlet {

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompositionOfPizzaServiceImpl.pizzaId = 5L;
        CompositionOfPizzaServiceImpl.ingredientId = 7L;

        CompositionOfPizzaDto compositionOfPizzaDto = compositionOfPizzaService.save(new CompositionOfPizzaDto());
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(compositionOfPizzaDto.toString());
        }
    }
}
