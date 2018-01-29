package com.epam.horelov.service;

import com.epam.horelov.dal.dao.RoomDao;
import com.epam.horelov.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private RoomDao roomDao;

    public RoomService(RoomDao roomDao){
        this.roomDao = roomDao;
    }

    public List<Room> getAllRooms(){
        return roomDao.getAllRooms();
    }

    public Room getRoomByNumber(int roomNumber){
        return roomDao.getRoomByNumber(roomNumber);
    }

}
