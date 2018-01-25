package com.epam.horelov.dal.dao;

import com.epam.horelov.entity.Room;

import java.util.List;

public interface RoomDao {

    public List<Room> getAllRooms();
    public void updateRoomStatus(int roomNumber);

}
