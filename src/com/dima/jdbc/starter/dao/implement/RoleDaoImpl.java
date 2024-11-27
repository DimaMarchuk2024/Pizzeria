package com.dima.jdbc.starter.dao.implement;

import com.dima.jdbc.starter.dao.RoleDao;
import com.dima.jdbc.starter.entity.RoleEntity;
import com.dima.jdbc.starter.exception.DaoException;
import com.dima.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {

    private static final String ROLE_ID = "id";
    private static final String ROLE_NAME = "role_name";

    private static RoleDaoImpl instance;

    public static synchronized RoleDaoImpl getInstance() {
        if (instance == null) {
            instance = new RoleDaoImpl();
        }
        return instance;
    }

    private static final String DELETE_SQL = """
            DELETE FROM role
            WHERE id = ?;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO role (role_name)
            VALUES (?);
                 """;

    private static final String UPDATE_SQL = """
            UPDATE role
            SET role_name = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT
            id, role_name
            FROM role
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private RoleDaoImpl() {
    }

    public List<RoleEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RoleEntity> roleEntities = new ArrayList<>();
            while (resultSet.next()) {
                roleEntities.add(buildRole(resultSet));
            }
            return roleEntities;
        } catch (SQLException e) {
            throw new DaoException("Can not find all roles", e);
        }
    }

    public Optional<RoleEntity> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            RoleEntity roleEntity = null;
            if (resultSet.next()) {
                roleEntity = buildRole(resultSet);
            }
            return Optional.ofNullable(roleEntity);
        } catch (SQLException e) {
            throw new DaoException("Can not find role by id",e);
        }
    }

    private RoleEntity buildRole(ResultSet resultSet) throws SQLException {
        return RoleEntity
                .builder()
                .id(resultSet.getLong(ROLE_ID))
                .roleName(resultSet.getString(ROLE_NAME))
                .build();
    }

    public void update(RoleEntity roleEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, roleEntity.getRoleName());
            preparedStatement.setLong(2, roleEntity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can not update role", e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Can not delete role",e);
        }
    }

    public RoleEntity save(RoleEntity roleEntity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, roleEntity.getRoleName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                roleEntity.setId(generatedKeys.getLong(ROLE_ID));
            }
            return roleEntity;
        } catch (SQLException e) {
            throw new DaoException("Can not save role", e);
        }
    }
}
