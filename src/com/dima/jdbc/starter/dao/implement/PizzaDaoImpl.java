package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.PizzaDao;
import com.dima.jdbc.starter.entity.PizzaEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaDaoImpl implements PizzaDao {

    private static final String PIZZA_ID = "id";
    private static final String PIZZA_NAME = "pizza_name";

    private static PizzaDaoImpl instance;
    public static synchronized PizzaDaoImpl getInstance() {
        if (instance == null) {
            instance = new PizzaDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM pizza
            WHERE id = ?;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO pizza (pizza_name)
            VALUES(?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE pizza
            SET pizza_name = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id, pizza_name
            FROM pizza
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private PizzaDaoImpl() {
    }

    public List<PizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PizzaEntity> pizzas = new ArrayList<>();
            while (resultSet.next()) {
                pizzas.add(buildPizza(resultSet));
            }
            return pizzas;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<PizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PizzaEntity pizza = null;
            if (resultSet.next()) {
                pizza = buildPizza(resultSet);
            }
            return Optional.ofNullable(pizza);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PizzaEntity buildPizza(ResultSet resultSet) throws SQLException {
        return PizzaEntity
                .builder()
                .id(resultSet.getLong(PIZZA_ID))
                .pizzaName(resultSet.getString(PIZZA_NAME))
                .build();
    }

    public void update(PizzaEntity pizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, pizzaEntity.getPizzaName());
            preparedStatement.setLong(2, pizzaEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pizzaEntity.getPizzaName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pizzaEntity.setId(generatedKeys.getLong(PIZZA_ID));
            }
            return pizzaEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


}
