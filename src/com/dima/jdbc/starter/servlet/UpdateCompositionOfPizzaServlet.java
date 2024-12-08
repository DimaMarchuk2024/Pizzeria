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

@WebServlet("/updateCompositionOfPizza")
public class UpdateCompositionOfPizzaServlet extends HttpServlet {

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompositionOfPizzaServiceImpl.pizzaId = 8L;
        CompositionOfPizzaServiceImpl.ingredientId = 6L;
        CompositionOfPizzaServiceImpl.id = 49L;

        compositionOfPizzaService.update(new CompositionOfPizzaDto());

    }
}
