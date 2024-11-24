package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.TypeOfPizzaDoughDao;
import com.dima.jdbc.starter.entity.TypeOfPizzaDoughEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeOfPizzaDoughDaoImpl implements TypeOfPizzaDoughDao {

    private static final String TYPE_OF_PIZZA_DOUGH_ID = "id";
    private static final String TYPE_DOUGH = "type_dough";

    private static TypeOfPizzaDoughDaoImpl instance;

    public static synchronized TypeOfPizzaDoughDaoImpl getInstance() {
        if (instance == null) {
            instance = new TypeOfPizzaDoughDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM type_of_pizza_dough
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO type_of_pizza_dough (type_dough)
            VALUES (?);
                 """;

    private static final String UPDATE_SQL = """
            UPDATE type_of_pizza_dough
            SET type_dough = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, type_dough
            FROM type_of_pizza_dough
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private TypeOfPizzaDoughDaoImpl() {
    }

    public List<TypeOfPizzaDoughEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TypeOfPizzaDoughEntity> typeOfPizzaDoughEntities = new ArrayList<>();
            while (resultSet.next()) {
                typeOfPizzaDoughEntities.add(buildTypeOfPizzaDough(resultSet));
            }
            return typeOfPizzaDoughEntities;
        } catch (SQLException e) {
            throw new DaoException("Can not find all types of pizza dough", e);
        }
    }

    public Optional<TypeOfPizzaDoughEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TypeOfPizzaDoughEntity typeOfPizzaDoughEntity = null;
            if (resultSet.next()) {
                typeOfPizzaDoughEntity = buildTypeOfPizzaDough(resultSet);
            }
            return Optional.ofNullable(typeOfPizzaDoughEntity);
        } catch (SQLException e) {
            throw new DaoException("Can not find type of pizza dough by id",e);
        }
    }

    private TypeOfPizzaDoughEntity buildTypeOfPizzaDough(ResultSet resultSet) throws SQLException {
        return TypeOfPizzaDoughEntity
                .builder()
                .id(resultSet.getLong(TYPE_OF_PIZZA_DOUGH_ID))
                .typeDough(resultSet.getString(TYPE_DOUGH))
                .build();
    }

    public void update(TypeOfPizzaDoughEntity typeOfPizzaDoughEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, typeOfPizzaDoughEntity.getTypeDough());
            preparedStatement.setLong(2, typeOfPizzaDoughEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update type of pizza dough", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete type of pizza dough", e);
        }
    }

    public TypeOfPizzaDoughEntity save(TypeOfPizzaDoughEntity typeOfPizzaDoughEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, typeOfPizzaDoughEntity.getTypeDough());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                typeOfPizzaDoughEntity.setId(generatedKeys.getLong(TYPE_OF_PIZZA_DOUGH_ID));
            }
            return typeOfPizzaDoughEntity;
        } catch (SQLException e) {
            throw new DaoException("Can not save type of pizza dough", e);
        }
    }
}
