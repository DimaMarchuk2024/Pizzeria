package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.OrderPizzaDao;
import com.dima.jdbc.starter.entity.*;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderPizzaDaoImpl implements OrderPizzaDao {

    private static final String ORDER_PIZZA_ID = "id";
    private static final String PIZZA_NAME = "pizza_name";
    private static final String PIZZA_SIZE = "size";
    private static final String TYPE_DOUGH = "type_dough";
    private static final String ORDER_PIZZA_NUMBER_OF_PIZZA = "number_of_pizza";
    private static final String ORDER_PIZZA_SUM_COST_ADDED_INGREDIENT = "sum_cost_added_ingredient";
    private static final String ORDER_PIZZA_SUM_COST_REMOVED_INGREDIENT = "sum_cost_removed_ingredient";
    private static final String ORDER_PIZZA_COST_OF_PIZZA = "cost_of_pizza";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    List<PizzaEntity> listPizza;
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
            op.id,
            p.pizza_name,
            ps.size,
            topd.type_dough,
            number_of_pizza,
            sum_cost_added_ingredient,
            sum_cost_removed_ingredient,
            cost_of_pizza,
            up.first_name,
            up.last_name
            FROM order_pizza op
            join pizza p on p.id = op.pizza_id
            join pizza_size ps on ps.id = op.pizza_size_id
            join type_of_pizza_dough topd on topd.id = op.type_of_pizza_dough_id
            join user_order_pizza uop on uop.id = op.user_order_pizza_id
            join user_pizzeria up on up.id = uop.user_pizzeria_id
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE op.id = ?
            """;

    private OrderPizzaDaoImpl() {
    }

    public List<OrderPizzaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderPizzaEntity> orderPizzaEntities = new ArrayList<>();

            while (resultSet.next()) {
                listPizza = new ArrayList<>();
                orderPizzaEntities.add(buildOrderPizza(resultSet));
            }
            return orderPizzaEntities;
        } catch (SQLException e) {
            throw new DaoException("Can not find all orders pizzas", e);
        }
    }
    public Optional<OrderPizzaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            OrderPizzaEntity orderPizzaEntity = null;
            listPizza = new ArrayList<>();
            while (resultSet.next()) {
                orderPizzaEntity = buildOrderPizza(resultSet);
            }
            return Optional.ofNullable(orderPizzaEntity);
        } catch (SQLException e) {
            throw new DaoException("Can not find order pizza by id", e);
        }
    }

    private OrderPizzaEntity buildOrderPizza(ResultSet resultSet) throws SQLException {
        PizzaEntity pizza = PizzaEntity
                .builder()
                .pizzaName(resultSet.getString(PIZZA_NAME))
                .build();

        listPizza.add(pizza);

        PizzaSizeEntity pizzaSize = PizzaSizeEntity
                .builder()
                .size(resultSet.getString(PIZZA_SIZE))
                .build();

        TypeOfPizzaDoughEntity typeOfPizzaDough = TypeOfPizzaDoughEntity
                .builder()
                .typeDough(resultSet.getString(TYPE_DOUGH))
                .build();

        UserPizzeriaEntity userPizzeria = UserPizzeriaEntity
                .builder()
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .build();

        UserOrderPizzaEntity userOrderPizza = UserOrderPizzaEntity
                .builder()
                .userPizzeriaEntity(userPizzeria)
                .build();

        return OrderPizzaEntity
                .builder()
                .id(resultSet.getLong(ORDER_PIZZA_ID))
                .listPizzaEntity(listPizza)
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
            preparedStatement.setLong(1, orderPizzaEntity.getListPizzaEntity().stream().findAny().orElseThrow().getId());
            preparedStatement.setLong(2, orderPizzaEntity.getPizzaSizeEntity().getId());
            preparedStatement.setLong(3, orderPizzaEntity.getTypeOfPizzaDoughEntity().getId());
            preparedStatement.setInt(4, orderPizzaEntity.getNumberOfPizza());
            preparedStatement.setBigDecimal(5, orderPizzaEntity.getSumCostAddedIngredient());
            preparedStatement.setBigDecimal(6, orderPizzaEntity.getSumCostRemovedIngredient());
            preparedStatement.setBigDecimal(7, orderPizzaEntity.getCostOfPizza());
            preparedStatement.setLong(8, orderPizzaEntity.getUserOrderPizzaEntity().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update order pizza", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete order pizza",e);
        }
    }

    public OrderPizzaEntity save(OrderPizzaEntity orderPizzaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, orderPizzaEntity.getListPizzaEntity().stream().findAny().orElseThrow().getId());
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
            throw new DaoException("Can not save order pizza", e);
        }
    }


}
