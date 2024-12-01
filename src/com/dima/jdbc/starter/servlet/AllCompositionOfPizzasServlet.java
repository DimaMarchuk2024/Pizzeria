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
import java.util.List;
@WebServlet("/compositionOfPizzas")
public class AllCompositionOfPizzasServlet extends HttpServlet {

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompositionOfPizzaDto> compositionOfPizzaDtos = compositionOfPizzaService.findAll();
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
//            for (CompositionOfPizzaDto compositionOfPizzaDto : compositionOfPizzaDtos) {
//                writer.write(compositionOfPizzaDto + ", " );
//            }
            writer.write(compositionOfPizzaDtos.toString());
        }
    }
}
