package com.dima.jdbc.starter;

import com.dima.jdbc.starter.dao.implement.PizzaDaoImpl;
import com.dima.jdbc.starter.dao.implement.PizzaSizeDaoImpl;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.entity.PizzaSizeEntity;

public class DaoRunner {

    public static void main(String[] args) {
        PizzaSizeDaoImpl pizzaSizeDao = PizzaSizeDaoImpl.getInstance();
        PizzaSizeEntity pizzaSizeEntity = new PizzaSizeEntity();
        pizzaSizeEntity.setSizeName("Средняя");
        pizzaSizeDao.save(pizzaSizeEntity);
    }

    private static void deleteTest() {
        PizzaDaoImpl pizzaDao = PizzaDaoImpl.getInstance();
        boolean deleteResult = pizzaDao.delete(1L);
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
