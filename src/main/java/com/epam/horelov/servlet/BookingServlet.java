package com.epam.horelov.servlet;

import com.epam.horelov.entity.Room;
import com.epam.horelov.entity.RoomClass;
import com.epam.horelov.entity.RoomStatus;
import com.epam.horelov.service.BookingService;
import com.epam.horelov.service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;
    private RoomService roomService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomService = (RoomService) getServletContext().getAttribute("room_service");
        ArrayList<Room> roomList = new ArrayList<>();
        for (Room room : roomService.getAllRooms()){
            if (RoomStatus.EMPTY.equals(room.getStatus())){
                roomList.add(room);
            }
        }
        req.setAttribute("emptyRooms", roomList);

        HashMap<RoomClass, Integer> emptyRoomsOfEachClass = new HashMap<>();

        for (Room room : roomList) {
            if (!emptyRoomsOfEachClass.containsKey(room.getRoomClass())){
                emptyRoomsOfEachClass.put(room.getRoomClass(), 1);
            } else {
                emptyRoomsOfEachClass.replace(room.getRoomClass(), emptyRoomsOfEachClass.get(room.getRoomClass())+1);
            }
        }

        req.setAttribute("emptyRoomsOfEachClass", emptyRoomsOfEachClass);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/booking.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        bookingService = (BookingService) servletContext.getAttribute("booking_service");
        bookingService.book(req);
        this.doGet(req, resp);
    }
}
