package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.service.CompositionOfPizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteCompositionOfPizza")
public class DeleteCompositionOfPizzaServlet extends HttpServlet {

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean deleteCompositionOfPizza = compositionOfPizzaService.delete(47L);
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(String.valueOf(deleteCompositionOfPizza));
        }

    }
}
