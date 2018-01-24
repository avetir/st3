package com.epam.horelov.dal.dao;

import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.dal.dbcp.ConnectionPoolImpl;
import com.epam.horelov.entity.BookRequest;
import com.epam.horelov.exception.CustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookRequestDaoImpl implements BookRequestDao {

    private static final String SET_AUTO_COMMIT_FALSE_ERROR_MSG = "Cannot set auto commit of connection to false";
    private static final String STATEMENTS_PREPARING_ERROR_MSG = "Cannot prepare statement";
    private static final String INSERT_BOOK_REQUEST_ERROR_MSG = "Cannot insert book request";
    private static final String INSERT_BOOK_REQUEST_QUERY = "" +
            "INSERT INTO " +
            "h_request (id, status, places_number, date_time_in, date_time_out, user_id, room_number, room_class) " +
            "VALUES " +
            "(?, ?::REQUEST_STATUS, ?, ?, ?, ?, ?, ?::ROOM_CLASS_TYPE);";
    private static final String UPDATE_ROOM_STATUS_QUERY = "UPDATE h_room SET status = 'BOOKED' WHERE h_room.room_number = ?;";

    private Connection connection;
    private PreparedStatement insertBookRequestPreparedStatement;
    private PreparedStatement updateRoomStatusStatement;

    public BookRequestDaoImpl() {
        ConnectionPool connectionPool = new ConnectionPoolImpl();
        connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            throw new CustomException(SET_AUTO_COMMIT_FALSE_ERROR_MSG, ex);
        }
        try {
            insertBookRequestPreparedStatement = connection.prepareStatement(INSERT_BOOK_REQUEST_QUERY);
            updateRoomStatusStatement = connection.prepareStatement(UPDATE_ROOM_STATUS_QUERY);
        } catch (SQLException ex) {
            throw new CustomException(STATEMENTS_PREPARING_ERROR_MSG, ex);
        }
    }

    @Override
    public void insertBookRequest(BookRequest bookRequest) {
        try {
            insertBookRequestPreparedStatement.setString(1, bookRequest.getId());
            insertBookRequestPreparedStatement.setString(2, bookRequest.getStatus().toString());
            insertBookRequestPreparedStatement.setInt(3, bookRequest.getPlacesNumber());
            insertBookRequestPreparedStatement.setTimestamp(4, Timestamp.valueOf(bookRequest.getDateTimeIn()));
            insertBookRequestPreparedStatement.setTimestamp(5, Timestamp.valueOf(bookRequest.getDateTimeOut()));
            insertBookRequestPreparedStatement.setString(6, bookRequest.getUserId());
            insertBookRequestPreparedStatement.setInt(7, bookRequest.getRoomNumber());
            insertBookRequestPreparedStatement.setString(8, bookRequest.getRoomClass().toString());
            insertBookRequestPreparedStatement.executeUpdate();

            updateRoomStatusStatement.setInt(1,bookRequest.getRoomNumber());
            updateRoomStatusStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            throw new CustomException(INSERT_BOOK_REQUEST_ERROR_MSG, ex);
        }

    }
}
