package com.dima.jdbc.starter.servlet;

import com.dima.jdbc.starter.dto.IngredientDto;
import com.dima.jdbc.starter.enumJsp.JspEnum;
import com.dima.jdbc.starter.service.IngredientService;
import com.dima.jdbc.starter.service.impl.IngredientServiceImpl;
import com.dima.jdbc.starter.util.JspHelper;
import com.dima.jdbc.starter.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

import static com.dima.jdbc.starter.util.UrlPath.INGREDIENTS;

@WebServlet(INGREDIENTS)
public class IngredientsServlet extends HttpServlet {

    IngredientService ingredientService = IngredientServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ingredients", ingredientService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(JspEnum.INGREDIENTS.getJsp()))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IngredientDto ingredientDto = IngredientDto
                .builder()
                .ingredientName(req.getParameter("ingredientName"))
                .costOfIngredient(getCostOfIngredientStringToBigDecimal(req))
                .build();

        ingredientService.save(ingredientDto);
        resp.sendRedirect(INGREDIENTS);
    }

    private BigDecimal getCostOfIngredientStringToBigDecimal(HttpServletRequest req) {
        return BigDecimal.valueOf(Double.parseDouble(req.getParameter("costOfIngredient")));
    }
}
