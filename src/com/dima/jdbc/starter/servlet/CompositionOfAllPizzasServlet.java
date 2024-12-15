package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.enumJsp.JspEnum;
import com.dima.jdbc.starter.service.CompositionOfPizzaService;
import com.dima.jdbc.starter.service.IngredientService;
import com.dima.jdbc.starter.service.impl.CompositionOfPizzaServiceImpl;
import com.dima.jdbc.starter.service.impl.IngredientServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.dima.jdbc.starter.util.UrlPath.COMPOSITION_OF_ALL_PIZZAS;

@WebServlet(COMPOSITION_OF_ALL_PIZZAS)
public class CompositionOfAllPizzasServlet extends HttpServlet {

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaServiceImpl.getInstance();
    IngredientService ingredientService = IngredientServiceImpl.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("compositionOfAllPizzas", compositionOfPizzaService.findAll());
        req.setAttribute("listIngredients", ingredientService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(JspEnum.COMPOSITION_OF_ALL_PIZZAS.getJsp()))
                .forward(req, resp);

    }
}

