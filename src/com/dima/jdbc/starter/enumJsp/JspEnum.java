package com.dima.jdbc.starter.enumJsp;

import com.dima.jdbc.starter.util.JspHelper;
import lombok.Getter;

@Getter
public enum JspEnum {

    PIZZAS("pizzas"),
    COMPOSITION_OF_ALL_PIZZAS("compositionOfAllPizzas"),
    COMPOSITION_OF_PIZZA("compositionOfPizza"),
    INGREDIENTS("ingredients"),
    LOGIN("login"),
    REGISTRATION("registration");

    final String jsp;

    JspEnum(String jsp) {
        this.jsp = jsp;
    }

}
