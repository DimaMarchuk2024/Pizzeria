package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.CompositionOfPizza;
import com.dima.jdbc.starter.entity.CompositionOfPizzaEntity;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionOfPizzaDaoImpl implements CompositionOfPizza {

    private static final String COMPOSITION_OF_PIZZA_ID = "id";
    private static final String PIZZA_ID = "pizza_id";
    private static final String INGREDIENT_ID = "ingredient_id";
    private static CompositionOfPizzaDaoImpl instance;

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
            id, pizza_id, ingredient_id
            FROM composition_of_pizza
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private CompositionOfPizzaDaoImpl() {
    }
    public List<CompositionOfPizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CompositionOfPizzaEntity> compositionOfPizzaEntities = new ArrayList<>();
            while (resultSet.next()) {
                compositionOfPizzaEntities.add(buildCompositionOfPizza(resultSet));
            }
            return compositionOfPizzaEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public Optional<CompositionOfPizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            CompositionOfPizzaEntity compositionOfPizza = null;
            if (resultSet.next()) {
                compositionOfPizza = buildCompositionOfPizza(resultSet);
            }
            return Optional.ofNullable(compositionOfPizza);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private CompositionOfPizzaEntity buildCompositionOfPizza(ResultSet resultSet) throws SQLException {
        PizzaEntity pizza = PizzaEntity
                .builder()
                .id(resultSet.getLong(PIZZA_ID))
                .build();

        IngredientEntity ingredient = IngredientEntity
                .builder()
                .id(resultSet.getLong(INGREDIENT_ID))
                .build();

        return CompositionOfPizzaEntity
                .builder()
                .id(resultSet.getLong(COMPOSITION_OF_PIZZA_ID))
                .pizzaEntity(pizza)
                .ingredientEntity(ingredient)
                .build();
    }

    public void update(CompositionOfPizzaEntity compositionOfPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1,compositionOfPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, compositionOfPizzaEntity.getIngredientEntity().getId());
            preparedStatement.setLong(3, compositionOfPizzaEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public CompositionOfPizzaEntity save(CompositionOfPizzaEntity compositionOfPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, compositionOfPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, compositionOfPizzaEntity.getIngredientEntity().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                compositionOfPizzaEntity.setId(generatedKeys.getLong(COMPOSITION_OF_PIZZA_ID));
            }
            return compositionOfPizzaEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
