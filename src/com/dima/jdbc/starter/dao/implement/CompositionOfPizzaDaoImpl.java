package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.CompositionOfPizzaDao;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionOfPizzaDaoImpl implements CompositionOfPizzaDao {

    private static final String COMPOSITION_OF_PIZZA_ID = "id";
    private static final String PIZZA_ID = "pizza_id";
    private static final String PIZZA_NAME = "pizza_name";
    private static final String INGREDIENT_ID = "ingredient_id";
    private static final String INGREDIENT_NAME = "ingredient_name";
    private static final String COST_OF_INGREDIENT = "cost_of_ingredient";
    private static CompositionOfPizzaDaoImpl instance;

    List<IngredientEntity> listIngredient;

    public static synchronized CompositionOfPizzaDaoImpl getInstance() {
        if (instance == null) {
            instance = new CompositionOfPizzaDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM composition_of_pizza
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO composition_of_pizza (pizza_id, ingredient_id)
            VALUES (?, ?);
                 """;
    private static final String UPDATE_SQL = """
            UPDATE composition_of_pizza
            SET
            pizza_id = ?,
            ingredient_id = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
                   p.pizza_name,
                   i.ingredient_name,
                   i.cost_of_ingredient
            FROM composition_of_pizza c
                    join ingredient i on i.id = ingredient_id
                    join pizza p on p.id = c.pizza_id
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE p.id = ?
            """;

    private CompositionOfPizzaDaoImpl() {
    }

    public List<CompositionOfPizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CompositionOfPizzaEntity> compositionOfPizzaEntities = new ArrayList<>();

            while (resultSet.next()) {
                listIngredient = new ArrayList<>();
                compositionOfPizzaEntities.add(buildCompositionOfPizza(resultSet));
            }
            return compositionOfPizzaEntities;
        } catch (SQLException e) {
            throw new DaoException("Can not find all compositions of pizzas", e);
        }
    }

    public Optional<CompositionOfPizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            CompositionOfPizzaEntity compositionOfPizza = null;
            listIngredient = new ArrayList<>();
            while (resultSet.next()) {
                compositionOfPizza = buildCompositionOfPizza(resultSet);
            }
            return Optional.ofNullable(compositionOfPizza);
        } catch (SQLException e) {
            throw new DaoException("Can not find composition of pizza by id", e);
        }
    }

    private CompositionOfPizzaEntity buildCompositionOfPizza(ResultSet resultSet) throws SQLException {
        PizzaEntity pizza = PizzaEntity
                .builder()
                .pizzaName(resultSet.getString(PIZZA_NAME))
                .build();

        IngredientEntity ingredient = IngredientEntity
                .builder()
                .ingredientName(resultSet.getString(INGREDIENT_NAME))
                .costOfIngredient(resultSet.getBigDecimal(COST_OF_INGREDIENT))
                .build();

        listIngredient.add(ingredient);

        return CompositionOfPizzaEntity
                .builder()
                .pizzaEntity(pizza)
                .listIngredientEntity(listIngredient)
                .build();
    }

    public void update(CompositionOfPizzaEntity compositionOfPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, compositionOfPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, compositionOfPizzaEntity.getListIngredientEntity().stream().findAny().orElseThrow().getId());
            preparedStatement.setLong(3, compositionOfPizzaEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update composition of pizza", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete composition of pizza", e);
        }
    }

    public CompositionOfPizzaEntity save(CompositionOfPizzaEntity compositionOfPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, compositionOfPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, compositionOfPizzaEntity.getListIngredientEntity().stream().findAny().orElseThrow().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                compositionOfPizzaEntity.setId(generatedKeys.getLong(COMPOSITION_OF_PIZZA_ID));
            }
            return compositionOfPizzaEntity;
        } catch (SQLException e) {
            throw new DaoException("Can not save composition of pizza", e);
        }
    }

}
