package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.interfaceDao.UserPizzeriaDao;
import com.dima.jdbc.starter.entity.*;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserPizzeriaDaoImpl implements UserPizzeriaDao {

    private static final String USER_PIZZERIA_ID = "id";
    private static final String USER_PIZZERIA_FIRST_NAME = "first_name";
    private static final String USER_PIZZERIA_LAST_NAME = "last_name";
    private static final String USER_PIZZERIA_PHONE_NUMBER = "phone_number";
    private static final String USER_PIZZERIA_EMAIL = "email";
    private static final String ROLE_ID = "role_id";
    private static final String USER_PIZZERIA_BIRTH_DATE = "birth_date";


    private static UserPizzeriaDaoImpl instance;

    public static synchronized UserPizzeriaDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserPizzeriaDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM user_pizzeria
             WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO user_pizzeria (first_name, last_name, phone_number, email, role_id, birth_date)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE user_pizzeria
            SET
            first_name = ?,
            last_name = ?,
            phone_number = ?,
            email = ?,
            role_id = ?,
            birth_date = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, first_name, last_name, phone_number, email, role_id, birth_date
            FROM user_pizzeria
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private UserPizzeriaDaoImpl() {
    }

    public List<UserPizzeriaEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserPizzeriaEntity> userPizzeriaEntities = new ArrayList<>();
            while (resultSet.next()) {
                userPizzeriaEntities.add(buildUserPizzeria(resultSet));
            }
            return userPizzeriaEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public Optional<UserPizzeriaEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserPizzeriaEntity userPizzeriaEntity = null;
            if (resultSet.next()) {
                userPizzeriaEntity = buildUserPizzeria(resultSet);
            }
            return Optional.ofNullable(userPizzeriaEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private UserPizzeriaEntity buildUserPizzeria(ResultSet resultSet) throws SQLException {
        RoleEntity role = RoleEntity
                .builder()
                .id(resultSet.getLong(ROLE_ID))
                .build();

        return UserPizzeriaEntity
                .builder()
                .id(resultSet.getLong(USER_PIZZERIA_ID))
                .firstName(resultSet.getString(USER_PIZZERIA_FIRST_NAME))
                .lastName(resultSet.getString(USER_PIZZERIA_LAST_NAME))
                .phoneNumber(resultSet.getString(USER_PIZZERIA_PHONE_NUMBER))
                .email(resultSet.getString(USER_PIZZERIA_EMAIL))
                .roleEntity(role)
                .birthDate(resultSet.getDate(USER_PIZZERIA_BIRTH_DATE).toLocalDate())
                .build();
    }

    public void update(UserPizzeriaEntity userPizzeriaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, userPizzeriaEntity.getFirstName());
            preparedStatement.setString(2, userPizzeriaEntity.getLastName());
            preparedStatement.setString(3, userPizzeriaEntity.getPhoneNumber());
            preparedStatement.setString(4, userPizzeriaEntity.getEmail());
            preparedStatement.setLong(5, userPizzeriaEntity.getRoleEntity().getId());
            preparedStatement.setDate(6, Date.valueOf(userPizzeriaEntity.getBirthDate()));
            preparedStatement.setLong(7, userPizzeriaEntity.getId());

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

    public UserPizzeriaEntity save(UserPizzeriaEntity userPizzeriaEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userPizzeriaEntity.getFirstName());
            preparedStatement.setString(2, userPizzeriaEntity.getLastName());
            preparedStatement.setString(3, userPizzeriaEntity.getPhoneNumber());
            preparedStatement.setString(4, userPizzeriaEntity.getEmail());
            preparedStatement.setLong(5, userPizzeriaEntity.getRoleEntity().getId());
            preparedStatement.setDate(6, Date.valueOf(userPizzeriaEntity.getBirthDate()));

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userPizzeriaEntity.setId(generatedKeys.getLong(USER_PIZZERIA_ID));
            }
            return userPizzeriaEntity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
