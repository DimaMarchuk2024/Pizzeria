package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.UserOrderPizzaDao;
import com.dima.jdbc.starter.entity.UserOrderPizzaEntity;
import com.dima.jdbc.starter.entity.UserPizzeriaEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserOrderPizzaDaoImpl implements UserOrderPizzaDao {

    private static final String USER_ORDER_PIZZA_ID = "id";
    private static final String USER_PIZZERIA_ID = "user_pizzeria_id";
    private static final String USER_ORDER_PIZZA_DATE_ORDER = "date_order";
    private static final String USER_ORDER_PIZZA_FINAL_COST_ORDER = "final_cost_order";

    private static UserOrderPizzaDaoImpl instance;

    public static synchronized UserOrderPizzaDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserOrderPizzaDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM user_order_pizza
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO user_order_pizza
            (user_pizzeria_id, date_order, final_cost_order)
            VALUES (?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE user_order_pizza
            SET
            user_pizzeria_id = ?,
            date_order = ?,
            final_cost_order = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, user_pizzeria_id, date_order, final_cost_order
            FROM user_order_pizza
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private UserOrderPizzaDaoImpl() {
    }

    public List<UserOrderPizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserOrderPizzaEntity> userOrderPizzaEntities = new ArrayList<>();
            while (resultSet.next()) {
                userOrderPizzaEntities.add(buildUserOrderPizza(resultSet));
            }
            return userOrderPizzaEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<UserOrderPizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserOrderPizzaEntity userOrderPizzaEntity = null;
            if (resultSet.next()) {
                userOrderPizzaEntity = buildUserOrderPizza(resultSet);
            }
            return Optional.ofNullable(userOrderPizzaEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private UserOrderPizzaEntity buildUserOrderPizza(ResultSet resultSet) throws SQLException {
        UserPizzeriaEntity userPizzeria = UserPizzeriaEntity
                .builder()
                .id(resultSet.getLong(USER_PIZZERIA_ID))
                .build();

        return UserOrderPizzaEntity
                .builder()
                .id(resultSet.getLong(USER_ORDER_PIZZA_ID))
                .userPizzeriaEntity(userPizzeria)
                .dateOrder(resultSet.getTimestamp(USER_ORDER_PIZZA_DATE_ORDER).toLocalDateTime())
                .finalCostOrder(resultSet.getBigDecimal(USER_ORDER_PIZZA_FINAL_COST_ORDER))
                .build();
    }

    public void update(UserOrderPizzaEntity userOrderPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, userOrderPizzaEntity.getUserPizzeriaEntity().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(userOrderPizzaEntity.getDateOrder()));
            preparedStatement.setBigDecimal(3, userOrderPizzaEntity.getFinalCostOrder());
            preparedStatement.setLong(4, userOrderPizzaEntity.getId());

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

    public UserOrderPizzaEntity save(UserOrderPizzaEntity userOrderPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, userOrderPizzaEntity.getUserPizzeriaEntity().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(userOrderPizzaEntity.getDateOrder()));
            preparedStatement.setBigDecimal(3, userOrderPizzaEntity.getFinalCostOrder());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userOrderPizzaEntity.setId(generatedKeys.getLong(USER_ORDER_PIZZA_ID));
            }
            return userOrderPizzaEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
