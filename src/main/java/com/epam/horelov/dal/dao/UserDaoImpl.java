package com.epam.horelov.dal.dao;

import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.dal.dbcp.ConnectionPoolImpl;
import com.epam.horelov.entity.Roles;
import com.epam.horelov.entity.User;
import com.epam.horelov.exception.CustomException;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String SET_AUTO_COMMIT_FALSE_ERROR_MSG = "Cannot set auto commit of connection to false";
    private static final String STATEMENTS_PREPARING_ERROR_MSG = "Cannot prepare statements";
    private static final String GET_USER_BY_EMAIL_ERROR_MSG = "Cannot get user by email";
    private static final String INSERT_USER_ERROR_MSG = "Cannot insert user";
    private static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM h_user WHERE H_user.email = ?;";
    private static final String INSERT_USER_QUERY = "INSERT INTO h_user (id, name, email, password, role) VALUES (?, ?, ?, ?, ?::USER_ROLE);";

    private Connection connection;
    private PreparedStatement selectUserPreparedStatement;
    private PreparedStatement insertUserPreparedStatement;

    public UserDaoImpl() {
        ConnectionPool connectionPool = new ConnectionPoolImpl();
        connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            throw new CustomException(SET_AUTO_COMMIT_FALSE_ERROR_MSG, ex);
        }
        try {
            selectUserPreparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_QUERY);
            insertUserPreparedStatement = connection.prepareStatement(INSERT_USER_QUERY);
        } catch (SQLException ex) {
            throw new CustomException(STATEMENTS_PREPARING_ERROR_MSG, ex);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            selectUserPreparedStatement.setString(1, email);
            ResultSet rs = selectUserPreparedStatement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getString("id"));
                user.setFullName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getInt("password"));
                user.setRole(Roles.valueOf(rs.getString("role")));
            }
            return user;
        } catch (SQLException ex) {
            throw new CustomException(GET_USER_BY_EMAIL_ERROR_MSG, ex);
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            insertUserPreparedStatement.setString(1, user.getId());
            insertUserPreparedStatement.setString(2, user.getFullName());
            insertUserPreparedStatement.setString(3, user.getEmail());
            insertUserPreparedStatement.setInt(4, user.getPassword());
            insertUserPreparedStatement.setString(5, user.getRole().toString());
            insertUserPreparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            throw new CustomException(INSERT_USER_ERROR_MSG, ex);
        }

    }
}
