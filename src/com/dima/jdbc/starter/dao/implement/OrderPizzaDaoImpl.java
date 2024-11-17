package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.OrderPizzaDao;
import com.dima.jdbc.starter.entity.*;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderPizzaDaoImpl implements OrderPizzaDao {

    private static final String ORDER_PIZZA_ID = "id";
    private static final String PIZZA_ID = "pizza_id";
    private static final String PIZZA_SIZE_ID = "pizza_size_id";
    private static final String TYPE_OF_PIZZA_DOUGH_ID = "type_of_pizza_dough_id";
    private static final String ORDER_PIZZA_NUMBER_OF_PIZZA = "number_of_pizza";
    private static final String ORDER_PIZZA_SUM_COST_ADDED_INGREDIENT = "sum_cost_added_ingredient";
    private static final String ORDER_PIZZA_SUM_COST_REMOVED_INGREDIENT = "sum_cost_removed_ingredient";
    private static final String ORDER_PIZZA_COST_OF_PIZZA = "cost_of_pizza";
    private static final String USER_ORDER_PIZZA_ID = "user_order_pizza_id";

    private static OrderPizzaDaoImpl instance;

    public static synchronized OrderPizzaDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderPizzaDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM order_pizza
             WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO order_pizza
            (pizza_id,
             pizza_size_id,
             type_of_pizza_dough_id,
             number_of_pizza,
             sum_cost_added_ingredient,
             sum_cost_removed_ingredient,
             cost_of_pizza,
             user_order_pizza_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE order_pizza
            SET
            pizza_id = ?,
            pizza_size_id = ?,
            type_of_pizza_dough_id = ?,
            number_of_pizza = ?,
            sum_cost_added_ingredient = ?,
            sum_cost_removed_ingredient = ?,
            cost_of_pizza = ?,
            user_order_pizza_id = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id,
            pizza_id,
            pizza_size_id,
            type_of_pizza_dough_id,
            number_of_pizza,
            sum_cost_added_ingredient,
            sum_cost_removed_ingredient,
            cost_of_pizza,
            user_order_pizza_id
            FROM order_pizza
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private OrderPizzaDaoImpl() {
    }

    public List<OrderPizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderPizzaEntity> orderPizzaEntities = new ArrayList<>();
            while (resultSet.next()) {
                orderPizzaEntities.add(buildOrderPizza(resultSet));
            }
            return orderPizzaEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public Optional<OrderPizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderPizzaEntity orderPizzaEntity = null;
            if (resultSet.next()) {
                orderPizzaEntity = buildOrderPizza(resultSet);
            }
            return Optional.ofNullable(orderPizzaEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private OrderPizzaEntity buildOrderPizza(ResultSet resultSet) throws SQLException {
        PizzaEntity pizza = PizzaEntity
                .builder()
                .id(resultSet.getLong(PIZZA_ID))
                .build();

        PizzaSizeEntity pizzaSize = PizzaSizeEntity
                .builder()
                .id(resultSet.getLong(PIZZA_SIZE_ID))
                .build();

        TypeOfPizzaDoughEntity typeOfPizzaDough = TypeOfPizzaDoughEntity
                .builder()
                .id(resultSet.getLong(TYPE_OF_PIZZA_DOUGH_ID))
                .build();

        UserOrderPizzaEntity userOrderPizza = UserOrderPizzaEntity
                .builder()
                .id(resultSet.getLong(USER_ORDER_PIZZA_ID))
                .build();

        return OrderPizzaEntity
                .builder()
                .id(resultSet.getLong(ORDER_PIZZA_ID))
                .pizzaEntity(pizza)
                .pizzaSizeEntity(pizzaSize)
                .typeOfPizzaDoughEntity(typeOfPizzaDough)
                .numberOfPizza(resultSet.getInt(ORDER_PIZZA_NUMBER_OF_PIZZA))
                .sumCostAddedIngredient(resultSet.getBigDecimal(ORDER_PIZZA_SUM_COST_ADDED_INGREDIENT))
                .sumCostRemovedIngredient(resultSet.getBigDecimal(ORDER_PIZZA_SUM_COST_REMOVED_INGREDIENT))
                .costOfPizza(resultSet.getBigDecimal(ORDER_PIZZA_COST_OF_PIZZA))
                .userOrderPizzaEntity(userOrderPizza)
                .build();
    }

    public void update(OrderPizzaEntity orderPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, orderPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, orderPizzaEntity.getPizzaSizeEntity().getId());
            preparedStatement.setLong(3, orderPizzaEntity.getTypeOfPizzaDoughEntity().getId());
            preparedStatement.setInt(4, orderPizzaEntity.getNumberOfPizza());
            preparedStatement.setBigDecimal(5, orderPizzaEntity.getSumCostAddedIngredient());
            preparedStatement.setBigDecimal(6, orderPizzaEntity.getSumCostRemovedIngredient());
            preparedStatement.setBigDecimal(7, orderPizzaEntity.getCostOfPizza());
            preparedStatement.setLong(8, orderPizzaEntity.getUserOrderPizzaEntity().getId());

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

    public OrderPizzaEntity save(OrderPizzaEntity orderPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, orderPizzaEntity.getPizzaEntity().getId());
            preparedStatement.setLong(2, orderPizzaEntity.getPizzaSizeEntity().getId());
            preparedStatement.setLong(3, orderPizzaEntity.getTypeOfPizzaDoughEntity().getId());
            preparedStatement.setInt(4, orderPizzaEntity.getNumberOfPizza());
            preparedStatement.setBigDecimal(5, orderPizzaEntity.getSumCostAddedIngredient());
            preparedStatement.setBigDecimal(6, orderPizzaEntity.getSumCostRemovedIngredient());
            preparedStatement.setBigDecimal(7, orderPizzaEntity.getCostOfPizza());
            preparedStatement.setLong(8, orderPizzaEntity.getUserOrderPizzaEntity().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderPizzaEntity.setId(generatedKeys.getLong(ORDER_PIZZA_ID));
            }
            return orderPizzaEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
