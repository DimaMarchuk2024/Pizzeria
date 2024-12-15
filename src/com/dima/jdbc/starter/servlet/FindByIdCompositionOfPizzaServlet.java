package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.CompositionOfPizzaDto;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.enumJsp.JspEnum;
import com.dima.jdbc.starter.service.CompositionOfPizzaService;
import com.dima.jdbc.starter.service.PizzaService;
import com.dima.jdbc.starter.service.PizzaSizeService;
import com.dima.jdbc.starter.service.TypeOfPizzaDoughService;
import com.dima.jdbc.starter.service.impl.CompositionOfPizzaServiceImpl;
import com.dima.jdbc.starter.service.impl.PizzaServiceImpl;
import com.dima.jdbc.starter.service.impl.PizzaSizeServiceImpl;
import com.dima.jdbc.starter.service.impl.TypeOfPizzaDoughServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.dima.jdbc.starter.util.UrlPath.COMPOSITION_OF_PIZZA;

@WebServlet(COMPOSITION_OF_PIZZA)
public class FindByIdCompositionOfPizzaServlet extends HttpServlet {
    private final PizzaService pizzaService = PizzaServiceImpl.getInstance();

    private final CompositionOfPizzaService compositionOfPizzaService = CompositionOfPizzaServiceImpl.getInstance();
    private final PizzaSizeService pizzaSizeService = PizzaSizeServiceImpl.getInstance();

    private final TypeOfPizzaDoughService typeOfPizzaDoughService = TypeOfPizzaDoughServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long pizzaId = Long.valueOf(req.getParameter("pizzaId"));
        req.setAttribute("pizzaById", pizzaService.findById(pizzaId));

        CompositionOfPizzaDto compositionOfPizzaDto = compositionOfPizzaService.findById(pizzaId);
        List<IngredientEntity> listIngredient = compositionOfPizzaDto.getListIngredient();
        req.setAttribute("compositionOfPizza", listIngredient);

        req.setAttribute("sizes", pizzaSizeService.findAll());
        req.setAttribute("typesOfPizzaDough", typeOfPizzaDoughService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(JspEnum.COMPOSITION_OF_PIZZA.getJsp()))
                .forward(req, resp);



    }
}
