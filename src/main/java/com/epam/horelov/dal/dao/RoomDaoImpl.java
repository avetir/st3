package com.epam.horelov.dal.dao;

import com.epam.horelov.dal.dbcp.ConnectionPool;
import com.epam.horelov.entity.Room;
import com.epam.horelov.entity.RoomClass;
import com.epam.horelov.entity.RoomStatus;
import com.epam.horelov.exception.CustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final String SET_AUTO_COMMIT_FALSE_ERROR_MSG = "Cannot set auto commit of connection to false";
    private static final String STATEMENTS_PREPARING_ERROR_MSG = "Cannot prepare statements";
    private static final String GET_ALL_ROOMS_ERROR_MSG = "Cannot get room list";
    private static final String GET_ROOM_BY_NUMBER_ERROR_MSG = "Cannot get room by number";
    private static final String SELECT_ALL_ROOMS_QUERY = "SELECT * FROM h_room;";
    private static final String UPDATE_ROOM_STATUS_QUERY = "UPDATE h_room SET status = ? WHERE h_room.room_number = ?;";

    public static final String ROOM_NUMBER = "room_number";
    public static final String CAPACITY = "capacity";
    public static final String ROOM_CLASS = "room_class";
    public static final String STATUS = "status";
    public static final String PRICE = "price";

    private Connection connection;
    private PreparedStatement selectAllRoomsPreparedStatement;
    private PreparedStatement updateRoomStatusPreparedStatement;


    public RoomDaoImpl(ConnectionPool connectionPool){
        connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            throw new CustomException(SET_AUTO_COMMIT_FALSE_ERROR_MSG, ex);
        }
        try {
            selectAllRoomsPreparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS_QUERY);
            updateRoomStatusPreparedStatement = connection.prepareStatement(UPDATE_ROOM_STATUS_QUERY);
        } catch (SQLException ex) {
            throw new CustomException(STATEMENTS_PREPARING_ERROR_MSG, ex);
        }
    }

    @Override
    public List<Room> getAllRooms() {
        try {
            ResultSet resultSet = selectAllRoomsPreparedStatement.executeQuery();
            ArrayList<Room> roomArrayList = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setNumber(resultSet.getInt(ROOM_NUMBER));
                room.setCapacity(resultSet.getInt(CAPACITY));
                room.setRoomClass(RoomClass.valueOf(resultSet.getString(ROOM_CLASS)));
                room.setStatus(RoomStatus.valueOf(resultSet.getString(STATUS)));
                room.setPrice(resultSet.getInt(PRICE));
                roomArrayList.add(room);
            }
            return roomArrayList;
        } catch (SQLException ex) {
            throw new CustomException(GET_ALL_ROOMS_ERROR_MSG, ex);
        }
    }

    @Override
    public Room getRoomByNumber(int roomNumber){
        try {
            ResultSet resultSet = selectAllRoomsPreparedStatement.executeQuery();
            Room room = new Room();
            while (resultSet.next()) {
                room.setNumber(resultSet.getInt(ROOM_NUMBER));
                room.setCapacity(resultSet.getInt(CAPACITY));
                room.setRoomClass(RoomClass.valueOf(resultSet.getString(ROOM_CLASS)));
                room.setStatus(RoomStatus.valueOf(resultSet.getString(STATUS)));
                room.setPrice(resultSet.getInt(PRICE));
            }
            return room;
        } catch (SQLException ex) {
            throw new CustomException(GET_ROOM_BY_NUMBER_ERROR_MSG, ex);
        }
    }

    @Override
    public void updateRoomStatus(int roomNumber) {

    }
}
