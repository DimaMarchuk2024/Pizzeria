package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.PizzaSizeDao;
import com.dima.jdbc.starter.entity.PizzaSizeEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaSizeDaoImpl implements PizzaSizeDao {

    private static final String PIZZA_SIZE_ID = "id";
    private static final String PIZZA_SIZE_NAME = "size_name";

    private static PizzaSizeDaoImpl instance;

    public static synchronized PizzaSizeDaoImpl getInstance() {
        if (instance == null) {
            instance = new PizzaSizeDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM pizza_size
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO pizza_size (size_name)
            VALUES (?);
                 """;

    private static final String UPDATE_SQL = """
            UPDATE pizza_size
            SET size_name = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, size_name
            FROM pizza_size
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private PizzaSizeDaoImpl() {
    }

    public List<PizzaSizeEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PizzaSizeEntity> pizzaSizeEntities = new ArrayList<>();
            while (resultSet.next()) {
                pizzaSizeEntities.add(buildPizzaSize(resultSet));
            }
            return pizzaSizeEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<PizzaSizeEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PizzaSizeEntity pizzaSizeEntity = null;
            if (resultSet.next()) {
                pizzaSizeEntity = buildPizzaSize(resultSet);
            }
            return Optional.ofNullable(pizzaSizeEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PizzaSizeEntity buildPizzaSize(ResultSet resultSet) throws SQLException {
        return PizzaSizeEntity
                .builder()
                .id(resultSet.getLong(PIZZA_SIZE_ID))
                .sizeName(resultSet.getString(PIZZA_SIZE_NAME))
                .build();
    }

    public void update(PizzaSizeEntity pizzaSizeEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, pizzaSizeEntity.getSizeName());
            preparedStatement.setLong(2, pizzaSizeEntity.getId());

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

    public PizzaSizeEntity save(PizzaSizeEntity pizzaSizeEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pizzaSizeEntity.getSizeName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pizzaSizeEntity.setId(generatedKeys.getLong(PIZZA_SIZE_ID));
            }
            return pizzaSizeEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}