package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.IngredientDao;
import com.dima.jdbc.starter.entity.IngredientEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientDaoImpl implements IngredientDao {


    private static final String INGREDIENT_ID = "id";
    private static final String INGREDIENT_NAME = "ingredient_name";
    private static final String INGREDIENT_COST = "cost_of_ingredient";
    private static IngredientDaoImpl instance;

    public static synchronized IngredientDaoImpl getInstance() {
        if (instance == null) {
            instance = new IngredientDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM ingredient
             WHERE id = ?;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO ingredient (ingredient_name, cost_of_ingredient)
            VALUES (?, ?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE ingredient
            SET
            ingredient_name = ?,
            cost_of_ingredient = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, ingredient_name, cost_of_ingredient
            FROM ingredient
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private IngredientDaoImpl() {
    }

    public List<IngredientEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<IngredientEntity> ingredients = new ArrayList<>();
            while (resultSet.next()) {
                ingredients.add(buildIngredient(resultSet));
            }
            return ingredients;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<IngredientEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            IngredientEntity ingredient = null;
            if (resultSet.next()) {
                ingredient = buildIngredient(resultSet);
            }
            return Optional.ofNullable(ingredient);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private IngredientEntity buildIngredient(ResultSet resultSet) throws SQLException {
        return IngredientEntity
                .builder()
                .id(resultSet.getLong(INGREDIENT_ID))
                .ingredientName(resultSet.getString(INGREDIENT_NAME))
                .costOfIngredient(resultSet.getBigDecimal(INGREDIENT_COST))
                .build();

    }

    public void update(IngredientEntity ingredient) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, ingredient.getIngredientName());
            preparedStatement.setBigDecimal(2, ingredient.getCostOfIngredient());
            preparedStatement.setLong(3, ingredient.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public IngredientEntity save(IngredientEntity ingredient) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ingredient.getIngredientName());
            preparedStatement.setBigDecimal(2, ingredient.getCostOfIngredient());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ingredient.setId(generatedKeys.getLong(INGREDIENT_ID));
            }
            return ingredient;
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

}
