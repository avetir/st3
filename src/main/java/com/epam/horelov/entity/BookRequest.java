package com.epam.horelov.entity;

import java.time.LocalDateTime;

public class BookRequest {

    private String id;
    private LocalDateTime dateTimeIn;
    private LocalDateTime dateTimeOut;
    private RequestStatus status;
    private RoomClass roomClass;

    private String userId;
    private int roomNumber;
    private int placesNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(LocalDateTime dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public LocalDateTime getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(LocalDateTime dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }



}
