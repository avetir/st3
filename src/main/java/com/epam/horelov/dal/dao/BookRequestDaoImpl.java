package com.epam.horelov.dal.dao;

import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.dal.dbcp.ConnectionPoolImpl;
import com.epam.horelov.entity.BookRequest;
import com.epam.horelov.entity.RequestStatus;
import com.epam.horelov.entity.RoomClass;
import com.epam.horelov.exception.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRequestDaoImpl implements BookRequestDao {

    private static final String SET_AUTO_COMMIT_FALSE_ERROR_MSG = "Cannot set auto commit of connection to false";
    private static final String STATEMENTS_PREPARING_ERROR_MSG = "Cannot prepare statement";
    private static final String INSERT_BOOK_REQUEST_ERROR_MSG = "Cannot insert book request";
    private static final String GET_ALL_REQUESTS_ERROR_MSG = "Cannot get requests list";
    private static final String GET_REQUEST_BY_ID_ERROR_MSG = "Cannot get request by id";
    private static final String UPDATE_REQUEST_STATUS_ERROR_MSG = "Cannot update request status";

    private static final String INSERT_BOOK_REQUEST_QUERY =
            "INSERT INTO h_request (id, status, places_number, date_time_in, date_time_out, user_id, room_number, room_class, request_date) " +
            "VALUES (?, ?::REQUEST_STATUS, ?, ?, ?, ?, ?, ?::ROOM_CLASS_TYPE, ?);";
    private static final String SELECT_ALL_REQUESTS_QUERY = "SELECT * FROM h_request;";
    private static final String SELECT_REQUEST_BY_ID_QUERY = "SELECT * FROM h_request WHERE id = ?;";
    private static final String UPDATE_REQUEST_STATUS_QUERY = "UPDATE h_request SET status = ? where id = ?;";

    private Connection connection;
    private PreparedStatement insertBookRequestPreparedStatement;
    private PreparedStatement selectAllRequestsPreparedStatement;
    private PreparedStatement selectRequestByIdPreparedStatement;
    private PreparedStatement updateRequestStatusPreparedStatement;

    public BookRequestDaoImpl(ConnectionPool connectionPool) {
        connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            throw new CustomException(SET_AUTO_COMMIT_FALSE_ERROR_MSG, ex);
        }
        try {
            insertBookRequestPreparedStatement = connection.prepareStatement(INSERT_BOOK_REQUEST_QUERY);
            selectAllRequestsPreparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS_QUERY);
            selectRequestByIdPreparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_ID_QUERY);
            updateRequestStatusPreparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS_QUERY);
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
            insertBookRequestPreparedStatement.setTimestamp(9, Timestamp.valueOf(bookRequest.getRequestDateTime()));
            insertBookRequestPreparedStatement.executeUpdate();


            connection.commit();
        } catch (SQLException ex) {
            throw new CustomException(INSERT_BOOK_REQUEST_ERROR_MSG, ex);
        }
    }

    @Override
    public List<BookRequest> getAllRequests() {
        try {
            ResultSet resultSet = selectAllRequestsPreparedStatement.executeQuery();
            ArrayList<BookRequest> requestList = new ArrayList<>();
            while (resultSet.next()) {
                BookRequest bookRequest = new BookRequest();
                bookRequest.setId(resultSet.getString("id"));
                bookRequest.setDateTimeIn(resultSet.getTimestamp("date_time_in").toLocalDateTime());
                bookRequest.setDateTimeOut(resultSet.getTimestamp("date_time_out").toLocalDateTime());
                bookRequest.setRequestDateTime(resultSet.getTimestamp("request_date").toLocalDateTime());
                bookRequest.setStatus(RequestStatus.valueOf(resultSet.getString("status")));
                bookRequest.setUserId(resultSet.getString("user_id"));
                bookRequest.setRoomNumber(resultSet.getInt("room_number"));
                bookRequest.setRoomClass(RoomClass.valueOf(resultSet.getString("room_class")));
                bookRequest.setPlacesNumber(resultSet.getInt("places_number"));
                requestList.add(bookRequest);
            }
            connection.commit();
            return requestList;
        } catch (SQLException ex) {
            throw new CustomException(GET_ALL_REQUESTS_ERROR_MSG, ex);
        }
    }

    @Override
    public BookRequest getRequestById(String id) {
        try {
            selectRequestByIdPreparedStatement.setString(1, id);
            ResultSet resultSet = selectRequestByIdPreparedStatement.executeQuery();
            BookRequest bookRequest = new BookRequest();
            while (resultSet.next()) {
                bookRequest.setId(resultSet.getString("id"));
                bookRequest.setDateTimeIn(resultSet.getTimestamp("date_time_in").toLocalDateTime());
                bookRequest.setDateTimeOut(resultSet.getTimestamp("date_time_out").toLocalDateTime());
                bookRequest.setRequestDateTime(resultSet.getTimestamp("request_date").toLocalDateTime());
                bookRequest.setStatus(RequestStatus.valueOf(resultSet.getString("status")));
                bookRequest.setUserId(resultSet.getString("user_id"));
                bookRequest.setRoomNumber(resultSet.getInt("room_number"));
                bookRequest.setRoomClass(RoomClass.valueOf(resultSet.getString("room_class")));
                bookRequest.setPlacesNumber(resultSet.getInt("places_number"));
            }
            connection.commit();
            return bookRequest;
        } catch (SQLException ex) {
            throw new CustomException(GET_REQUEST_BY_ID_ERROR_MSG, ex);
        }
    }

    @Override
    public void updateRequestStatus(RequestStatus requestStatus, String id) {
        try {
            updateRequestStatusPreparedStatement.setString(1, requestStatus.toString());
            updateRequestStatusPreparedStatement.setString(2, id);
            updateRequestStatusPreparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            throw new CustomException(UPDATE_REQUEST_STATUS_ERROR_MSG, ex);
        }
    }


}
