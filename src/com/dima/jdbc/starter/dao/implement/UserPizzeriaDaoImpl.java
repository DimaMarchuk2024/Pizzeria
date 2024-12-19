package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.UserPizzeriaDao;
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
    private static final String ROLE = "role_name";
    private static final String USER_PIZZERIA_BIRTH_DATE = "birth_date";
    private static final String USER_PIZZERIA_PASSWORD = "password";


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
            INSERT INTO user_pizzeria(
            first_name,
            last_name,
            phone_number,
            email,
            role_id,
            birth_date,
            password
            )
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE user_pizzeria
            SET
            first_name = ?,
            last_name = ?,
            phone_number = ?,
            email = ?,
            role_id = ?,
            birth_date = ?,
            password = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
                up.id,
                first_name,
                last_name,
                phone_number,
                email,
                r.role_name,
                birth_date,
                password
            FROM user_pizzeria up
             join role r on r.id = up.role_id
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE up.id = ?
            """;

    private static final String GET_BY_PHONE_NUMBER_AND_PASSWORD_SQL = """
            SELECT
                up.id,
                first_name,
                last_name,
                phone_number,
                email,
                r.role_name,
                birth_date,
                password
            FROM user_pizzeria up
             join role r on r.id = up.role_id
             WHERE phone_number = ? AND password = ?
            """;

    private UserPizzeriaDaoImpl() {
    }

    public Optional<UserPizzeriaEntity> findByPhoneNumberAndPassword(String phoneNumber, String password){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PHONE_NUMBER_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserPizzeriaEntity userPizzeriaEntity = null;
            if (resultSet.next()) {
                userPizzeriaEntity = buildUserPizzeria(resultSet);
            }
            return Optional.ofNullable(userPizzeriaEntity);
        } catch (SQLException e) {
            throw new DaoException("Can not find user pizzeria by phone number and password", e);
        }
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
            throw new DaoException("Can not find all users pizzeria", e);
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
            throw new DaoException("Can not find user pizzeria by id", e);
        }
    }

    private UserPizzeriaEntity buildUserPizzeria(ResultSet resultSet) throws SQLException {
        RoleEntity role = RoleEntity
                .builder()
                .roleName(resultSet.getString(ROLE))
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
                .password(resultSet.getString(USER_PIZZERIA_PASSWORD))
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
            preparedStatement.setString(7, userPizzeriaEntity.getPassword());
            preparedStatement.setLong(8, userPizzeriaEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update user pizzeria", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete user pizzeria", e);
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
            preparedStatement.setString(7, userPizzeriaEntity.getPassword());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userPizzeriaEntity.setId(generatedKeys.getLong(USER_PIZZERIA_ID));
            }
            return userPizzeriaEntity;
        } catch (SQLException e) {
            throw new DaoException("Can not save user pizzeria", e);
        }
    }


}
