package com.dima.jdbc.starter;

import com.dima.jdbc.starter.dao.implement.CompositionOfPizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.IngredientDaoImpl;
import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.PizzaSizeDaoImpl;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.entity.PizzaSizeEntity;

import java.util.List;

public class DaoRunner {

    public static void main(String[] args) {
        compositionOfPizzaAll();
    }

    private static void compositionOfPizzaAll() {
        CompositionOfPizzaDaoImpl compositionOfPizzaDao = CompositionOfPizzaDaoImpl.getInstance();
        List<CompositionOfPizzaEntity> compositionOfPizzaDaoAll = compositionOfPizzaDao.findAll();
        for (CompositionOfPizzaEntity compositionOfPizza : compositionOfPizzaDaoAll) {
            System.out.println(compositionOfPizza);
            System.out.println();
        }
    }

    private static void saveSize() {
        PizzaSizeDaoImpl pizzaSizeDao = PizzaSizeDaoImpl.getInstance();
        PizzaSizeEntity pizzaSizeEntity = new PizzaSizeEntity();
        pizzaSizeEntity.setSize("Средняя");
        pizzaSizeDao.save(pizzaSizeEntity);
    }

    private static void deleteTest() {
        PizzaDaoImpl pizzaDao = PizzaDaoImpl.getInstance();
        boolean deleteResult = pizzaDao.delete(10L);
        System.out.println(deleteResult);
    }

    private static void saveTest() {
        PizzaDaoImpl pizzaDao = PizzaDaoImpl.getInstance();
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setPizzaName("Маргарита");

        PizzaEntity savedPizza = pizzaDao.save(pizzaEntity);
        System.out.println(savedPizza);
    }
}
