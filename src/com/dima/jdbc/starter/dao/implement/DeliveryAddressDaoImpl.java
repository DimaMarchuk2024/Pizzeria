package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.DeliveryAddressDao;
import com.dima.jdbc.starter.entity.*;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {

    private static final String DELIVERY_ADDRESS_ID = "id";
    private static final String USER_PIZZERIA_ID = "user_pizzeria_id";
    private static final String DELIVERY_ADDRESS_NAME = "delivery_address_name";
    private static DeliveryAddressDaoImpl instance;

    public static synchronized DeliveryAddressDaoImpl getInstance() {
        if (instance == null) {
            instance = new DeliveryAddressDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM delivery_address
             WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO delivery_address (user_pizzeria_id, delivery_address_name)
            VALUES (?, ?);
            """;
    private static final String UPDATE_SQL = """
            UPDATE delivery_address
            SET
            user_pizzeria_id = ?,
            delivery_address_name = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, user_pizzeria_id, delivery_address_name
            FROM delivery_address
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private DeliveryAddressDaoImpl() {
    }

    public List<DeliveryAddressEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DeliveryAddressEntity> deliveryAddressEntities = new ArrayList<>();
            while (resultSet.next()) {
                deliveryAddressEntities.add(buildDeliveryAddress(resultSet));
            }
            return deliveryAddressEntities;
        } catch (SQLException e) {
            throw new DaoException("Can not find all delivery addresses", e);
        }
    }
    public Optional<DeliveryAddressEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            DeliveryAddressEntity deliveryAddressEntity = null;
            if (resultSet.next()) {
                deliveryAddressEntity = buildDeliveryAddress(resultSet);
            }
            return Optional.ofNullable(deliveryAddressEntity);
        } catch (SQLException e) {
            throw new DaoException("Can not find delivery address by id", e);
        }
    }

    private DeliveryAddressEntity buildDeliveryAddress(ResultSet resultSet) throws SQLException {
        UserPizzeriaEntity userPizzeria = UserPizzeriaEntity
                .builder()
                .id(resultSet.getLong(USER_PIZZERIA_ID))
                .build();

        return DeliveryAddressEntity
                .builder()
                .id(resultSet.getLong(DELIVERY_ADDRESS_ID))
                .userPizzeriaEntity(userPizzeria)
                .deliveryAddressName(resultSet.getString(DELIVERY_ADDRESS_NAME))
                .build();
    }

    public void update(DeliveryAddressEntity deliveryAddressEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, deliveryAddressEntity.getUserPizzeriaEntity().getId());
            preparedStatement.setString(2, deliveryAddressEntity.getDeliveryAddressName());
            preparedStatement.setLong(3, deliveryAddressEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update delivery address", e);
        }
    }

    public DeliveryAddressEntity save(DeliveryAddressEntity deliveryAddress) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, deliveryAddress.getUserPizzeriaEntity().getId());
            preparedStatement.setString(2, deliveryAddress.getDeliveryAddressName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                deliveryAddress.setId(generatedKeys.getLong(DELIVERY_ADDRESS_ID));
            }
            return deliveryAddress;
        } catch (SQLException e) {
            throw new DaoException("Can not save delivery address", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete delivery address", e);
        }
    }
}
